package com.turuchie.melodydreams.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.turuchie.melodydreams.models.FollowedUser;
import com.turuchie.melodydreams.models.LikedSong;
import com.turuchie.melodydreams.models.Playlist;
import com.turuchie.melodydreams.models.Song;
import com.turuchie.melodydreams.models.User;
import com.turuchie.melodydreams.services.PlaylistService;
import com.turuchie.melodydreams.services.SongService;
import com.turuchie.melodydreams.services.UserService;

@Component
public class MetricsUtil {
    @Autowired
    private UserService userServ;

    @Autowired
    private SongService songServ;

    @Autowired
    private PlaylistService playlistServ;

    public void addMetricsToModel(Model model) {
        List<Song> songs = songServ.getAll();

        Map<Long, Integer> playCounts = new HashMap<>();
        Map<Long, Integer> pauseCounts = new HashMap<>();
        Map<Long, Float> seekSliderValues = new HashMap<>();
        Map<Long, Integer> likeCounts = new HashMap<>(); // Store like counts per song
        Map<Long, String> createdAtDates = new HashMap<>();
        Map<Long, Long> accountHistories = new HashMap<>();
        Map<Long, List<User>> likedByUsers = new HashMap<>(); // Store users who liked each song
        Map<Long, List<User>> followedUsers = new HashMap<>(); // Store users followed by each song's user
        Map<Long, List<User>> usersLikingSongs = new HashMap<>(); // Store users liking each song

        for (Song song : songs) {
            int totalPlayCount = song.getMetrics().stream().mapToInt(m -> m.getPlayCount()).sum();
            playCounts.put(song.getId(), totalPlayCount);

            int totalPauseCount = song.getMetrics().stream().mapToInt(m -> m.getPauseCount()).sum();
            pauseCounts.put(song.getId(), totalPauseCount);

            float averageSeekSliderValue = (float) song.getMetrics().stream().mapToDouble(m -> m.getSeekSliderValue()).average().orElse(0);
            seekSliderValues.put(song.getId(), averageSeekSliderValue);

            // Calculate total likes for the song
            List<LikedSong> likedSongs = song.getLikedSongs();
            int totalLikes = likedSongs.size();
            likeCounts.put(song.getId(), totalLikes);

            // Collect users who liked the song
            List<User> likingUsers = new ArrayList<>();
            for (LikedSong likedSong : likedSongs) {
                User likingUser = userServ.getOne(likedSong.getLikingUserId());
                likingUsers.add(likingUser);
            }
            likedByUsers.put(song.getId(), likingUsers);

            // Collect followed users for the song's user
            List<User> followedUserList = new ArrayList<>();
            for (FollowedUser followedUser : song.getUser().getFollowedUsers()) {
                User followed = userServ.getOne(followedUser.getFollowingUserId());
                followedUserList.add(followed);
            }
            followedUsers.put(song.getId(), followedUserList);

            // Collect users liking the song
            List<User> usersLikingSong = new ArrayList<>();
            for (LikedSong likedSong : song.getLikedSongs()) {
                User userLikingSong = userServ.getOne(likedSong.getLikingUserId());
                usersLikingSong.add(userLikingSong);
            }
            usersLikingSongs.put(song.getId(), usersLikingSong);

            createdAtDates.put(song.getId(), formatDate(song.getCreatedAt()));
            accountHistories.put(song.getId(), calculateAccountHistory(song.getCreatedAt()));
        }

        model.addAttribute("playCounts", playCounts);
        model.addAttribute("playCountSize", playCounts.size());
        model.addAttribute("pauseCounts", pauseCounts);
        model.addAttribute("pauseCountSize", pauseCounts.size());
        model.addAttribute("seekSliderValues", seekSliderValues);
        model.addAttribute("seekSliderSize", seekSliderValues.size());
        model.addAttribute("likeCounts", likeCounts);
        model.addAttribute("likeCountSize", likeCounts.size());
        model.addAttribute("createdAtDates", createdAtDates);
        model.addAttribute("accountHistories", accountHistories);
        model.addAttribute("likedByUsers", likedByUsers);
        model.addAttribute("followedUsers", followedUsers);
        model.addAttribute("usersLikingSongs", usersLikingSongs);
    }

