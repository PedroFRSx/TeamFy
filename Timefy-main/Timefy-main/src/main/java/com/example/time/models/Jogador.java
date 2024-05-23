package com.example.time.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity

public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 5, message = "O nome deve ter pelo menos 5 caracteres")
    private String nome;

    @Positive(message = "O salário deve ser maior que zero")
    private double salario;

    @NotBlank(message = "A posição é obrigatória")
    private String posicao;

    @NotNull(message = "A data de nascimento é obrigatória.")
    @Past(message = "A data de nascimento deve ser menor que a data atual.")
    private LocalDate dataNascimento;

    @Min(value = 0, message = "A velocidade deve ser pelo menos 0")
    @Max(value = 10, message = "A velocidade deve ser no máximo 10")
    private int velocidade;

    @Min(value = 0, message = "A força deve ser pelo menos 0")
    @Max(value = 10, message = "A força deve ser no máximo 10")
    private int forca;

    @DecimalMin(value = "0.50", message = "A altura deve ser pelo menos 0,50 metros")
    @DecimalMax(value = "3.00", message = "A altura deve ser no máximo 3,00 metros")
    private double altura;

    @Min(value = 1, message = "O peso deve ser pelo menos 1")
    @Max(value = 150, message = "O peso deve ser no máximo 150")
    private int peso;

    @Min(value = 0, message = "O desempenho deve ser pelo menos 0")
    @Max(value = 10, message = "O desempenho deve ser no máximo 10")
    private int desempenho;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getDesempenho() {
        return desempenho;
    }

    public void setDesempenho(int desempenho) {
        this.desempenho = desempenho;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "time_id", nullable = true)
    private Time time;
}
