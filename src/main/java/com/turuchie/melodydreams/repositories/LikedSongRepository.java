package com.turuchie.melodydreams.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turuchie.melodydreams.models.LikedSong;

@Repository
public interface LikedSongRepository extends JpaRepository<LikedSong, Long> {
	List<LikedSong> findAll();
    Optional<LikedSong> findById(Long id);
	Optional<LikedSong> findBySongToLikeIdAndLikingUserId(Long songToLikeId, Long likingUserId);
}