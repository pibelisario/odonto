package br.caixa.odonto.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.caixa.odonto.models.Atendimento;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    List<Atendimento> findAtendimentoBydataAtendimentoBetween(LocalDate dI, LocalDate dF);

}
