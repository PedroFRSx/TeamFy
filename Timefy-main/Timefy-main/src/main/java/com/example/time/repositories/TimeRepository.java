package com.example.time.repositories;

import com.example.time.models.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository extends JpaRepository<Time, Long> {
    boolean existsByNome(String nome);
    Time findByNome(String nome);
}