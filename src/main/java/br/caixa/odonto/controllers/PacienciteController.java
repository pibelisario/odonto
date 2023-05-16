package br.caixa.odonto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("atendimentos")
    public ModelAndView atendimentos() {
        ModelAndView mv = new ModelAndView("atendimentos");
        List<Paciente> pacientes = (pacienteService.listAll());
        mv.addObject("pacientes", pacientes);
        return mv;
    }

    @GetMapping("editarPaciente/{id}")
    public ModelAndView editarPaciente(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("editarAtendimento");
        mv.addObject("objPaciente", pacienteService.findById(id));
        return mv;
    }

    @GetMapping("excluirPaciente/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        pacienteService.excluirPaciente(id);
        return new ModelAndView("redirect:/atendimentos");

    }

}
