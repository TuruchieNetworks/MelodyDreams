package com.turuchie.melodydreams.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turuchie.melodydreams.models.Playlist;
import com.turuchie.melodydreams.models.User;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
	List<Playlist> findAll();
	List<Playlist> findByUser(User user);
    Optional<Playlist> findById(Long id);
    Optional<Playlist> findByTitle(String title);
	boolean existsByTitleAndUserId(String title, Long id);
}