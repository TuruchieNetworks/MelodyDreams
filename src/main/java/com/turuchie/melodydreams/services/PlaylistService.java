package com.turuchie.melodydreams.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.turuchie.melodydreams.models.Playlist;
import com.turuchie.melodydreams.repositories.PlaylistRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class PlaylistService {
    @Value("${file.upload-dir}") // Specify the file upload directory in application.properties
    private String uploadDir;

    @Autowired
    private PlaylistRepository playlistRepo;

    public PlaylistService(PlaylistRepository playlistRepo) {
        this.playlistRepo = playlistRepo;
    }

    public Playlist getOne(Long id) {
        Optional<Playlist> Playlist = playlistRepo.findById(id);
        return Playlist.isPresent() ? Playlist.get() : null;
    }

    public Playlist getOneTitle(String Title) {
        Optional<Playlist> Playlist = playlistRepo.findByTitle(Title);
        return Playlist.isPresent() ? Playlist.get() : null;
    }

    public List<Playlist> getAll() {
        return (List<Playlist>) playlistRepo.findAll();
    }

    // Method To Search Single Song
	public List<Playlist> getPlaylistsByLetters(String letters) {
	    List<Playlist> filteredPlaylists = new ArrayList<>();
	    Iterable<Playlist> allPlaylists = playlistRepo.findAll();    

	    for (Playlist playlist : allPlaylists) {
	        String searchedPlaylistTitle = playlist.getTitle();
	        if (searchedPlaylistTitle.contains(letters)) {
	            filteredPlaylists.add(playlist);
	        }
	    }

	    return filteredPlaylists;
	}

    /**
     * Create a new Playlist.
     * @param Playlist The Playlist to create.
     * @return The saved Playlist.
     * @throws IOException If an I/O error occurs.
     */
    @Transactional
    public Playlist create(Playlist Playlist) throws IOException {
        return playlistRepo.save(Playlist);
    }

    /**
     * Check if a track has already been submitted by the user.
     * @param Playlist The Playlist to check.
     * @return True if the track is already submitted, false otherwise.
     */
    public boolean isTrackSubmitted(Playlist Playlist) {
        return playlistRepo.existsByTitleAndUserId(Playlist.getTitle(), Playlist.getUser().getId());
    }

    /**
     * Save a Playlist to the database.
     * @param Playlist The Playlist to save.
     * @return The saved Playlist.
     */
    public Playlist savePlaylistToDatabase(Playlist Playlist) {
        try {
            return playlistRepo.save(Playlist);
        } catch (Exception e) {
            System.err.println("Error saving the Playlist: " + e.getMessage());
            throw new SomeAppropriateException("Error saving the Playlist.", e);
        }
    }

    /**
     * Update an existing Playlist.
     * @param Playlist The Playlist to update.
     * @return The updated Playlist.
     * @throws SomeAppropriateException If an error occurs during the update.
     */
    @Transactional
    public Playlist update(@Valid Playlist Playlist) throws SomeAppropriateException {
        try {
            return savePlaylistToDatabase(Playlist);
        } catch (SomeAppropriateException e) {
            throw e;
        } catch (Exception e) {
            throw new SomeAppropriateException("Error updating the Playlist.", e);
        }
    }
  
 
    @Transactional
    public Playlist updateFile(
            @Valid Playlist Playlist,
            MultipartFile audioData,
            MultipartFile trackImageData,
            BindingResult result) throws SomeAppropriateException {
        try {
            // Save the updated Playlist to the database
            return savePlaylistToDatabase(Playlist);
        } catch (SomeAppropriateException e) {
            throw e;
        } catch (Exception e) {
            throw new SomeAppropriateException("Error saving the Playlist.", e);
        }
    }

    public void delete(Long id) {
        playlistRepo.deleteById(id);
    }

    public Playlist getOrCreatePlaylistByTitle(String Title) {
        Optional<Playlist> Playlist = playlistRepo.findByTitle(Title);
        return Playlist.orElseGet(() -> {
            Playlist newPlaylist = new Playlist();
            newPlaylist.setTitle(Title);
            return playlistRepo.save(newPlaylist);
        });
    }

    public Playlist createPlaylistIfNotInDatabase(String Title) {
        Playlist existingPlaylist = getOrCreatePlaylistByTitle(Title);
        if (existingPlaylist == null) {
            Playlist newPlaylist = new Playlist();
            newPlaylist.setTitle(Title);
            return playlistRepo.save(newPlaylist);
        }
        return existingPlaylist;
    }

}