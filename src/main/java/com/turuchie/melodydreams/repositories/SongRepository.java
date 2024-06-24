package com.turuchie.melodydreams.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turuchie.melodydreams.models.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
	List<Song> findAll();
    Optional<Song> findById(Long id);
    Optional<Song> findByTrackTitle(String trackTitle);
	// Song save(Song song, MultipartFile audioData, MultipartFile trackImageData);
	boolean existsByTrackTitleAndUserId(String trackTitle, Long id);
}