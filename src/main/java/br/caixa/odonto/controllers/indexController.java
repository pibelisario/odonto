package br.caixa.odonto.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.caixa.odonto.models.Paciente;

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
