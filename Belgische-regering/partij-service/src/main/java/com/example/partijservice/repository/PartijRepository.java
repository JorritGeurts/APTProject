package com.example.partijservice.repository;

import com.example.partijservice.model.Partij;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PartijRepository extends MongoRepository<Partij, String> {
    Optional<Partij> findByNaam(String naam);
}
