package br.caixa.odonto.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.caixa.odonto.models.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    // List<Atendimento> findAtendimentoByDataBetween(java.util.Date dataInifical,
    // java.util.Date dataFinal);

}
