package com.turuchie.melodydreams.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.turuchie.melodydreams.models.FavoriteSong;
import com.turuchie.melodydreams.models.FollowedUser;
import com.turuchie.melodydreams.models.LikedSong;
import com.turuchie.melodydreams.models.LikedUser;
import com.turuchie.melodydreams.models.Metrics;
import com.turuchie.melodydreams.models.Playlist;
import com.turuchie.melodydreams.models.PlaylistSongs;
import com.turuchie.melodydreams.models.Song;
import com.turuchie.melodydreams.models.User;
import com.turuchie.melodydreams.models.UserPlaylist;
import com.turuchie.melodydreams.services.MetricsService;
import com.turuchie.melodydreams.services.PlaylistService;
import com.turuchie.melodydreams.services.SongService;
import com.turuchie.melodydreams.services.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class MusicListUtils {
    @Autowired
    static SongService songServ;

    @Autowired
    static UserService userServ;

    @Autowired
    static PlaylistService playlistServ;

    @Autowired
    static MetricsService metricsServ;	

    @Autowired
    static MetricsUtil metricsUtil;	

	public MusicListUtils(SongService songServ, UserService userServ,
		PlaylistService playlistServ, MetricsService metricsServ, MetricsUtil metricsUtil) {
        MusicListUtils.songServ = songServ;
        MusicListUtils.userServ = userServ;
        MusicListUtils.playlistServ = playlistServ;
        MusicListUtils.metricsUtil = metricsUtil;
	}

	// Set All Music List
	public void setMusicList(Model model, HttpServletRequest request) {
	    String musicList = prepareMusicList(request);
	    String mappedMusicList = mapMusicList(request);
	    Long loggedUserId = getUserIdFromSession(request);

	    // Add both music lists to the model
	    model.addAttribute("musicList", musicList);
	    model.addAttribute("mappedMusicList", mappedMusicList);
	    prepareAndSetSongListIntoModel(model);
        metricsUtil.addPlaylistDataToModel(model, loggedUserId); 
	    metricsUtil.addMetricsToModel(model);
	}

	// Set All Playlist Music List
	public void setPlaylistMusicList(Model model, HttpServletRequest request, List<Playlist> playlist) {
	    String musicList = preparePlaylistsMusicList(request, playlist);
	    String mappedMusicList = mapMusicList(request);
	    Long loggedUserId = getUserIdFromSession(request);

	    // Add both music lists to the model
	    model.addAttribute("musicList", musicList);
	    model.addAttribute("mappedMusicList", mappedMusicList);
	    prepareAndSetSongListIntoModel(model);
        metricsUtil.addPlaylistDataToModel(model, loggedUserId); 
	    metricsUtil.addMetricsToModel(model);
	}

	// Set All Searched Music List
	public void setSearchedMusicList(Model model, HttpServletRequest request, List<Song> songs) {
	    String musicList = prepareSearchedMusicList(request, songs);
	    String mappedMusicList = mapMusicList(request);
	    Long loggedUserId = getUserIdFromSession(request);

	    // Add both music lists to the model
	    model.addAttribute("musicList", musicList);
	    model.addAttribute("mappedMusicList", mappedMusicList);
        metricsUtil.addPlaylistDataToModel(model, loggedUserId); 
	    metricsUtil.addMetricsToModel(model);
	    prepareAndSetSongListIntoModel(model);
	}

	// Set Users Music List
	public void setUsersMusicList(Model model, HttpServletRequest request, Long userId) {
	    String musicList = prepareUserMusicList(request, userId);

	    // Add both music lists to the model
	    model.addAttribute("userMusicList", musicList);
	    prepareAndSetUserSongListIntoModel(model, userId);
        metricsUtil.addPlaylistDataToModel(model, userId); 
	    metricsUtil.addMetricsToModel(model);
	}

	// Set Single Music List
	public void setSingleMusicList(Model model, HttpServletRequest request, Long songId) {
	    String musicList = prepareSingleSongMusicList(request, songId);
	    Long trackArtistId = songServ.getOne(songId).getUser().getId();

	    // Add both music lists to the model
	    model.addAttribute("singleMusicList", musicList);
	    prepareAndSetSingleSongListIntoModel(model, songId);
        metricsUtil.addPlaylistDataToModel(model, trackArtistId); 
        metricsUtil.addSingleMetricsToModel(model, songId);
	}

	// Method to retrieve user ID from session
	public static Long getUserIdFromSession(HttpServletRequest request) {
	    return (Long) request.getSession().getAttribute("user_id");
	}
	
	// Method To Build Music List For Js
	private static String getSongJson(HttpServletRequest request, Song song, User loggedInUser) {
        Map<Long, Integer> playCounts = new HashMap<>();
        Map<Long, Integer> pauseCounts = new HashMap<>();
        Map<Long, Float> seekSliderValues = new HashMap<>();
        Map<Long, String> createdAtDates = new HashMap<>();
        Map<Long, Long> accountHistories = new HashMap<>();
        StringBuilder songJson = new StringBuilder();

        // Calculate metrics
        int totalPlayCount = song.getMetrics().stream().mapToInt(Metrics::getPlayCount).sum();
        playCounts.put(song.getId(), totalPlayCount);

        int totalPauseCount = song.getMetrics().stream().mapToInt(Metrics::getPauseCount).sum();
        pauseCounts.put(song.getId(), totalPauseCount);

        float averageSeekSliderValue = (float) song.getMetrics().stream().mapToDouble(Metrics::getSeekSliderValue).average().orElse(0);
        seekSliderValues.put(song.getId(), averageSeekSliderValue);

        long accountHistory = metricsUtil.calculateAccountHistory(song.getCreatedAt());
        accountHistories.put(song.getId(), metricsUtil.calculateAccountHistory(song.getCreatedAt()));

        String createdAtDate = metricsUtil.formatDate(song.getCreatedAt());
        createdAtDates.put(song.getId(), metricsUtil.formatDate(song.getCreatedAt()));

        // Build Music List 
        songJson.append("{")
                .append("\"trackId\": \"").append(song.getId()).append("\", ")
                .append("\"trackUserId\": \"").append(song.getUser().getId()).append("\", ")
                .append("\"album\": \"").append(song.getAlbum()).append("\", ")
                .append("\"genre\": \"").append(song.getGenre()).append("\", ")
                .append("\"name\": \"").append(song.getTrackTitle()).append("\", ")
                .append("\"artist\": \"").append(song.getTrackArtist()).append("\", ")
                .append("\"download\": \"").append(song.getAudioFileName()).append("\", ")
                .append("\"description\": \"").append(song.getDescription()).append("\", ")
                .append("\"playCount\": ").append(totalPlayCount).append(", ")
                .append("\"pauseCount\": ").append(totalPauseCount).append(", ")
                .append("\"seekSliderValue\": ").append(averageSeekSliderValue).append(", ")
                .append("\"createdAtDate\": \"").append(createdAtDate).append("\", ")
                .append("\"accountHistory\": ").append(accountHistory).append(", ")
                .append("\"img\": \"").append(request.getContextPath()).append("/FileUploads/")
                .append(song.getTrackImageDataUrl()).append("/").append(song.getTrackImageFileName()).append("\", ")
                .append("\"music\": \"").append(request.getContextPath()).append("/FileUploads/")
                .append(song.getAudioDataUrl()).append("/").append(song.getAudioFileName()).append("\",")
                .append(getLikedSongsJson(song.getLikedSongs())).append(", ")
                .append(getLikedUsersJson(song.getUser().getLikedUsers())).append(", ")
                .append(getFollowedUsersJson(song.getUser().getFollowedUsers())).append(", ")
                .append(getFavoriteSongsJson(song.getFavoriteSongs())).append(", ")
                .append(getPlaylistsJson(song.getUser().getPlaylists())).append(", ")
                .append(getPlaylistSongsJson(song.getPlaylistSongs())).append(", ")
                .append(getUserPlaylistsJson(song.getUserPlaylists()));

        if (loggedInUser != null) {
            songJson.append(", \"user\": {")
                    .append("\"userId\": \"").append(loggedInUser.getId()).append("\", ")
                    .append("\"loggedUserName\": \"").append(loggedInUser.getFirstName()).append("\"")
                    .append("}");
        }

        songJson.append("}");
        return songJson.toString();
    }

	// Prepare Music List For All Songs
	public static String prepareMusicList(HttpServletRequest request) {
	    // Retrieve the user ID from the session
	    Long userId = getUserIdFromSession(request);

	    // Retrieve the logged-in user from the database
	    User loggedInUser = userServ.getOne(userId);

	    // Instantiate a new list to hold the songs
	    List<Song> songs = songServ.getAll();

	    // Perform null check and handle accordingly
	    if (songs == null || songs.isEmpty()) {
	        return "[]"; // Return an empty JSON array if no songs are found
	    }

	    // Build the JSON string representing the music list
        StringBuilder musicList = new StringBuilder("[");
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);

            // Append the song JSON using the helper method
            musicList.append(getSongJson(request, song, loggedInUser));
            if (i < songs.size() - 1) {
                musicList.append(", ");
            }
        }
        musicList.append("]");
        return musicList.toString();
	}

	// Prepare Searched MusicList
	public static String prepareSearchedMusicList(HttpServletRequest request, List<Song> songs) {
	    // Retrieve the user ID from the session
	    Long userId = getUserIdFromSession(request);

	    // Retrieve the logged-in user from the database
	    User loggedInUser = userServ.getOne(userId);

	    // Perform null check and handle accordingly
	    if (songs == null || songs.isEmpty()) {
	        return "[]"; // Return an empty JSON array if no songs are found
	    }

	    // Build the JSON string representing the music list
        StringBuilder musicList = new StringBuilder("[");
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);

            // Append the song JSON using the helper method
            musicList.append(getSongJson(request, song, loggedInUser));
            if (i < songs.size() - 1) {
                musicList.append(", ");
            }
        }
        musicList.append("]");
        return musicList.toString();
	}


    // Prepare Users Song List
    public static String prepareUserMusicList(HttpServletRequest request, Long userId) {
        User oneUser = userServ.getOne(userId);
        Long loggedInUserId = getUserIdFromSession(request);
        User loggedInUser = userServ.getOne(loggedInUserId);

        if (oneUser == null || loggedInUser == null) {
            return "[]";
        }

        List<Song> userSongs = oneUser.getSongs();

        if (userSongs == null || userSongs.isEmpty()) {
            return "[]";
        }

        StringBuilder musicList = new StringBuilder("[");
        for (int i = 0; i < userSongs.size(); i++) {
            Song song = userSongs.get(i);

            // Append the song JSON using the helper method
            musicList.append(getSongJson(request, song, loggedInUser));
            if (i < userSongs.size() - 1) {
                musicList.append(", ");
            }
        }
        musicList.append("]");
        return musicList.toString();
    }

    // Prepare Single Song List
    public static String prepareSingleSongMusicList(HttpServletRequest request, Long songId) {
        // Retrieve the song object by songId
        Song oneSong = songServ.getOne(songId);
        Long loggedInUserId = getUserIdFromSession(request);
        User loggedInUser = userServ.getOne(loggedInUserId);

        // Perform null check and handle accordingly
        if (oneSong == null || loggedInUser == null) {
            return "[]";
        }

        StringBuilder musicList = new StringBuilder("[");
            // Append the song JSON using the helper method
            musicList.append(getSongJson(request, oneSong, loggedInUser));
        musicList.append("]");
        return musicList.toString();
    }

    // Prepare Music List from a List of Playlists
    public static String preparePlaylistsMusicList(HttpServletRequest request, List<Playlist> playlists) {
        Long loggedInUserId = getUserIdFromSession(request);
        User loggedInUser = userServ.getOne(loggedInUserId);

        if (playlists == null || playlists.isEmpty() || loggedInUser == null) {
            return "[]";
        }

        StringBuilder musicList = new StringBuilder("[");
        for (int i = 0; i < playlists.size(); i++) {
            Playlist playlist = playlists.get(i);
            List<PlaylistSongs> playlistSongs = playlist.getPlaylistsongs();

            if (playlistSongs != null) {
                for (int j = 0; j < playlistSongs.size(); j++) {
                    Song song = playlistSongs.get(j).getSong();
                    musicList.append(getSongJson(request, song, loggedInUser));
                    if (i < playlists.size() - 1 || j < playlistSongs.size() - 1) {
                        musicList.append(", ");
                    }
                }
            }
        }
        musicList.append("]");
        return musicList.toString();
    }

	// Prepare Playlist Song List From Id-Playlists
	public static String preparePlaylistMusicList(HttpServletRequest request, Long playlistId) {
	    Playlist playlist = playlistServ.getOne(playlistId);
	    Long loggedInUserId = getUserIdFromSession(request);
	    User loggedInUser = userServ.getOne(loggedInUserId);
	
	    if (playlist == null || loggedInUser == null) {
	        return "[]";
	    }
	
	    List<PlaylistSongs> playlistSongs = playlist.getPlaylistsongs();
	
	    if (playlistSongs == null || playlistSongs.isEmpty()) {
	        return "[]";
	    }
	
	    StringBuilder musicList = new StringBuilder("[");
	    for (int i = 0; i < playlistSongs.size(); i++) {
	         Song song = playlistSongs.get(i).getSong();
	         musicList.append(getSongJson(request, song, loggedInUser));
	         if (i < playlistSongs.size() - 1) {
	             musicList.append(", ");
	         }
	     }
	     musicList.append("]");
	     return musicList.toString();
	}

	// Prepare Music List For All User's Playlists
	public static String prepareUserPlaylistsMusicList(HttpServletRequest request, Long userId) {
	    User user = userServ.getOne(userId);
	    Long loggedInUserId = getUserIdFromSession(request);
	    User loggedInUser = userServ.getOne(loggedInUserId);

	    if (user == null || loggedInUser == null) {
	        return "[]";
	    }

	    List<Playlist> playlists = user.getPlaylists();

	    if (playlists == null || playlists.isEmpty()) {
	        return "[]";
	    }

	    StringBuilder musicList = new StringBuilder("[");
	    for (Playlist playlist : playlists) {
	        List<PlaylistSongs> playlistSongs = playlist.getPlaylistsongs();
	        if (playlistSongs != null) {
	            for (int i = 0; i < playlistSongs.size(); i++) {
	                Song song = playlistSongs.get(i).getSong();
	                musicList.append(getSongJson(request, song, loggedInUser));
	                if (i < playlistSongs.size() - 1 || playlists.indexOf(playlist) < playlists.size() - 1) {
	                    musicList.append(", ");
	                }
	            }
	        }
	    }
	    musicList.append("]");
	    return musicList.toString();
	}

	// Helper Methods To Get Playlists
	private static String getPlaylistsJson(List<Playlist> playlists) {
	    StringBuilder json = new StringBuilder("\"playlists\": [");
	    if (playlists != null && !playlists.isEmpty()) {
	        for (int i = 0; i < playlists.size(); i++) {
	            Playlist playlist = playlists.get(i);
	            json.append("{\"playlistId\": \"").append(playlist.getId()).append("\", ")
	                .append("\"title\": \"").append(playlist.getTitle()).append("\", ")
	                .append("\"description\": \"").append(playlist.getDescription()).append("\", ")
	                .append(getPlaylistSongsJson(playlist.getPlaylistsongs())).append("}");
	            if (i < playlists.size() - 1) {
	                json.append(", ");
	            }
	        }
	    }
	    json.append("]");
	    return json.toString();
	}

	// Helper Methods To Get Playlist Songs
	private static String getPlaylistSongsJson(List<PlaylistSongs> playlistSongs) {
	    StringBuilder json = new StringBuilder("\"playlistSongs\": [");
	    if (playlistSongs != null && !playlistSongs.isEmpty()) {
	        for (int i = 0; i < playlistSongs.size(); i++) {
	            PlaylistSongs playlistSong = playlistSongs.get(i);
	            json.append("{\"playlistSongId\": \"").append(playlistSong.getId()).append("\", ")
	                .append("\"playlistId\": \"").append(playlistSong.getPlaylist().getId()).append("\", ")
	                .append("\"songId\": \"").append(playlistSong.getSong().getId()).append("\"}");
	            if (i < playlistSongs.size() - 1) {
	                json.append(", ");
	            }
	        }
	    }
	    json.append("]");
	    return json.toString();
	}

    // Helper Methods To Get Liked Songs
    private static String getLikedSongsJson(List<LikedSong> likedSongs) {
        StringBuilder json = new StringBuilder("\"likedSongs\": [");
        if (likedSongs != null && !likedSongs.isEmpty()) {
            for (int i = 0; i < likedSongs.size(); i++) {
                LikedSong likedSong = likedSongs.get(i);
                json.append("{\"likingUserId\": \"").append(likedSong.getLikingUserId()).append("\"}");
                if (i < likedSongs.size() - 1) {
                    json.append(", ");
                }
            }
        }
        json.append("], \"likedSongsCount\": \"").append(likedSongs != null ? likedSongs.size() : 0).append("\"");
        return json.toString();
    }

    // Helper Methods To Get Liked Users
    private static String getLikedUsersJson(List<LikedUser> likedUsers) {
        StringBuilder json = new StringBuilder("\"likedUsers\": [");
        if (likedUsers != null && !likedUsers.isEmpty()) {
            for (int i = 0; i < likedUsers.size(); i++) {
                LikedUser likedUser = likedUsers.get(i);
                json.append("{\"userLikedId\": \"").append(likedUser.getUserLikedId()).append("\", ")
            		.append("\"likingUsers\": \"").append(likedUser.getUser()).append("\"}");
                if (i < likedUsers.size() - 1) {
                    json.append(", ");
                }
            }
        }
        json.append("]");
        return json.toString();
    }

    // Helper Methods To Get Followed Users
    private static String getFollowedUsersJson(List<FollowedUser> followedUsers) {
        StringBuilder json = new StringBuilder("\"followedUsers\": [");
        if (followedUsers != null && !followedUsers.isEmpty()) {
            for (int i = 0; i < followedUsers.size(); i++) {
                FollowedUser followedUser = followedUsers.get(i);
                json.append("{\"followingUserId\": \"").append(followedUser.getFollowingUserId()).append("\", ")
                	.append("\"followingUsers\": \"").append(followedUser.getUser()).append("\"}");
                if (i < followedUsers.size() - 1) {
                    json.append(", ");
                }
            }
        }
        json.append("]");
        return json.toString();
    }

    // Helper Methods To Get Favorite Songs
    private static String getFavoriteSongsJson(List<FavoriteSong> favoriteSongs) {
        StringBuilder json = new StringBuilder("\"favoriteSongs\": [");
        if (favoriteSongs != null && !favoriteSongs.isEmpty()) {
            for (int i = 0; i < favoriteSongs.size(); i++) {
                FavoriteSong favoriteSong = favoriteSongs.get(i);
                json.append("{\"favoriteSongId\": \"").append(favoriteSong.getId()).append("\", ")
                    .append("\"favoriteSongUserId\": \"").append(favoriteSong.getUser().getId()).append("\", ")
                    .append("\"songId\": \"").append(favoriteSong.getSong().getId()).append("\"}");
                if (i < favoriteSongs.size() - 1) {
                    json.append(", ");
                }
            }
        }
        json.append("]");
        return json.toString();
    }

    // Helper Methods To Get User Playlists
    private static String getUserPlaylistsJson(List<UserPlaylist> userPlaylists) {
        StringBuilder json = new StringBuilder("\"userPlaylists\": [");
        if (userPlaylists != null && !userPlaylists.isEmpty()) {
            for (int i = 0; i < userPlaylists.size(); i++) {
                UserPlaylist userPlaylist = userPlaylists.get(i);
                json.append("{\"userPlaylistId\": \"").append(userPlaylist.getId()).append("\", ")
                    .append("\"songId\": \"").append(userPlaylist.getSong().getId()).append("\", ")
                    .append("\"userPlaylistTitle\": \"").append(userPlaylist.getTitle()).append("\"}");
                if (i < userPlaylists.size() - 1) {
                    json.append(", ");
                }
            }
        }
        json.append("]");
        return json.toString();
    }

    public void prepareAndSetSongListIntoModel(Model model) {
        // Assuming you have a service method to retrieve all users
        Iterable<User> allUsers = userServ.findAll();

        // Create a new list to hold the song data
        List<Song> allSongs = new ArrayList<>();

        // Iterate over each user to extract songs
        for (User user : allUsers) {
            if (user.getSongs() != null) {
                // Add songs of the user to the song list
                allSongs.addAll(user.getSongs());
            }
        }

        // Set the song list into the model
        model.addAttribute("allTrackList", allSongs);
    }

    // Helper Method To Search For Users Songs
    public void prepareAndSetUserSongListIntoModel(Model model, Long userId) {
        User oneUser = userServ.getOne(userId);

        // Create a new list to hold the song data
        List<Song> oneUserSongs = new ArrayList<>();

        // Check if the user exists and has songs
        if (oneUser != null && oneUser.getSongs() != null) {
            // Add songs of the user to the song list
            oneUserSongs.addAll(oneUser.getSongs());
        }

        // Set the song list into the model
        model.addAttribute("allUsersTrackList", oneUserSongs);
    }

    // Helper Method To Generate Single Song List 
    public void prepareAndSetSingleSongListIntoModel(Model model, Long songId) {
        // Assuming you have a service method to retrieve a song by ID
        Song song = songServ.getOne(songId);

        // Create a new list to hold the single song data
        List<Song> singleSongList = new ArrayList<>();

        // Check if the song exists
        if (song != null) {
            // Add the single song to the list
            singleSongList.add(song);
        }

        // Set the single song list into the model
        model.addAttribute("singleSongList", singleSongList);
    }

	// Song List, Images and URLs
	public static String mapMusicList(HttpServletRequest request) {
	    List<Song> songs = songServ.getAll();
	
	    // Perform null check and handle accordingly
	    if (songs == null || songs.isEmpty()) {
	        return "[]"; // Return an empty JSON array if no songs are found
	    }

	    // Create an ObjectMapper to build JSON
	    ObjectMapper mapper = new ObjectMapper();
	    ArrayNode musicArray = mapper.createArrayNode();
	
	    // Iterate over songs and add each song to the JSON array
	    for (Song song : songs) {
	        ObjectNode songNode = mapper.createObjectNode();
	        songNode.put("img", request.getContextPath() + "/FileUploads/" + song.getTrackImageDataUrl() + "/" + song.getTrackImageFileName());
	        songNode.put("name", song.getTrackTitle());
	        songNode.put("artist", song.getTrackArtist());
	        songNode.put("music", request.getContextPath() + "/FileUploads/" + song.getAudioDataUrl() + "/" + song.getAudioFileName());
	        musicArray.add(songNode);
	    }
	
	    // Convert the JSON array to a string and return it
	    try {
	        return mapper.writeValueAsString(musicArray);
	    } catch (JsonProcessingException e) {
	        e.printStackTrace(); // Handle the exception appropriately
	        return "[]"; // Return an empty JSON array in case of an error
	    }
	}


}
