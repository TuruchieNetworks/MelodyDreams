package com.turuchie.melodydreams.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.turuchie.melodydreams.models.PlaylistSongs;
import com.turuchie.melodydreams.repositories.PlaylistSongsRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class PlaylistSongsService {

    @Value("${file.upload-dir}") // Specify the file upload directory in application.properties
    private String uploadDir;

    @Autowired
    private PlaylistSongsRepository PlaylistSongsRepo;

    public PlaylistSongsService(PlaylistSongsRepository PlaylistSongsRepo) {
        this.PlaylistSongsRepo = PlaylistSongsRepo;
    }

    public PlaylistSongs getOne(Long id) {
        Optional<PlaylistSongs> PlaylistSongs = PlaylistSongsRepo.findById(id);
        return PlaylistSongs.isPresent() ? PlaylistSongs.get() : null;
    }

    public List<PlaylistSongs> getAll() {
        return (List<PlaylistSongs>) PlaylistSongsRepo.findAll();
    }
 
    /**
     * Create a new PlaylistSongs.
     * @param PlaylistSongs The PlaylistSongs to create.
     * @return The saved PlaylistSongs.
     * @throws IOException If an I/O error occurs.
     */
    @Transactional
    public PlaylistSongs create(PlaylistSongs PlaylistSongs) throws IOException {
        return PlaylistSongsRepo.save(PlaylistSongs);
    }

    /**
     * Save a PlaylistSongs to the database.
     * @param PlaylistSongs The PlaylistSongs to save.
     * @return The saved PlaylistSongs.
     */
    public PlaylistSongs savePlaylistSongsToDatabase(PlaylistSongs PlaylistSongs) {
        try {
            return PlaylistSongsRepo.save(PlaylistSongs);
        } catch (Exception e) {
            System.err.println("Error saving the PlaylistSongs: " + e.getMessage());
            throw new SomeAppropriateException("Error saving the PlaylistSongs.", e);
        }
    }

    /**
     * Update an existing PlaylistSongs.
     * @param PlaylistSongs The PlaylistSongs to update.
     * @return The updated PlaylistSongs.
     * @throws SomeAppropriateException If an error occurs during the update.
     */
    @Transactional
    public PlaylistSongs update(@Valid PlaylistSongs PlaylistSongs) throws SomeAppropriateException {
        try {
            return savePlaylistSongsToDatabase(PlaylistSongs);
        } catch (SomeAppropriateException e) {
            throw e;
        } catch (Exception e) {
            throw new SomeAppropriateException("Error updating the PlaylistSongs.", e);
        }
    }
  
 
    @Transactional
    public PlaylistSongs updateFile(
            @Valid PlaylistSongs PlaylistSongs,
            MultipartFile audioData,
            MultipartFile trackImageData,
            BindingResult result) throws SomeAppropriateException {
        try {
            // Save the updated PlaylistSongs to the database
            return savePlaylistSongsToDatabase(PlaylistSongs);
        } catch (SomeAppropriateException e) {
            throw e;
        } catch (Exception e) {
            throw new SomeAppropriateException("Error saving the PlaylistSongs.", e);
        }
    }

    public void delete(Long id) {
        PlaylistSongsRepo.deleteById(id);
    }

}