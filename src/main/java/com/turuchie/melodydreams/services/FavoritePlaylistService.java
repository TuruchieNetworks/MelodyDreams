package com.turuchie.melodydreams.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turuchie.melodydreams.models.FavoritePlaylist;
import com.turuchie.melodydreams.repositories.FavoritePlaylistRepository;

@Service
public class FavoritePlaylistService {
	
	@Autowired
	private FavoritePlaylistRepository favoritePlaylistRepo;
	
	public FavoritePlaylistService(FavoritePlaylistRepository favoritePlaylistRepo) {
		this.favoritePlaylistRepo = favoritePlaylistRepo;
	}
	
	public FavoritePlaylist getOne(Long id) {
		Optional<FavoritePlaylist> favoritePlaylist = favoritePlaylistRepo.findById(id);
		return favoritePlaylist.isPresent() ? favoritePlaylist.get() : null;
	}

	public FavoritePlaylist getOneFavoritePlaylistName(String title) {
		Optional<FavoritePlaylist> favoritePlaylist = favoritePlaylistRepo.findByTitle(title);
		return favoritePlaylist.isPresent() ? favoritePlaylist.get() : null;
	}

	public List<FavoritePlaylist> getAll() {
		return (List<FavoritePlaylist>) favoritePlaylistRepo.findAll();
	}

    // Method To Search Single Song
	public List<FavoritePlaylist> getFavoritePlaylistsByLetters(String letters) {
	    List<FavoritePlaylist> filteredFavoritePlaylists = new ArrayList<>();
	    Iterable<FavoritePlaylist> allFavoritePlaylists = favoritePlaylistRepo.findAll();    

	    for (FavoritePlaylist favoritePlaylist : allFavoritePlaylists) {
	        String searchedFavoritePlaylistName = favoritePlaylist.getTitle();
	        if (searchedFavoritePlaylistName.contains(letters)) {
	            filteredFavoritePlaylists.add(favoritePlaylist);
	        }
	    }

	    return filteredFavoritePlaylists;
	}

	public FavoritePlaylist create(FavoritePlaylist favoritePlaylist) {
		return favoritePlaylistRepo.save(favoritePlaylist);
	}

	public FavoritePlaylist update(FavoritePlaylist favoritePlaylist) {
		return favoritePlaylistRepo.save(favoritePlaylist);
	}

	public void delete(Long id) {
		favoritePlaylistRepo.deleteById(id);
	}

//	************************Validation logic for preventing multiple entries*****************************
	public boolean isRelationshipExists(Long favoritePlaylistId, Long userId) {
        //repository or data access layer to check if the relationship already exists
        Optional<FavoritePlaylist> existingRelationship = favoritePlaylistRepo.findByFavoritedPlaylistIdAndFavoritedPlaylistUserId(favoritePlaylistId, userId);

        // Return true if the relationship already exists, false otherwise
        return existingRelationship.isPresent();
	}
}