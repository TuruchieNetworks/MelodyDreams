package com.turuchie.melodydreams.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turuchie.melodydreams.models.PlaylistSongs;

@Repository
public interface PlaylistSongsRepository extends JpaRepository<PlaylistSongs, Long> {
	List<PlaylistSongs> findAll();
    Optional<PlaylistSongs> findById(Long id);
}