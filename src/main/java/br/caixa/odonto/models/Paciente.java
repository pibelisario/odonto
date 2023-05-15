package br.caixa.odonto.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String prontuario;
    @NotBlank
    private String rg;
    @NotBlank
    private String nome;
    @NotNull
    private LocalDate dataAtendimento;
    private String observacoes;
    @NotBlank
    private String origem;
    private String status;

}
