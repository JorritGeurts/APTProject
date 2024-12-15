package com.example.regeringservice.repository;

import com.example.regeringservice.model.Regering;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RegeringRepository extends MongoRepository<Regering, String> {
    Optional<Regering> findByNaam(String naam);
}