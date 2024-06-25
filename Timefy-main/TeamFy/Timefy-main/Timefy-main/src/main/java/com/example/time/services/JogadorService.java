package com.example.time.services;
import com.example.time.models.Jogador;
import com.example.time.repositories.JogadorRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class JogadorService {

    private final JogadorRepository jogadorRepository;

    public JogadorService(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }
    public List<Jogador> findByDataNascimento(LocalDate dataNascimento) {
        return jogadorRepository.findByDataNascimento(dataNascimento);
    }

    public List<Jogador> findAll() {
        return jogadorRepository.findAll();
    }

    public Jogador findById(Long id) throws Exception {
        Optional<Jogador> jogador = jogadorRepository.findById(id);
        if (jogador.isEmpty()) {
            throw new Exception("Jogador não encontrado com id: " + id);
        }
        return jogador.get();
    }

    public Jogador save(Jogador jogador) throws Exception {
        validateJogador(jogador);
        return jogadorRepository.save(jogador);
    }

    private void validateJogador(Jogador jogador) throws Exception {
        validateNome(jogador.getNome(), jogador.getId());
        validateSalario(jogador.getSalario());
        validatePosicao(jogador.getPosicao());
        validateDataNascimento(jogador.getDataNascimento());
        validateVelocidade(jogador.getVelocidade());
        validateForca(jogador.getForca());
        validateAltura(jogador.getAltura());
        validatePeso(jogador.getPeso());
        validateDesempenho(jogador.getDesempenho());
    }

    private void validateNome(String nome, Long id) throws Exception {
        if (nome == null || nome.length() < 5) {
            throw new Exception("O nome do jogador deve ter pelo menos 5 caracteres");
        }
        if (jogadorRepository.existsByNome(nome)) {
            Jogador jogadorExistente = jogadorRepository.findByNome(nome);
            if (!jogadorExistente.getId().equals(id)) {
                throw new Exception("O nome do jogador deve ser único");
            }
        }
    }

    private void validateSalario(double salario) throws Exception {
        if (salario <= 0) {
            throw new Exception("O salário do jogador deve ser maior que zero");
        }
    }

    private void validatePosicao(String posicao) throws Exception {
        if (posicao == null || posicao.isEmpty()) {
            throw new Exception("A posição do jogador é obrigatória");
        }
    }

    private void validateDataNascimento(LocalDate dataNascimento) throws Exception {
        if (dataNascimento == null || dataNascimento.isAfter(LocalDate.now())) {
            throw new Exception("A data de nascimento do jogador deve ser anterior à data atual");
        }
    }

    private void validateVelocidade(int velocidade) throws Exception {
        if (velocidade < 0 || velocidade > 10) {
            throw new Exception("A velocidade do jogador deve estar entre 0 e 10");
        }
    }

    private void validateForca(int forca) throws Exception {
        if (forca < 0 || forca > 10) {
            throw new Exception("A força do jogador deve estar entre 0 e 10");
        }
    }

    private void validateAltura(double altura) throws Exception {
        if (altura < 0.50 || altura > 3.00) {
            throw new Exception("A altura do jogador deve estar entre 0,50 e 3,00 metros");
        }
    }

    private void validatePeso(int peso) throws Exception {
        if (peso < 1 || peso > 150) {
            throw new Exception("O peso do jogador deve estar entre 1 e 150");
        }
    }

    private void validateDesempenho(int desempenho) throws Exception {
        if (desempenho < 0 || desempenho > 10) {
            throw new Exception("O desempenho do jogador deve estar entre 0 e 10");
        }
    }

    public Jogador delete(Long id) throws Exception {
        Optional<Jogador> jogador = jogadorRepository.findById(id);
        if (jogador.isEmpty()) {
            throw new Exception("Jogador não encontrado com ID: " + id);
        }
        jogadorRepository.delete(jogador.get());
        return jogador.get();
    }

    public long count() {
        return jogadorRepository.count();
    }


    public Jogador create(Jogador jogador) throws Exception {
        validateJogador(jogador);
        return save(jogador);
    }
}