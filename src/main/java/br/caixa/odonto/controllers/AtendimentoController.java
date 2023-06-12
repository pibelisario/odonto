package br.caixa.odonto.controllers;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.caixa.odonto.models.Atendimento;
import br.caixa.odonto.models.Usuario;
import br.caixa.odonto.repositories.UsuarioRepository;
import br.caixa.odonto.services.AtendimentoService;

@Controller
public class AtendimentoController {

    @Autowired
    AtendimentoService atendimentoService;

    @Autowired
    IndexController indexController;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("salvarAtendimento")
    public ModelAndView salvarAtendimento(Atendimento atendimento, RedirectAttributes attributes,
            @RequestParam String userName) {
        ModelAndView mv = new ModelAndView("redirect:/cadAtendimento");
        atendimentoService.salvarPaciente(atendimento, userName);
        attributes.addFlashAttribute("msg", "Cadastro salvo com sucesso!");
        return mv;
    }

    @GetMapping("/cadAtendimento")
    public ModelAndView cadAtendimento() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
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
