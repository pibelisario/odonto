package br.caixa.odonto.controllers;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.caixa.odonto.models.Atendimento;
import br.caixa.odonto.services.AtendimentoService;

@Controller
public class PacienciteController {

    @Autowired
    AtendimentoService atendimentoService;

    @Autowired
    IndexController indexController;

    @PostMapping("salvarAtendimento")
    public String salvarAtendimento(Atendimento paciente) {
        atendimentoService.salvarPaciente(paciente);
        return indexController.index();
    }

    @PostMapping("buscarPorMes")
    public ModelAndView buscarPorMes(String data) throws ParseException {
        List<Atendimento> atendimentos = atendimentoService.findByData(data);
        return null;
    }

    @GetMapping("atendimentos")
    public ModelAndView atendimentos() {
        ModelAndView mv = new ModelAndView("atendimentos");
        List<Atendimento> pacientes = (atendimentoService.listAll());
        mv.addObject("pacientes", pacientes);
        return mv;
    }

    @GetMapping("excluirPaciente/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        atendimentoService.excluirPaciente(id);
        return new ModelAndView("redirect:/atendimentos");

    }

    @GetMapping("editarAtendimento/{idPaciente}")
    public ModelAndView editarAtendimento(@PathVariable("idPaciente") Long idPaciente) {
        ModelAndView mv = new ModelAndView("editarAtendimento");
        mv.addObject("objPaciente", atendimentoService.findById(idPaciente));
        return mv;
    }

}
