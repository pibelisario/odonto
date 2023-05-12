package br.caixa.odonto.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
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
    @NotBlank
    private LocalDate dataAtendimento;
    private String observacoes;
    @NotBlank
    private String origem;
    private String status;

}
