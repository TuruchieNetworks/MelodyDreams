package com.turuchie.melodydreams.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turuchie.melodydreams.models.FavoriteAlbum;
import com.turuchie.melodydreams.repositories.FavoriteAlbumRepository;

@Service
public class FavoriteAlbumService {
	
	@Autowired
	private FavoriteAlbumRepository favoriteAlbumRepo;
	
	public FavoriteAlbumService(FavoriteAlbumRepository favoriteAlbumRepo) {
		this.favoriteAlbumRepo = favoriteAlbumRepo;
	}
	
	public FavoriteAlbum getOne(Long id) {
		Optional<FavoriteAlbum> favoriteAlbum = favoriteAlbumRepo.findById(id);
		return favoriteAlbum.isPresent() ? favoriteAlbum.get() : null;
	}

	public FavoriteAlbum getOneFavoriteAlbumName(String albumName) {
		Optional<FavoriteAlbum> favoriteAlbum = favoriteAlbumRepo.findByAlbumName(albumName);
		return favoriteAlbum.isPresent() ? favoriteAlbum.get() : null;
	}

	public List<FavoriteAlbum> getAll() {
		return (List<FavoriteAlbum>) favoriteAlbumRepo.findAll();
	}

    // Method To Search Single Song
	public List<FavoriteAlbum> getFavoriteAlbumsByLetters(String letters) {
	    List<FavoriteAlbum> filteredFavoriteAlbums = new ArrayList<>();
	    Iterable<FavoriteAlbum> allFavoriteAlbums = favoriteAlbumRepo.findAll();    

	    for (FavoriteAlbum favoriteAlbum : allFavoriteAlbums) {
	        String searchedFavoriteAlbumName = favoriteAlbum.getAlbumName();
	        if (searchedFavoriteAlbumName.contains(letters)) {
	            filteredFavoriteAlbums.add(favoriteAlbum);
	        }
	    }

	    return filteredFavoriteAlbums;
	}

	public FavoriteAlbum create(FavoriteAlbum favoriteAlbum) {
		return favoriteAlbumRepo.save(favoriteAlbum);
	}

	public FavoriteAlbum update(FavoriteAlbum favoriteAlbum) {
		return favoriteAlbumRepo.save(favoriteAlbum);
	}

	public void delete(Long id) {
		favoriteAlbumRepo.deleteById(id);
	}

//	************************Validation logic for preventing multiple entries*****************************
	public boolean isRelationshipExists(Long favoriteAlbumId, Long followingUserId) {
        //repository or data access layer to check if the relationship already exists
        Optional<FavoriteAlbum> existingRelationship = favoriteAlbumRepo.findByAlbumFavoritedIdAndUserFavoritingAlbumId(favoriteAlbumId, followingUserId);

        // Return true if the relationship already exists, false otherwise
        return existingRelationship.isPresent();
	}
}