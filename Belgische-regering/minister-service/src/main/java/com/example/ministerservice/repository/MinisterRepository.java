package com.example.ministerservice.repository;

import com.example.ministerservice.model.Minister;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface MinisterRepository extends JpaRepository<Minister, Long> {
    Optional<Minister> findByNaam(String naam);
}
