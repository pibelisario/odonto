package br.caixa.odonto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.caixa.odonto.models.Paciente;

@Controller
public class PacienciteController {

    @PostMapping("salvarAtendimento")
    public ModelAndView salvarAtendimento(Paciente pacienciente) {
        return null;
    }

}
