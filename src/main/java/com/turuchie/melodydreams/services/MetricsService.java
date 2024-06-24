package com.turuchie.melodydreams.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turuchie.melodydreams.models.Metrics;
import com.turuchie.melodydreams.repositories.MetricsRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class MetricsService {
    @Autowired
    private MetricsRepository MetricsRepo;

    public MetricsService(MetricsRepository MetricsRepo) {
        this.MetricsRepo = MetricsRepo;
    }

    public Metrics getOne(Long id) {
        Optional<Metrics> Metrics = MetricsRepo.findById(id);
        return Metrics.isPresent() ? Metrics.get() : null;
    }

    public List<Metrics> getAll() {
        return (List<Metrics>) MetricsRepo.findAll();
    }

    @Transactional
    public Metrics create(Metrics Metrics) throws IOException {   
        // Save the Metrics to the database after both files are saved
        return MetricsRepo.save(Metrics);
    } 

    public Metrics saveMetricsToDatabase(Metrics Metrics) {
        try {
            return MetricsRepo.save(Metrics);
        } catch (Exception e) {
            System.err.println("Error saving the Metrics: " + e.getMessage());
            throw new SomeAppropriateException("Error saving the Metrics.", e);
        }
    }

    @Transactional
    public Metrics update(
            @Valid Metrics Metrics) throws SomeAppropriateException {
        try {
        	// Save the updated Metrics to the database
            return saveMetricsToDatabase(Metrics);
        } catch (SomeAppropriateException e) {
            throw e;
        } catch (Exception e) {
            throw new SomeAppropriateException("Error saving the Metrics.", e);
        }
    }    

    public void delete(Long id) {
        MetricsRepo.deleteById(id);
    }

}