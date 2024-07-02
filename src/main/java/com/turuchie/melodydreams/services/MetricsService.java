package com.turuchie.melodydreams.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.turuchie.melodydreams.models.Metrics;
import com.turuchie.melodydreams.repositories.MetricsRepository;

@Service
public class MetricsService {
    @Autowired
    private MetricsRepository metricsRepo;

    public MetricsService(MetricsRepository metricsRepo) {
        this.metricsRepo = metricsRepo;
    }

    public Metrics getOne(Long id) {
        Optional<Metrics> Metrics = metricsRepo.findById(id);
        return Metrics.isPresent() ? Metrics.get() : null;
    }

    public List<Metrics> getAll() {
        return (List<Metrics>) metricsRepo.findAll();
    }

    public Metrics saveMetricsToDatabase(Metrics Metrics) {
        try {
            return metricsRepo.save(Metrics);
        } catch (Exception e) {
            System.err.println("Error saving the Metrics: " + e.getMessage());
            throw new SomeAppropriateException("Error saving the Metrics.", e);
        }
    }

    @Async
    public CompletableFuture<Metrics> create(Metrics metrics) throws IOException {
        Metrics savedMetrics = metricsRepo.save(metrics);
        return CompletableFuture.completedFuture(savedMetrics);
    }

    @Async
    public CompletableFuture<Metrics> update(Metrics metrics) {
        Metrics updatedMetrics = metricsRepo.save(metrics);
        return CompletableFuture.completedFuture(updatedMetrics);
    }

    @Async
    public CompletableFuture<Void> delete(Long id) {
        metricsRepo.deleteById(id);
        return CompletableFuture.completedFuture(null);
    }

//    @Transactional
//    public Metrics create(Metrics metrics) throws IOException {   
//        // Save the Metrics to the database after both files are saved
//        return metricsRepo.save(metrics);
//    } 
    
//    @Transactional
//    public Metrics update(
//            @Valid Metrics Metrics) throws SomeAppropriateException {
//        try {
//        	// Save the updated Metrics to the database
//            return saveMetricsToDatabase(Metrics);
//        } catch (SomeAppropriateException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new SomeAppropriateException("Error saving the Metrics.", e);
//        }
//    }    
//
//    public void delete(Long id) {
//        metricsRepo.deleteById(id);
//    }

}