package br.caixa.odonto.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.caixa.odonto.models.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    List<Atendimento> findAtendimentoBydataAtendimentoBetween(LocalDate dI, LocalDate dF);

}
