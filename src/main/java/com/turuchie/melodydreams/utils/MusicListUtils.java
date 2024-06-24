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
import com.turuchie.melodydreams.models.Song;
import com.turuchie.melodydreams.models.User;
import com.turuchie.melodydreams.models.UserPlaylist;
import com.turuchie.melodydreams.services.MetricsService;
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
    static MetricsService metricsServ;	

    @Autowired
    static MetricsUtil metricsUtil;	

	public MusicListUtils(SongService songServ, UserService userServ,
		MetricsService metricsServ, MetricsUtil metricsUtil) {
        MusicListUtils.songServ = songServ;
        MusicListUtils.userServ = userServ;
        MusicListUtils.metricsUtil = metricsUtil;
	    }

	// Set All Music List
	public void setMusicList(Model model, HttpServletRequest request) {
	    String musicList = prepareMusicList(request);
	    String mappedMusicList = mapMusicList(request);

	    // Add both music lists to the model
	    model.addAttribute("musicList", musicList);
	    model.addAttribute("mappedMusicList", mappedMusicList);
	    prepareAndSetSongListIntoModel(model);
	}

	// Set All Music List
	public void setSearchedMusicList(Model model, HttpServletRequest request, List<Song> songs) {
	    String musicList = prepareSearchedMusicList(request, songs);
	    String mappedMusicList = mapMusicList(request);

	    // Add both music lists to the model
	    model.addAttribute("musicList", musicList);
	    model.addAttribute("mappedMusicList", mappedMusicList);
	    prepareAndSetSongListIntoModel(model);
	}

	// Set Users Music List
	public void setUsersMusicList(Model model, HttpServletRequest request, Long userId) {
	    String musicList = prepareUserMusicList(request, userId);

	    // Add both music lists to the model
	    model.addAttribute("userMusicList", musicList);
	    prepareAndSetUserSongListIntoModel(model, userId);
	}

	// Set Single Music List
	public void setSingleMusicList(Model model, HttpServletRequest request, Long songId) {
	    String musicList = prepareSingleSongMusicList(request, songId);

	    // Add both music lists to the model
	    model.addAttribute("singleMusicList", musicList);
	    prepareAndSetSingleSongListIntoModel(model, songId);
	}

	// Method to retrieve user ID from session
	public static Long getUserIdFromSession(HttpServletRequest request) {
	    return (Long) request.getSession().getAttribute("user_id");
	}

	// All Mapped Song List
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


	// Modified prepareMusicList method// Modified prepareMusicList method
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

	        // Calculate metrics
	        int totalPlayCount = song.getMetrics().stream().mapToInt(Metrics::getPlayCount).sum();
	        int totalPauseCount = song.getMetrics().stream().mapToInt(Metrics::getPauseCount).sum();
	        float averageSeekSliderValue = (float) song.getMetrics().stream().mapToDouble(Metrics::getSeekSliderValue).average().orElse(0);
	        long accountHistory = metricsUtil.calculateAccountHistory(song.getCreatedAt());
	        String createdAtDate = metricsUtil.formatDate(song.getCreatedAt());

	        musicList.append("{");
	        musicList.append("\"trackId\": \"").append(song.getId()).append("\", ");
	        musicList.append("\"trackUserId\": \"").append(song.getUser().getId()).append("\", ");
	        musicList.append("\"album\": \"").append(song.getAlbum()).append("\", ");
	        musicList.append("\"genre\": \"").append(song.getGenre()).append("\", ");
	        musicList.append("\"name\": \"").append(song.getTrackTitle()).append("\", ");
	        musicList.append("\"artist\": \"").append(song.getTrackArtist()).append("\", ");
	        musicList.append("\"download\": \"").append(song.getAudioFileName()).append("\", ");
	        musicList.append("\"description\": \"").append(song.getDescription()).append("\", ");
	        musicList.append("\"playCount\": ").append(totalPlayCount).append(", ");
	        musicList.append("\"pauseCount\": ").append(totalPauseCount).append(", ");
	        musicList.append("\"seekSliderValue\": ").append(averageSeekSliderValue).append(", ");
	        musicList.append("\"createdAtDate\": \"").append(createdAtDate).append("\", ");
	        musicList.append("\"accountHistory\": ").append(accountHistory).append(", ");
	        musicList.append("\"img\": \"").append(request.getContextPath()).append("/FileUploads/")
	                 .append(song.getTrackImageDataUrl()).append("/").append(song.getTrackImageFileName()).append("\", ");
	        musicList.append("\"music\": \"").append(request.getContextPath()).append("/FileUploads/")
	                 .append(song.getAudioDataUrl()).append("/").append(song.getAudioFileName()).append("\",");

	        // Liked Songs
	        musicList.append("\"likedSongs\": [");
	        List<LikedSong> likedSongs = song.getLikedSongs();
	        if (likedSongs != null && !likedSongs.isEmpty()) {
	            for (int j = 0; j < likedSongs.size(); j++) {
	                LikedSong likedSong = likedSongs.get(j);
	                musicList.append("{");
	                musicList.append("\"likingUserId\": \"").append(likedSong.getLikingUserId()).append("\"");
	                musicList.append("}");
	                if (j < likedSongs.size() - 1) {
	                    musicList.append(", ");
	                }
	            }
	        }
	        musicList.append("], ");
	        musicList.append("\"likedSongsCount\": ").append(likedSongs.size()).append(", ");

	        // Liked Users
	        musicList.append("\"likedUsers\": [");
	        List<LikedUser> likedUsers = song.getUser().getLikedUsers();
	        if (likedUsers != null && !likedUsers.isEmpty()) {
	            for (int j = 0; j < likedUsers.size(); j++) {
	                LikedUser likedUser = likedUsers.get(j);
	                musicList.append("{");
	                musicList.append("\"userLikedId\": \"").append(likedUser.getUserLikedId()).append("\"");
	                musicList.append("}");
	                if (j < likedUsers.size() - 1) {
	                    musicList.append(", ");
	                }
	            }
	        }
	        musicList.append("], ");

	        // Followed Users
	        musicList.append("\"followedUsers\": [");
	        List<FollowedUser> followedUsers = song.getUser().getFollowedUsers();
	        if (followedUsers != null && !followedUsers.isEmpty()) {
	            for (int j = 0; j < followedUsers.size(); j++) {
	                FollowedUser followedUser = followedUsers.get(j);
	                musicList.append("{");
	                musicList.append("\"followingUserId\": \"").append(followedUser.getFollowingUserId()).append("\"");
	                musicList.append("}");
	                if (j < followedUsers.size() - 1) {
	                    musicList.append(", ");
	                }
	            }
	        }
	        musicList.append("], ");

	        // Include logged-in user data
	        if (loggedInUser != null) {
	            musicList.append("\"user\": {");
	            musicList.append("\"userId\": \"").append(loggedInUser.getId()).append("\", ");
	            musicList.append("\"loggedUserName\": \"").append(loggedInUser.getFirstName()).append("\"");
	            // Add more user-related data as needed...
	            musicList.append("}");
	        }

	        musicList.append("}");
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

	    // Instantiate a new list to hold the songs
	    //List<Song> songs = songServ.getAll();

	    // Perform null check and handle accordingly
	    if (songs == null || songs.isEmpty()) {
	        return "[]"; // Return an empty JSON array if no songs are found
	    }

	    // Build the JSON string representing the music list
	    StringBuilder musicList = new StringBuilder("[");
	    for (int i = 0; i < songs.size(); i++) {
	        Song song = songs.get(i);

	        // Calculate metrics
	        int totalPlayCount = song.getMetrics().stream().mapToInt(Metrics::getPlayCount).sum();
	        int totalPauseCount = song.getMetrics().stream().mapToInt(Metrics::getPauseCount).sum();
	        float averageSeekSliderValue = (float) song.getMetrics().stream().mapToDouble(Metrics::getSeekSliderValue).average().orElse(0);
	        long accountHistory = metricsUtil.calculateAccountHistory(song.getCreatedAt());
	        String createdAtDate = metricsUtil.formatDate(song.getCreatedAt());

	        musicList.append("{");
	        musicList.append("\"trackId\": \"").append(song.getId()).append("\", ");
	        musicList.append("\"trackUserId\": \"").append(song.getUser().getId()).append("\", ");
	        musicList.append("\"album\": \"").append(song.getAlbum()).append("\", ");
	        musicList.append("\"genre\": \"").append(song.getGenre()).append("\", ");
	        musicList.append("\"name\": \"").append(song.getTrackTitle()).append("\", ");
	        musicList.append("\"artist\": \"").append(song.getTrackArtist()).append("\", ");
	        musicList.append("\"download\": \"").append(song.getAudioFileName()).append("\", ");
	        musicList.append("\"description\": \"").append(song.getDescription()).append("\", ");
	        musicList.append("\"playCount\": ").append(totalPlayCount).append(", ");
	        musicList.append("\"pauseCount\": ").append(totalPauseCount).append(", ");
	        musicList.append("\"seekSliderValue\": ").append(averageSeekSliderValue).append(", ");
	        musicList.append("\"createdAtDate\": \"").append(createdAtDate).append("\", ");
	        musicList.append("\"accountHistory\": ").append(accountHistory).append(", ");
	        musicList.append("\"img\": \"").append(request.getContextPath()).append("/FileUploads/")
	                 .append(song.getTrackImageDataUrl()).append("/").append(song.getTrackImageFileName()).append("\", ");
	        musicList.append("\"music\": \"").append(request.getContextPath()).append("/FileUploads/")
	                 .append(song.getAudioDataUrl()).append("/").append(song.getAudioFileName()).append("\",");

	        // Liked Songs
	        musicList.append("\"likedSongs\": [");
	        List<LikedSong> likedSongs = song.getLikedSongs();
	        if (likedSongs != null && !likedSongs.isEmpty()) {
	            for (int j = 0; j < likedSongs.size(); j++) {
	                LikedSong likedSong = likedSongs.get(j);
	                musicList.append("{");
	                musicList.append("\"likingUserId\": \"").append(likedSong.getLikingUserId()).append("\"");
	                musicList.append("}");
	                if (j < likedSongs.size() - 1) {
	                    musicList.append(", ");
	                }
	            }
	        }
	        musicList.append("], ");
	        musicList.append("\"likedSongsCount\": ").append(likedSongs.size()).append(", ");

	        // Liked Users
	        musicList.append("\"likedUsers\": [");
	        List<LikedUser> likedUsers = song.getUser().getLikedUsers();
	        if (likedUsers != null && !likedUsers.isEmpty()) {
	            for (int j = 0; j < likedUsers.size(); j++) {
	                LikedUser likedUser = likedUsers.get(j);
	                musicList.append("{");
	                musicList.append("\"userLikedId\": \"").append(likedUser.getUserLikedId()).append("\"");
	                musicList.append("}");
	                if (j < likedUsers.size() - 1) {
	                    musicList.append(", ");
	                }
	            }
	        }
	        musicList.append("], ");

	        // Followed Users
	        musicList.append("\"followedUsers\": [");
	        List<FollowedUser> followedUsers = song.getUser().getFollowedUsers();
	        if (followedUsers != null && !followedUsers.isEmpty()) {
	            for (int j = 0; j < followedUsers.size(); j++) {
	                FollowedUser followedUser = followedUsers.get(j);
	                musicList.append("{");
	                musicList.append("\"followingUserId\": \"").append(followedUser.getFollowingUserId()).append("\"");
	                musicList.append("}");
	                if (j < followedUsers.size() - 1) {
	                    musicList.append(", ");
	                }
	            }
	        }
	        musicList.append("], ");

	        // Include logged-in user data
	        if (loggedInUser != null) {
	            musicList.append("\"user\": {");
	            musicList.append("\"userId\": \"").append(loggedInUser.getId()).append("\", ");
	            musicList.append("\"loggedUserName\": \"").append(loggedInUser.getFirstName()).append("\"");
	            // Add more user-related data as needed...
	            musicList.append("}");
	        }

	        musicList.append("}");
	        if (i < songs.size() - 1) {
	            musicList.append(", ");
	        }
	    }
	    musicList.append("]");
	    return musicList.toString();
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

    // Users Song List
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

        Map<Long, Integer> playCounts = new HashMap<>();
        Map<Long, Integer> pauseCounts = new HashMap<>();
        Map<Long, Float> seekSliderValues = new HashMap<>();
        Map<Long, String> createdAtDates = new HashMap<>();
        Map<Long, Long> accountHistories = new HashMap<>();

        for (Song song : userSongs) {
            int totalPlayCount = song.getMetrics().stream().mapToInt(Metrics::getPlayCount).sum();
            playCounts.put(song.getId(), totalPlayCount);

            int totalPauseCount = song.getMetrics().stream().mapToInt(Metrics::getPauseCount).sum();
            pauseCounts.put(song.getId(), totalPauseCount);

            float averageSeekSliderValue = (float) song.getMetrics().stream().mapToDouble(Metrics::getSeekSliderValue).average().orElse(0);
            seekSliderValues.put(song.getId(), averageSeekSliderValue);

            createdAtDates.put(song.getId(), metricsUtil.formatDate(song.getCreatedAt()));
            accountHistories.put(song.getId(), metricsUtil.calculateAccountHistory(song.getCreatedAt()));
        }

        StringBuilder musicList = new StringBuilder("[");
        for (int i = 0; i < userSongs.size(); i++) {
            Song userSong = userSongs.get(i);
            musicList.append("{")
                     .append("\"trackId\": \"").append(userSong.getId()).append("\", ")
                     .append("\"trackUserId\": \"").append(userSong.getUser().getId()).append("\", ")
                     .append("\"album\": \"").append(userSong.getAlbum()).append("\", ")
                     .append("\"genre\": \"").append(userSong.getGenre()).append("\", ")
                     .append("\"name\": \"").append(userSong.getTrackTitle()).append("\", ")
                     .append("\"artist\": \"").append(userSong.getTrackArtist()).append("\", ")
                     .append("\"download\": \"").append(userSong.getAudioFileName()).append("\", ")
                     .append("\"description\": \"").append(userSong.getDescription()).append("\", ")
                     .append("\"playCount\": \"").append(playCounts.get(userSong.getId())).append("\", ")
                     .append("\"pauseCount\": \"").append(pauseCounts.get(userSong.getId())).append("\", ")
                     .append("\"seekSliderValue\": \"").append(seekSliderValues.get(userSong.getId())).append("\", ")
                     .append("\"createdAt\": \"").append(createdAtDates.get(userSong.getId())).append("\", ")
                     .append("\"accountHistory\": \"").append(accountHistories.get(userSong.getId())).append("\", ")
                     .append(getLikedSongsJson(userSong.getLikedSongs())).append(", ")
                     .append(getLikedUsersJson(userSong.getUser().getLikedUsers())).append(", ")
                     .append(getFollowedUsersJson(userSong.getUser().getFollowedUsers())).append(", ")
                     .append(getFavoriteSongsJson(userSong.getFavoriteSongs())).append(", ")
                     .append(getUserPlaylistsJson(userSong.getUserPlaylists())).append(", ")
                     .append("\"img\": \"").append(request.getContextPath()).append("/FileUploads/")
                         .append(userSong.getTrackImageDataUrl()).append("/").append(userSong.getTrackImageFileName()).append("\", ")
                     .append("\"music\": \"").append(request.getContextPath()).append("/FileUploads/")
                         .append(userSong.getAudioDataUrl()).append("/").append(userSong.getAudioFileName()).append("\"");

            if (loggedInUser != null) {
                musicList.append(", \"user\": {")
                         .append("\"userId\": \"").append(loggedInUser.getId()).append("\", ")
                         .append("\"loggedUserName\": \"").append(loggedInUser.getFirstName()).append("\"")
                         .append("}");
            }

            musicList.append("}");
            if (i < userSongs.size() - 1) {
                musicList.append(", ");
            }
        }
        musicList.append("]");

        return musicList.toString();
    }

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

    private static String getLikedUsersJson(List<LikedUser> likedUsers) {
        StringBuilder json = new StringBuilder("\"likedUsers\": [");
        if (likedUsers != null && !likedUsers.isEmpty()) {
            for (int i = 0; i < likedUsers.size(); i++) {
                LikedUser likedUser = likedUsers.get(i);
                json.append("{\"userLikedId\": \"").append(likedUser.getUserLikedId()).append("\"}");
                if (i < likedUsers.size() - 1) {
                    json.append(", ");
                }
            }
        }
        json.append("]");
        return json.toString();
    }

    private static String getFollowedUsersJson(List<FollowedUser> followedUsers) {
        StringBuilder json = new StringBuilder("\"followedUsers\": [");
        if (followedUsers != null && !followedUsers.isEmpty()) {
            for (int i = 0; i < followedUsers.size(); i++) {
                FollowedUser followedUser = followedUsers.get(i);
                json.append("{\"followingUserId\": \"").append(followedUser.getFollowingUserId()).append("\"}");
                if (i < followedUsers.size() - 1) {
                    json.append(", ");
                }
            }
        }
        json.append("]");
        return json.toString();
    }

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

    public void prepareAndSetUserSongListIntoModel(Model model, Long userId) {
        // Assuming you have a service method to retrieve a user by ID
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

    // Single Song List
    public static String prepareSingleSongMusicList(HttpServletRequest request, Long songId) {
        // Retrieve the song object by songId
        Song oneSong = songServ.getOne(songId);
        Long loggedInUserId = getUserIdFromSession(request);
        User loggedInUser = userServ.getOne(loggedInUserId);

        // Perform null check and handle accordingly
        if (oneSong == null || loggedInUser == null) {
            return "[]";
        }

        // Calculate metrics
        int totalPlayCount = oneSong.getMetrics().stream().mapToInt(Metrics::getPlayCount).sum();
        int totalPauseCount = oneSong.getMetrics().stream().mapToInt(Metrics::getPauseCount).sum();
        float averageSeekSliderValue = (float) oneSong.getMetrics().stream().mapToDouble(Metrics::getSeekSliderValue).average().orElse(0);
        long accountHistory = metricsUtil.calculateAccountHistory(oneSong.getCreatedAt());
        String createdAtDate = metricsUtil.formatDate(oneSong.getCreatedAt());

        // Build the JSON string representing the single song
        StringBuilder musicList = new StringBuilder("[");
        musicList.append("{");
        musicList.append("\"trackId\": \"").append(oneSong.getId()).append("\", ");
        musicList.append("\"trackUserId\": \"").append(oneSong.getUser().getId()).append("\", ");
        musicList.append("\"album\": \"").append(oneSong.getAlbum()).append("\", ");
        musicList.append("\"genre\": \"").append(oneSong.getGenre()).append("\", ");
        musicList.append("\"name\": \"").append(oneSong.getTrackTitle()).append("\", ");
        musicList.append("\"artist\": \"").append(oneSong.getTrackArtist()).append("\", ");
        musicList.append("\"download\": \"").append(oneSong.getAudioFileName()).append("\", ");
        musicList.append("\"description\": \"").append(oneSong.getDescription()).append("\", ");
        musicList.append("\"playCount\": ").append(totalPlayCount).append(", ");
        musicList.append("\"pauseCount\": ").append(totalPauseCount).append(", ");
        musicList.append("\"seekSliderValue\": ").append(averageSeekSliderValue).append(", ");
        musicList.append("\"createdAtDate\": \"").append(createdAtDate).append("\", ");
        musicList.append("\"accountHistory\": ").append(accountHistory).append(", ");
        musicList.append("\"music\": \"").append(request.getContextPath()).append("/FileUploads/")
                 .append(oneSong.getAudioDataUrl()).append("/").append(oneSong.getAudioFileName()).append("\", ");
        musicList.append("\"img\": \"").append(request.getContextPath()).append("/FileUploads/")
                 .append(oneSong.getTrackImageDataUrl()).append("/").append(oneSong.getTrackImageFileName()).append("\",");

        // Liked Songs
        musicList.append("\"likedSongs\": [");
        List<LikedSong> likedSongs = oneSong.getLikedSongs();
        if (likedSongs != null && !likedSongs.isEmpty()) {
            for (int j = 0; j < likedSongs.size(); j++) {
                LikedSong likedSong = likedSongs.get(j);
                musicList.append("{");
                musicList.append("\"likingUserId\": \"").append(likedSong.getLikingUserId()).append("\"");
                musicList.append("}");
                if (j < likedSongs.size() - 1) {
                    musicList.append(", ");
                }
            }
        }
        musicList.append("], ");
        musicList.append("\"likedSongsCount\": ").append(likedSongs.size()).append(", ");

        // Liked Users
        musicList.append("\"likedUsers\": [");
        List<LikedUser> likedUsers = oneSong.getUser().getLikedUsers();
        if (likedUsers != null && !likedUsers.isEmpty()) {
            for (int j = 0; j < likedUsers.size(); j++) {
                LikedUser likedUser = likedUsers.get(j);
                musicList.append("{");
                musicList.append("\"userLikedId\": \"").append(likedUser.getUserLikedId()).append("\"");
                musicList.append("}");
                if (j < likedUsers.size() - 1) {
                    musicList.append(", ");
                }
            }
        }
        musicList.append("], ");

        // Followed Users
        musicList.append("\"followedUsers\": [");
        List<FollowedUser> followedUsers = oneSong.getUser().getFollowedUsers();
        if (followedUsers != null && !followedUsers.isEmpty()) {
            for (int j = 0; j < followedUsers.size(); j++) {
                FollowedUser followedUser = followedUsers.get(j);
                musicList.append("{");
                musicList.append("\"followingUserId\": \"").append(followedUser.getFollowingUserId()).append("\"");
                musicList.append("}");
                if (j < followedUsers.size() - 1) {
                    musicList.append(", ");
                }
            }
        }
        musicList.append("], ");

        // Include logged-in user data
        musicList.append("\"user\": {");
        musicList.append("\"userId\": \"").append(loggedInUser.getId()).append("\", ");
        musicList.append("\"loggedUserName\": \"").append(loggedInUser.getFirstName()).append("\"");
        // Add more user-related data as needed...
        musicList.append("}");

        musicList.append("}]");
        return musicList.toString();
    }

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

}
