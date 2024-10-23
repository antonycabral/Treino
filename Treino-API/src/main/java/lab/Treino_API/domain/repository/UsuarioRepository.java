package lab.Treino_API.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lab.Treino_API.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    List<Usuario> findByNomeCompleto(String nomeCompleto);
}
