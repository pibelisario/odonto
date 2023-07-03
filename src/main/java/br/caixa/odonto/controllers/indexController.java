package br.caixa.odonto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.caixa.odonto.models.Usuario;
import br.caixa.odonto.repositories.UsuarioRepository;
import br.caixa.odonto.services.UsuarioService;

@Controller
public class IndexController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @RequestMapping("/")
    public String index() {
        return "redirect:/login2";
    }

    // @RequestMapping("/login")
    // public ModelAndView home() {
    // ModelAndView mv = new ModelAndView("login/login2");
    // return mv;
    // }

    @GetMapping("/login")
    public ModelAndView login2() {
        ModelAndView mv = new ModelAndView("login/login2");
        return mv;
    }

}
