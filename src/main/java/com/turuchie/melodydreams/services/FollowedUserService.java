package com.turuchie.melodydreams.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turuchie.melodydreams.models.FollowedUser;
import com.turuchie.melodydreams.repositories.FollowedUserRepository;

@Service
public class FollowedUserService {
	
	@Autowired
	private FollowedUserRepository followedUserRepo;
	
	public FollowedUserService(FollowedUserRepository followedUserRepo) {
		this.followedUserRepo = followedUserRepo;
	}
	
	public FollowedUser getOne(Long id) {
		Optional<FollowedUser> followedUser = followedUserRepo.findById(id);
		return followedUser.isPresent() ? followedUser.get() : null;
	}

	public FollowedUser getOneFollowedUserName(String followingUserName) {
		Optional<FollowedUser> followedUser = followedUserRepo.findByFollowingUserName(followingUserName);
		return followedUser.isPresent() ? followedUser.get() : null;
	}

	public List<FollowedUser> getAll() {
		return (List<FollowedUser>) followedUserRepo.findAll();
	}

	public FollowedUser create(FollowedUser followedUser) {
		return followedUserRepo.save(followedUser);
	}

	public FollowedUser update(FollowedUser followedUser) {
		return followedUserRepo.save(followedUser);
	}

	public void delete(Long id) {
		followedUserRepo.deleteById(id);
	}

//	************************Validation logic for preventing multiple entries*****************************
	public boolean isRelationshipExists(Long followedUserId, Long followingUserId) {
        //repository or data access layer to check if the relationship already exists
        Optional<FollowedUser> existingRelationship = followedUserRepo.findByFollowedUserIdAndFollowingUserId(followedUserId, followingUserId);

        // Return true if the relationship already exists, false otherwise
        return existingRelationship.isPresent();
	}
}