package br.caixa.odonto.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.caixa.odonto.models.Usuario;
import br.caixa.odonto.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUser(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new User(usuario.getUsername(), usuario.getPassword(), true, true,
                true, true, usuario.getAuthorities());
    }

}
