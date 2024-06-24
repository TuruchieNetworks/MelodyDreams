package com.turuchie.melodydreams.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	public LikedSong create(LikedSong likedSong) {
		return likedSongRepo.save(likedSong);
	}

	public LikedSong update(LikedSong likedSong) {
		return likedSongRepo.save(likedSong);
	}

	public void delete(Long id) {
		likedSongRepo.deleteById(id);
	}

//	************************Validation logic for preventing multiple entries*****************************
	public boolean isRelationshipExists(Long songToLikeId, Long likingUserId) {
        //repository or data access layer to check if the relationship already exists
        Optional<LikedSong> existingRelationship = likedSongRepo.findBySongToLikeIdAndLikingUserId(songToLikeId, likingUserId);

        // Return true if the relationship already exists, false otherwise
        return existingRelationship.isPresent();
	}
}