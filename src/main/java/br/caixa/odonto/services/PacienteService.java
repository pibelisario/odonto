package br.caixa.odonto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.caixa.odonto.models.Paciente;
import br.caixa.odonto.repositories.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    public void salvarPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    public List<Paciente> listAll() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes;
    }

}
