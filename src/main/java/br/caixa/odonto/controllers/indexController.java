package br.caixa.odonto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class indexController {

    @GetMapping
    public String index() {
        return "index.html";
    }

    @GetMapping("formulario")
    public String formulario() {
        return "formulario.html";
    }
}
