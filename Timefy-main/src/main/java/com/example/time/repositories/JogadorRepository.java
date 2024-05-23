package com.example.time.repositories;

import com.example.time.models.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    Jogador findByNome(String nome);
    boolean existsByNome(String nome);
    List<Jogador> findByDataNascimento(LocalDate dataNascimento);
}