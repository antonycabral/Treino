package Gambiarra.Treino.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import Gambiarra.Treino.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Usuario findByEmail(String email);
}
