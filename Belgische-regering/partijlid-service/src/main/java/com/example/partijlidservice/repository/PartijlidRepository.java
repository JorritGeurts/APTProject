package com.example.partijlidservice.repository;

import com.example.partijlidservice.model.Partijlid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartijlidRepository extends JpaRepository<Partijlid, Long> {
}
