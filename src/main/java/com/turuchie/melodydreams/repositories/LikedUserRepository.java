package com.turuchie.melodydreams.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turuchie.melodydreams.models.LikedUser;

@Repository
public interface LikedUserRepository extends JpaRepository<LikedUser, Long> {
	List<LikedUser> findAll();
    Optional<LikedUser> findById(Long id);
	Optional<LikedUser> findByUserLikedIdAndLikingUserId(Long userLikedId, Long likingUserId);
 
}