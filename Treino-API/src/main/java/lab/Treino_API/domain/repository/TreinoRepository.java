package lab.Treino_API.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lab.Treino_API.domain.model.Treino;

public interface TreinoRepository extends JpaRepository<Treino, Long> {
    List<Treino> findByTipoContainingIgnoreCase(String tipo);
}
