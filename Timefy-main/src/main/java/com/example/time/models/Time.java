package com.example.time.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 3, message = "O nome deve ter pelo menos 3 caracteres.")
    private String nome;

    @NotNull(message = "A data de criação é obrigatória.")
    @PastOrPresent(message = "A data de criação não pode ser posterior à data atual.")
    private LocalDate dataCriacao;

    @NotBlank(message = "A cidade é obrigatória")
    @Size(min = 3, message = "A cidade deve ter pelo menos 3 caracteres.")
    private String cidade;

    @NotBlank(message = "O país é obrigatório")
    @Pattern(regexp = "Argentina|Brasil|Chile|Uruguai", message = "O país deve ser um dos países da América do Sul.")
    private String pais;

    @Positive(message = "O número de torcedores deve ser maior que zero")
    private int numeroTorcedores;

    @NotBlank(message = "O técnico é obrigatório")
    private String tecnico;

    @DecimalMin(value = "0.0", message = "O dinheiro em caixa deve ser pelo menos 0.0")
    @DecimalMax(value = "999999999.99", message = "O dinheiro em caixa deve ser no máximo 999.999.999,99")
    private double dinheiroCaixa;

    @NotBlank(message = "O nome do estádio é obrigatório")
    private String nomeEstadio;


    public Long getId() {
        return id;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getNumeroTorcedores() {
        return numeroTorcedores;
    }

    public void setNumeroTorcedores(int numeroTorcedores) {
        this.numeroTorcedores = numeroTorcedores;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public double getDinheiroCaixa() {
        return dinheiroCaixa;
    }

    public void setDinheiroCaixa(double dinheiroCaixa) {
        this.dinheiroCaixa = dinheiroCaixa;
    }

    public String getNomeEstadio() {
        return nomeEstadio;
    }

    public void setNomeEstadio(String nomeEstadio) {
        this.nomeEstadio = nomeEstadio;
    }
   @OneToMany(mappedBy = "time", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Jogador> jogador = new HashSet<>();
}
