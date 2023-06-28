package br.caixa.odonto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.caixa.odonto.models.Usuario;
import br.caixa.odonto.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario findByUser(String userName) {
        return usuarioRepository.findByUser(userName).get();
    }

}
