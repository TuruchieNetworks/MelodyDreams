package com.turuchie.melodydreams.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turuchie.melodydreams.models.FavoritePlaylist;

@Repository
public interface FavoritePlaylistRepository extends JpaRepository<FavoritePlaylist, Long> {
	List<FavoritePlaylist> findAll();
    Optional<FavoritePlaylist> findById(Long id); 
	Optional<FavoritePlaylist> findByTitle(String title);
	Optional<FavoritePlaylist> findByFavoritedPlaylistIdAndFavoritedPlaylistUserId(Long favoritePlaylistId, Long userId);
}