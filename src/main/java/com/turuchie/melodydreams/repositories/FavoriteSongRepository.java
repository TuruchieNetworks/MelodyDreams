package com.turuchie.melodydreams.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turuchie.melodydreams.models.FavoriteSong;

@Repository
public interface FavoriteSongRepository extends JpaRepository<FavoriteSong, Long> {
	List<FavoriteSong> findAll();
    Optional<FavoriteSong> findById(Long id);
	Optional<FavoriteSong> findByFavoritedSongIdAndUserId(Long songId, Long userId);
}