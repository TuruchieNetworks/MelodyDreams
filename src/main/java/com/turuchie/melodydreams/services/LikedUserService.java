package com.turuchie.melodydreams.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turuchie.melodydreams.models.LikedUser;
import com.turuchie.melodydreams.repositories.LikedUserRepository;

@Service
public class LikedUserService {
	
	@Autowired
	private LikedUserRepository likedUserRepo;
	
	public LikedUserService(LikedUserRepository likedUserRepo) {
		this.likedUserRepo = likedUserRepo;
	}
	
	public LikedUser getOne(Long id) {
		Optional<LikedUser> likedUser = likedUserRepo.findById(id);
		return likedUser.isPresent() ? likedUser.get() : null;
	}

	public List<LikedUser> getAll() {
		return (List<LikedUser>) likedUserRepo.findAll();
	}

	public LikedUser create(LikedUser likedUser) {
		return likedUserRepo.save(likedUser);
	}

	public LikedUser update(LikedUser likedUser) {
		return likedUserRepo.save(likedUser);
	}

	public void delete(Long id) {
		likedUserRepo.deleteById(id);
	}

//	************************Validation logic for preventing multiple entries*****************************
	public boolean isRelationshipExists(Long userLikedId, Long likingUserId) {
        //repository or data access layer to check if the relationship already exists
        Optional<LikedUser> existingRelationship = likedUserRepo.findByUserLikedIdAndLikingUserId(userLikedId, likingUserId);

        // Return true if the relationship already exists, false otherwise
        return existingRelationship.isPresent();
	}
   
//	public boolean isExists(Long userLikedId, Long likingUserId) {
//        return likedUserRepo.existsByUserLikedIdAndUserLikingUserId(userLikedId, likingUserId);
//    }
}