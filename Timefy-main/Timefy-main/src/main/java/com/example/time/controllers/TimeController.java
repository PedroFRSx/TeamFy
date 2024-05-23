package com.example.time.controllers;

import com.example.time.models.Time;
import com.example.time.services.TimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/times")
public class TimeController {

    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }
    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(timeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findById (@PathVariable Long id) {
        try {
            Time time = timeService.findById(id);
            return ResponseEntity.ok(time);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Time time) {
        try {
            Time savedTime = timeService.save(time);
            return ResponseEntity.ok(savedTime);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id, @RequestBody Time timeDetails) {
        try {
            Time time = timeService.findById(id);
            time.setNome(timeDetails.getNome());
            time.setDataCriacao(timeDetails.getDataCriacao());
            time.setCidade(timeDetails.getCidade());
            time.setPais(timeDetails.getPais());
            time.setNumeroTorcedores(timeDetails.getNumeroTorcedores());
            time.setTecnico(timeDetails.getTecnico());
            time.setDinheiroCaixa(timeDetails.getDinheiroCaixa());
            time.setNomeEstadio(timeDetails.getNomeEstadio());
            return ResponseEntity.ok(timeService.save(time));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(timeService.delete(id));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getTotalTimes() {
        return ResponseEntity.ok(timeService.count());
    }
}