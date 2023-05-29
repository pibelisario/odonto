package br.caixa.odonto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("index")
    public String index() {
        return "index";
    }

}
