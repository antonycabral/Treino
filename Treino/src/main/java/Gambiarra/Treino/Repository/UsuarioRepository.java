package Gambiarra.Treino.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Gambiarra.Treino.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String email);
}
