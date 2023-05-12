package br.caixa.odonto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.caixa.odonto.models.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
