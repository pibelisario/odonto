package br.caixa.odonto.controllers;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.caixa.odonto.models.Atendimento;
import br.caixa.odonto.services.AtendimentoService;
import jakarta.validation.Valid;

@Controller
public class AtendimentoController {

    @Autowired
    AtendimentoService atendimentoService;

    @Autowired
    IndexController indexController;

    @PostMapping("salvarAtendimento")
    public ModelAndView salvarAtendimento(@Valid Atendimento atendimento, BindingResult result,
            RedirectAttributes attributes,
            @RequestParam String userName) {
        ModelAndView mv = new ModelAndView("redirect:/cadAtendimento");

        if (result.hasErrors()) {
            // List<String> msg = new ArrayList<>();
            // for (ObjectError objectError : result.getAllErrors()) {
            // msg.add(objectError.getDefaultMessage());
            // }
            attributes.addFlashAttribute("error", "Verifique se todos os campos estão digitados corretamente");
            return mv;
        }
        atendimentoService.salvarPaciente(atendimento, userName);
        attributes.addFlashAttribute("msg", "Cadastro salvo com sucesso!");
        return mv;
    }

    @PostMapping("salvarAtendimentoEditado")
    public ModelAndView salvarAtendimentoEditado(@Valid Atendimento atendimento, BindingResult result,
            RedirectAttributes attributes,
            @RequestParam String userName, @RequestParam Long id) {
        ModelAndView mv = new ModelAndView("redirect:/atendimentos/" + userName);

        if (result.hasErrors()) {
            // List<String> msg = new ArrayList<>();
            // for (ObjectError objectError : result.getAllErrors()) {
            // msg.add(objectError.getDefaultMessage());
            // }
            attributes.addFlashAttribute("error", "Verifique se todos os campos estão digitados corretamente");
            return mv;
        }
        atendimentoService.salvarPaciente(atendimento, userName);
        attributes.addFlashAttribute("msg", "Cadastro salvo com sucesso!");
        return mv;
    }

    @GetMapping("/cadAtendimento")
    public ModelAndView cadAtendimento() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @GetMapping("/buscarPorMes")
    public ModelAndView buscarPorMes(@RequestParam String data, @RequestParam String userName) throws ParseException {
        ModelAndView mv = new ModelAndView("atendimentos");
        List<Atendimento> atendimentos = atendimentoService.findByData(data, userName);
        System.out.println("asdfasdf" + data);
        mv.addObject("atendimentos", atendimentos);
        return mv;
    }

    @GetMapping("atendimentos/{userName}")
    public ModelAndView atendimentos(@PathVariable String userName) {
        ModelAndView mv = new ModelAndView("atendimentos");
        mv.addObject("atendimentos", atendimentoService.listAll(userName));
        return mv;
    }

    @GetMapping("excluirAtendimento/{id}/{userName}")
    public ModelAndView excluir(@PathVariable("id") Long id, @PathVariable String userName) {
        atendimentoService.excluirAtendimento(id);
        return new ModelAndView("redirect:/atendimentos/" + userName);

    }

    @GetMapping("editarAtendimento/{idAtendimento}")
    public ModelAndView editarAtendimento(@PathVariable("idAtendimento") Long idAtendimento) {
        ModelAndView mv = new ModelAndView("editarAtendimento");
        mv.addObject("objAtendimento", atendimentoService.findById(idAtendimento));
        return mv;
    }

}