    public void addSingleMetricsToModel(Model model, Long songId) {
        Song song = songServ.getOne(songId);

        Map<Long, Integer> playCounts = new HashMap<>();
        Map<Long, Integer> pauseCounts = new HashMap<>();
        Map<Long, Float> seekSliderValues = new HashMap<>();
        Map<Long, Integer> likeCounts = new HashMap<>(); // Store like counts per song
        Map<Long, String> createdAtDates = new HashMap<>();
        Map<Long, Long> accountHistories = new HashMap<>();
        Map<Long, List<User>> likedByUsers = new HashMap<>(); // Store users who liked each song
        Map<Long, List<User>> followedUsers = new HashMap<>(); // Store users followed by each song's user
        Map<Long, List<User>> usersLikingSongs = new HashMap<>(); // Store users liking each song

        int totalPlayCount = song.getMetrics().stream().mapToInt(m -> m.getPlayCount()).sum();
        playCounts.put(song.getId(), totalPlayCount);

        int totalPauseCount = song.getMetrics().stream().mapToInt(m -> m.getPauseCount()).sum();
        pauseCounts.put(song.getId(), totalPauseCount);

        float averageSeekSliderValue = (float) song.getMetrics().stream().mapToDouble(m -> m.getSeekSliderValue()).average().orElse(0);
        seekSliderValues.put(song.getId(), averageSeekSliderValue);

        // Calculate total likes for the song
        List<LikedSong> likedSongs = song.getLikedSongs();
        int totalLikes = likedSongs.size();
        likeCounts.put(song.getId(), totalLikes);

        // Collect users who liked the song
        List<User> likingUsers = new ArrayList<>();
        for (LikedSong likedSong : likedSongs) {
            User likingUser = userServ.getOne(likedSong.getLikingUserId());
            likingUsers.add(likingUser);
        }
        likedByUsers.put(song.getId(), likingUsers);

        // Collect followed users for the song's user
        List<User> followedUserList = new ArrayList<>();
        for (FollowedUser followedUser : song.getUser().getFollowedUsers()) {
            User followed = userServ.getOne(followedUser.getFollowingUserId());
            followedUserList.add(followed);
        }
        followedUsers.put(song.getId(), followedUserList);

        // Collect users liking the song
        List<User> usersLikingSong = new ArrayList<>();
        for (LikedSong likedSong : song.getLikedSongs()) {
            User userLikingSong = userServ.getOne(likedSong.getLikingUserId());
            usersLikingSong.add(userLikingSong);
        }
        usersLikingSongs.put(song.getId(), usersLikingSong);

        createdAtDates.put(song.getId(), formatDate(song.getCreatedAt()));
        accountHistories.put(song.getId(), calculateAccountHistory(song.getCreatedAt()));

        model.addAttribute("playCounts", playCounts);
        model.addAttribute("playCountSize", playCounts.size());
        model.addAttribute("pauseCounts", pauseCounts);
        model.addAttribute("pauseCountSize", pauseCounts.size());
        model.addAttribute("seekSliderValues", seekSliderValues);
        model.addAttribute("seekSliderSize", seekSliderValues.size());
        model.addAttribute("likeCounts", likeCounts);
        model.addAttribute("likeCountSize", likeCounts.size());
        model.addAttribute("createdAtDates", createdAtDates);
        model.addAttribute("accountHistories", accountHistories);
        model.addAttribute("likedByUsers", likedByUsers);
        model.addAttribute("followedUsers", followedUsers);
        model.addAttribute("usersLikingSongs", usersLikingSongs);
    }

    public void addPlaylistDataToModel(Model model, Long userId) {
        List<Playlist> playlists = playlistServ.getAll();
        model.addAttribute("playlists", playlists);
        model.addAttribute("hasPlaylists", !playlists.isEmpty());
    }

    public String formatDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy");
        return dateTime.format(formatter);
    }

    public long calculateAccountHistory(LocalDateTime createdAt) {
        return ChronoUnit.YEARS.between(createdAt.toLocalDate(), LocalDate.now());
    }
}
