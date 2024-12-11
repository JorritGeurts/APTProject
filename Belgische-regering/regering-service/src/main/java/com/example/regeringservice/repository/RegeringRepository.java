package com.example.regeringservice.repository;

import com.example.regeringservice.model.Regering;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegeringRepository extends MongoRepository<Regering, String> {
}