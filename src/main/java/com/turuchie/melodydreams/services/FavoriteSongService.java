package com.turuchie.melodydreams.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turuchie.melodydreams.models.FavoriteSong;
import com.turuchie.melodydreams.repositories.FavoriteSongRepository;

@Service
public class FavoriteSongService {	
	@Autowired
	private FavoriteSongRepository favoriteSongRepo;
	
	public FavoriteSongService(FavoriteSongRepository favoriteSongRepo) {
		this.favoriteSongRepo = favoriteSongRepo;
	}
	
	public FavoriteSong getOne(Long id) {
		Optional<FavoriteSong> favoriteSong = favoriteSongRepo.findById(id);
		return favoriteSong.isPresent() ? favoriteSong.get() : null;
	}

	public List<FavoriteSong> getAll() {
		return (List<FavoriteSong>) favoriteSongRepo.findAll();
	}

    // Method To Search Single Song
	public List<FavoriteSong> getFavoriteSongsByLetters(String letters) {
	    List<FavoriteSong> filteredFavoriteSongs = new ArrayList<>();
	    Iterable<FavoriteSong> allFavoriteSongs = favoriteSongRepo.findAll();    

	    for (FavoriteSong favoriteSong : allFavoriteSongs) {
	        String searchedFavoriteSongTitle = favoriteSong.getSong().getTrackTitle();
	        if (searchedFavoriteSongTitle.contains(letters)) {
	            filteredFavoriteSongs.add(favoriteSong);
	        }
	    }
	    return filteredFavoriteSongs;
	}

	public FavoriteSong create(FavoriteSong favoriteSong) {
		return favoriteSongRepo.save(favoriteSong);
	}

	public FavoriteSong update(FavoriteSong favoriteSong) {
		return favoriteSongRepo.save(favoriteSong);
	}

	public void delete(Long id) {
		favoriteSongRepo.deleteById(id);
	}

//	************************Validation logic for preventing multiple entries*****************************
	public boolean isRelationshipExists(Long favoritedSongId, Long userId) {
        //repository or data access layer to check if the relationship already exists
        Optional<FavoriteSong> existingRelationship = favoriteSongRepo.findByFavoritedSongIdAndUserId(favoritedSongId, userId);

        // Return true if the relationship already exists, false otherwise
        return existingRelationship.isPresent();
	}

    public void createIfNotExists(FavoriteSong newFavoriteSong) {
        Long userId = newFavoriteSong.getUser().getId();
        Long songId = newFavoriteSong.getFavoritedSongId();

        if (isRelationshipExists(userId, songId)) {
            throw new RuntimeException("Favorite song relationship already exists");
        }

        favoriteSongRepo.save(newFavoriteSong);
    }
}