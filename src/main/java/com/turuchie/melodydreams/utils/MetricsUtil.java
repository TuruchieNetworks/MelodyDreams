package com.turuchie.melodydreams.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.turuchie.melodydreams.models.LikedSong;
import com.turuchie.melodydreams.models.Playlist;
import com.turuchie.melodydreams.models.Song;
import com.turuchie.melodydreams.services.PlaylistService;
import com.turuchie.melodydreams.services.SongService;

@Component
public class MetricsUtil {
    @Autowired
    private SongService songService;

    @Autowired
    private PlaylistService playlistServ;

    public void addMetricsToModel(Model model) {
        List<Song> songs = songService.getAll();

        Map<Long, Integer> playCounts = new HashMap<>();
        Map<Long, Integer> pauseCounts = new HashMap<>();
        Map<Long, Float> seekSliderValues = new HashMap<>();
        Map<Long, Integer> likeCounts = new HashMap<>(); // Store like counts per song
        Map<Long, String> createdAtDates = new HashMap<>();
        Map<Long, Long> accountHistories = new HashMap<>();

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
