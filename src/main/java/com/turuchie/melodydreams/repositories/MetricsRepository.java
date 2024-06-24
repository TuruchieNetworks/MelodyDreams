package com.turuchie.melodydreams.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turuchie.melodydreams.models.Metrics;

@Repository
public interface MetricsRepository extends JpaRepository<Metrics, Long> {
	List<Metrics> findAll();
    Optional<Metrics> findById(Long id);
}