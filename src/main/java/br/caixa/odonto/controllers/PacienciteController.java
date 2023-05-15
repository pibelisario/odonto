package br.caixa.odonto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import br.caixa.odonto.models.Paciente;
import br.caixa.odonto.services.PacienteService;

@Controller
public class PacienciteController {

    @Autowired
    PacienteService pacienteService;

    @Autowired
    IndexController indexController;

    @PostMapping("salvarAtendimento")
    public String salvarAtendimento(Paciente paciente) {
        pacienteService.salvarPaciente(paciente);
        System.out.println(paciente.getDataAtendimento());
        return indexController.index();
    }

}
