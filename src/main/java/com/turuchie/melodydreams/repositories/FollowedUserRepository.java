package com.turuchie.melodydreams.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turuchie.melodydreams.models.FollowedUser;

@Repository
public interface FollowedUserRepository extends JpaRepository<FollowedUser, Long> {
	List<FollowedUser> findAll();
    Optional<FollowedUser> findById(Long id);
    Optional<FollowedUser> findByFollowingUserName(String followingUser);
	Optional<FollowedUser> findByFollowedUserIdAndFollowingUserId(Long followedUserId, Long followingUserId);
}