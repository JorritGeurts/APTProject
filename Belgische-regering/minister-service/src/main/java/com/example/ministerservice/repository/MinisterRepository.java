package com.example.ministerservice.repository;

import com.example.ministerservice.model.Minister;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface MinisterRepository extends JpaRepository<Minister, Long> {
}
