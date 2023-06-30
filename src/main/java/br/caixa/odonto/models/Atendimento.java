package br.caixa.odonto.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Prontuário vazio")
    private String prontuario;
    @NotBlank(message = "Rg vazio")
    private String rg;
    @NotBlank(message = " Nome vazio")
    private String nome;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataAtendimento;
    private String observacoes;
    @NotBlank
    private String origem;
    private String status;
    private Boolean tratamentoConcluido;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
