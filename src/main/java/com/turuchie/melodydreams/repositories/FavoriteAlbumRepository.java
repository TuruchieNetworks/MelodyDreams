package com.turuchie.melodydreams.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turuchie.melodydreams.models.FavoriteAlbum;

@Repository
public interface FavoriteAlbumRepository extends JpaRepository<FavoriteAlbum, Long> {
	List<FavoriteAlbum> findAll();
    Optional<FavoriteAlbum> findById(Long id); 
    Optional<FavoriteAlbum> findByAlbumName(String albumName);
	Optional<FavoriteAlbum> findByAlbumFavoritedIdAndUserFavoritingAlbumId(Long albumId, Long userId);
}