package com.turuchie.melodydreams.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.turuchie.melodydreams.models.LikedSong;
import com.turuchie.melodydreams.repositories.LikedSongRepository;

@Service
public class LikedSongService {
	
	@Autowired
	private LikedSongRepository likedSongRepo;
	
	public LikedSongService(LikedSongRepository likedSongRepo) {
		this.likedSongRepo = likedSongRepo;
	}
	
	public LikedSong getOne(Long id) {
		Optional<LikedSong> likedSong = likedSongRepo.findById(id);
		return likedSong.isPresent() ? likedSong.get() : null;
	}

	public List<LikedSong> getAll() {
		return (List<LikedSong>) likedSongRepo.findAll();
	}

    // Method To Search Single Song
	public List<LikedSong> getLikedSongsByLetters(String letters) {
	    List<LikedSong> filteredLikedSongs = new ArrayList<>();
	    Iterable<LikedSong> allLikedSongs = likedSongRepo.findAll();    

	    for (LikedSong likedSong : allLikedSongs) {
	        String searchedLikedSongTitle = likedSong.getSong().getTrackTitle();
	        if (searchedLikedSongTitle.contains(letters)) {
	            filteredLikedSongs.add(likedSong);
	        }
	    }

	    return filteredLikedSongs;
	}

    @Async
    public CompletableFuture<LikedSong> create(LikedSong likedSong) {
        LikedSong savedLikedSong = likedSongRepo.save(likedSong);
        return CompletableFuture.completedFuture(savedLikedSong);
    }

    @Async
    public CompletableFuture<LikedSong> update(LikedSong likedSong) {
        LikedSong updatedLikedSong = likedSongRepo.save(likedSong);
        return CompletableFuture.completedFuture(updatedLikedSong);
    }

    @Async
    public CompletableFuture<Void> delete(Long id) {
        likedSongRepo.deleteById(id);
        return CompletableFuture.completedFuture(null);
    }

//    public LikedSong create(LikedSong likedSong) {
//		return likedSongRepo.save(likedSong);
//	}
//
//	public LikedSong update(LikedSong likedSong) {
//		return likedSongRepo.save(likedSong);
//	}
//
//	public void delete(Long id) {
//		likedSongRepo.deleteById(id);
//	}

//	************************Validation logic for preventing multiple entries*****************************
	public boolean isRelationshipExists(Long songToLikeId, Long likingUserId) {
        //repository or data access layer to check if the relationship already exists
        Optional<LikedSong> existingRelationship = likedSongRepo.findBySongToLikeIdAndLikingUserId(songToLikeId, likingUserId);

        // Return true if the relationship already exists, false otherwise
        return existingRelationship.isPresent();
	}
}