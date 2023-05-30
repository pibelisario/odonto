package br.caixa.odonto.controllers;

import java.text.ParseException;
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
        ModelAndView mv = new ModelAndView("atendimentos");
        List<Atendimento> atendimentos = atendimentoService.findByData(data);
        mv.addObject("atendimentos", atendimentos);
        return mv;
    }

    @GetMapping("atendimentos")
    public ModelAndView atendimentos() {
        ModelAndView mv = new ModelAndView("atendimentos");
        List<Atendimento> atendimento = (atendimentoService.listAll());
        mv.addObject("atendimentos", atendimento);
        return mv;
    }

    @GetMapping("excluirAtendimento/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        atendimentoService.excluirAtendimento(id);
        return new ModelAndView("redirect:/atendimentos");

    }

    @GetMapping("editarAtendimento/{idAtendimento}")
    public ModelAndView editarAtendimento(@PathVariable("idAtendimento") Long idAtendimento) {
        ModelAndView mv = new ModelAndView("editarAtendimento");
        mv.addObject("objAtendimento", atendimentoService.findById(idAtendimento));
        return mv;
    }

}
