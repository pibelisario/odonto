package br.caixa.odonto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.caixa.odonto.enums.RoleName;
import br.caixa.odonto.models.Usuario;
import br.caixa.odonto.services.UsuarioService;

@Controller
public class IndexController {

    @Autowired
    UsuarioService usuarioService;

    // @RequestMapping("/")
    // public ModelAndView index() {
    // ModelAndView mv = new ModelAndView();
    // List<Usuario> usuarios = usuarioService.findAll();
    // mv.addObject("listaUsuarios", usuarios);
    // mv.setViewName("redirect:/login");
    // return mv;
    // }

    @RequestMapping("/login")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("login/login");
        List<Usuario> usuarios = usuarioService.findAll();
        mv.addObject("listaUsuarios", usuarios);
        return mv;
    }

}
