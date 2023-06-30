package br.caixa.odonto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.caixa.odonto.models.Atendimento;

@Controller
public class IndexController {

    // @GetMapping("index")
    // public ModelAndView index() {
    // ModelAndView mv = new ModelAndView("index");
    // LocalDate date = LocalDate.now();
    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // mv.addObject("novoPaciente", new Paciente());
    // mv.addObject("dataAten", date);
    // System.out.println(date.format(formatter));
    // return mv;
    // }

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/login");
        return mv;
    }

    @RequestMapping("/login")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/login");
        return mv;
    }

}
