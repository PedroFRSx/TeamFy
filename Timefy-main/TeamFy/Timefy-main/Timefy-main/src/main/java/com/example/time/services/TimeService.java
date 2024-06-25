package com.example.time.services;

import com.example.time.models.Jogador;
import com.example.time.models.Time;
import com.example.time.repositories.TimeRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TimeService {

    private final TimeRepository timeRepository;

    public TimeService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    public List<Time> findAll() {
        return timeRepository.findAll();
    }

    public Time findById(Long id) throws Exception {
        Optional<Time> time = timeRepository.findById(id);
        if (time.isEmpty()) {
            throw new Exception("Time não encontrado com id: " + id);
        }
        return time.get();
    }

    public Time save(Time time) throws Exception {
        validateTime(time);
        return timeRepository.save(time);
    }

    private void validateTime(Time time) throws Exception {
        validateNome(time.getNome(), time.getId());
        validateDataCriacao(time.getDataCriacao());
        validateCidade(time.getCidade());
        validatePais(time.getPais());
        validateNumeroTorcedores(time.getNumeroTorcedores());
        validateTecnico(time.getTecnico());
        validateDinheiroCaixa(time.getDinheiroCaixa());
        validateNomeEstadio(time.getNomeEstadio());
    }

    private void validateNome(String nome, Long id) throws Exception {
        if (nome == null || nome.length() < 3) {
            throw new Exception("O nome do time deve ter ao menos 3 caracteres");
        }
        if (timeRepository.existsByNome(nome)) {
            Time timeExistente = timeRepository.findByNome(nome);
            if (!timeExistente.getId().equals(id)) {
                throw new Exception("O nome do time deve ser único");
            }
        }
    }

    private void validateDataCriacao(LocalDate dataCriacao) throws Exception {
        if (dataCriacao == null || dataCriacao.isAfter(LocalDate.now())) {
            throw new Exception("A data de criação não pode ser posterior à data atual");
        }
    }

    private void validateCidade(String cidade) throws Exception {
        if (cidade == null || cidade.length() < 3) {
            throw new Exception("A cidade do time deve ter pelo menos 3 caracteres");
        }
    }

    private void validatePais(String pais) throws Exception {
        if (pais == null || !pais.matches("Argentina|Brasil|Chile|Uruguai")) {
            throw new Exception("O país deve ser Argentina, Brasil, Chile ou Uruguai.");
        }
    }

    private void validateNumeroTorcedores(int numeroTorcedores) throws Exception {
        if (numeroTorcedores <= 0) {
            throw new Exception("O número de torcedores deve ser maior que zero");
        }
    }

    private void validateTecnico(String tecnico) throws Exception {
        if (tecnico == null || tecnico.isBlank()) {
            throw new Exception("O técnico é obrigatório");
        }
    }

    private void validateDinheiroCaixa(double dinheiroCaixa) throws Exception {
        if (dinheiroCaixa < 0.0 || dinheiroCaixa > 999999999.99) {
            throw new Exception("O dinheiro em caixa deve estar entre 0.0 e 999.999.999,99");
        }
    }

    private void validateNomeEstadio(String nomeEstadio) throws Exception {
        if (nomeEstadio == null || nomeEstadio.isBlank()) {
            throw new Exception("O nome do estádio é obrigatório");
        }
    }

    public Time delete(Long id) throws Exception {
        Optional<Time> time = timeRepository.findById(id);
        if (time.isEmpty()) {
            throw new Exception("Time não encontrado com id: " + id);
        }
        timeRepository.delete(time.get());
        return time.get();
    }

    public long count() {
        return timeRepository.count();
    }
}