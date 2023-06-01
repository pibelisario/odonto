package br.caixa.odonto.repositories;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import br.caixa.odonto.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // @Query("select u from Usuario u where u.user = :user and u.senha = :senha")
    // Usuario findUser(String user, String senha);

    Optional<Usuario> findByUser(String username);

}
