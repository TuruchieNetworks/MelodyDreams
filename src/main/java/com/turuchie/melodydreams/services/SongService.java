package com.turuchie.melodydreams.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.turuchie.melodydreams.models.Song;
import com.turuchie.melodydreams.repositories.SongRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class SongService {

    @Value("${file.upload-dir}") // Specify the file upload directory in application.properties
    private String uploadDir;

    @Autowired
    private SongRepository songRepo;

    public SongService(SongRepository songRepo) {
        this.songRepo = songRepo;
    }

    public Song getOne(Long id) {
        Optional<Song> song = songRepo.findById(id);
        return song.isPresent() ? song.get() : null;
    }

    public List<Song> getAll() {
        return (List<Song>) songRepo.findAll();
    }

    public Song getOneTrackTitle(String trackTitle) {
        Optional<Song> song = songRepo.findByTrackTitle(trackTitle);
        return song.isPresent() ? song.get() : null;
    }

    // Method To Search Single Song
    public List<Song> getSongsByLetters(String letters) {
        if (letters == null || letters.trim().isEmpty()) {
            return Collections.emptyList();
        }

        Iterable<Song> allSongs = songRepo.findAll();
        List<Song> filteredSongs = new ArrayList<>();

        for (Song song : allSongs) {
            String searchedTrackTitle = song.getTrackTitle();
            if (searchedTrackTitle != null && searchedTrackTitle.contains(letters)) {
                filteredSongs.add(song);
            }
        }

        return filteredSongs;
    }

    public List<Song> getAllSongsMatchingSearchTerm(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return Collections.emptyList();
        }

        List<Song> songs = songRepo.findAll();
        return songs.stream()
                .filter(song -> 
                        (song.getTrackTitle() != null && song.getTrackTitle().contains(searchTerm)) ||
                        (song.getUser() != null && 
                         ((song.getUser().getFirstName() != null && song.getUser().getFirstName().contains(searchTerm)) ||
                          (song.getUser().getLastName() != null && song.getUser().getLastName().contains(searchTerm))))
                )
                .collect(Collectors.toList());
    }

 
    /**
     * Create a new song.
     * @param song The song to create.
     * @return The saved song.
     * @throws IOException If an I/O error occurs.
     */
    @Transactional
    public Song create(Song song) throws IOException {
        if (isTrackSubmitted(song)) {
            throw new SomeAppropriateException("This track has already been submitted by the user.");
        }
        return songRepo.save(song);
    }

    /**
     * Check if a track has already been submitted by the user.
     * @param song The song to check.
     * @return True if the track is already submitted, false otherwise.
     */
    public boolean isTrackSubmitted(Song song) {
        return songRepo.existsByTrackTitleAndUserId(song.getTrackTitle(), song.getUser().getId());
    }

    /**
     * Save a song to the database.
     * @param song The song to save.
     * @return The saved song.
     */
    public Song saveSongToDatabase(Song song) {
        try {
            return songRepo.save(song);
        } catch (Exception e) {
            System.err.println("Error saving the song: " + e.getMessage());
            throw new SomeAppropriateException("Error saving the song.", e);
        }
    }

    /**
     * Update an existing song.
     * @param song The song to update.
     * @return The updated song.
     * @throws SomeAppropriateException If an error occurs during the update.
     */
    @Transactional
    public Song update(@Valid Song song) throws SomeAppropriateException {
        try {
            return saveSongToDatabase(song);
        } catch (SomeAppropriateException e) {
            throw e;
        } catch (Exception e) {
            throw new SomeAppropriateException("Error updating the song.", e);
        }
    }
  
 
    @Transactional
    public Song updateFile(
            @Valid Song song,
            MultipartFile audioData,
            MultipartFile trackImageData,
            BindingResult result) throws SomeAppropriateException {
        try {
//            FileUtils.validateFileNotEmpty(audioData, "audioData", "Please Enter Audio File!");
//            FileUtils.validateFileNotEmpty(trackImageData, "trackImageData", "Track Cover Art Is Empty!");
//
//            // Set file names using FileUtils
//            String audioFileName = FileUtils.cleanFileName(audioData.getOriginalFilename());
//            String trackImageFileName = FileUtils.cleanFileName(trackImageData.getOriginalFilename());
//
//            // Save audio file to disk
//            FileUtils.alternativeSaveFile(audioData, uploadDir + "/TrackAudioData");
//
//            // Save track image file to disk
//            FileUtils.alternativeSaveFile(trackImageData, uploadDir + "/TrackArtImages");
//
//            // Set other properties of the song...
//
//            // Update the song entity with the new information
//            song.setAudioFileName(audioFileName);
//            song.setTrackImageFileName(trackImageFileName);

            // Save the updated song to the database
            return saveSongToDatabase(song);
        } catch (SomeAppropriateException e) {
            throw e;
        } catch (Exception e) {
            throw new SomeAppropriateException("Error saving the song.", e);
        }
    }

    public void delete(Long id) {
        songRepo.deleteById(id);
    }

    public Song getOrCreateSongByTitle(String trackTitle) {
        Optional<Song> song = songRepo.findByTrackTitle(trackTitle);
        return song.orElseGet(() -> {
            Song newSong = new Song();
            newSong.setTrackTitle(trackTitle);
            return songRepo.save(newSong);
        });
    }

    public Song createSongIfNotInDatabase(String trackTitle) {
        Song existingSong = getOrCreateSongByTitle(trackTitle);
        if (existingSong == null) {
            Song newSong = new Song();
            newSong.setTrackTitle(trackTitle);
            return songRepo.save(newSong);
        }
        return existingSong;
    }

}