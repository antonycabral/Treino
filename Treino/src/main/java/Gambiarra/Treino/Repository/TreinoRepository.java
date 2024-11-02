package Gambiarra.Treino.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Gambiarra.Treino.model.Treino;

public interface TreinoRepository extends JpaRepository<Treino, String> {
    List<Treino> findByUsuarioId(String usuarioId);
}
