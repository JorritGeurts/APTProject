package com.example.partijlidservice.repository;

import com.example.partijlidservice.model.Partijlid;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface PartijlidRepository extends JpaRepository<Partijlid, Long> {
    Optional<Partijlid> findByNaam(String naam);
}
