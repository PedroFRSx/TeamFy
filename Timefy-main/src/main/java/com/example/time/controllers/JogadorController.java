package com.example.time.controllers;

import com.example.time.models.Jogador;
import com.example.time.services.JogadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/jogadores")
// Permite apenas esta origem específica
public class JogadorController {

    private final JogadorService jogadorService;

    public JogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Jogador jogador) {
        try {
            Jogador savedJogador = jogadorService.create(jogador);
            return ResponseEntity.ok(savedJogador);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Jogador> getAllJogadores() {
        return jogadorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById (@PathVariable Long id) {
        try {
            Jogador jogador = jogadorService.findById(id);
            return ResponseEntity.ok(jogador);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity  edit(@PathVariable Long id, @RequestBody Jogador jogadorDetails) {
        try {
            Jogador jogador = jogadorService.findById(id);
            jogador.setNome(jogadorDetails.getNome());
            jogador.setSalario(jogadorDetails.getSalario());
            jogador.setPosicao(jogadorDetails.getPosicao());
            jogador.setDataNascimento(jogadorDetails.getDataNascimento());
            jogador.setVelocidade(jogadorDetails.getVelocidade());
            jogador.setForca(jogadorDetails.getForca());
            jogador.setAltura(jogadorDetails.getAltura());
            jogador.setPeso(jogadorDetails.getPeso());
            jogador.setDesempenho(jogadorDetails.getDesempenho());
            return ResponseEntity.ok(jogadorService.save(jogador));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(jogadorService.delete(id));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getTotalJogadores() {
        return ResponseEntity.ok(jogadorService.count());
    }
}