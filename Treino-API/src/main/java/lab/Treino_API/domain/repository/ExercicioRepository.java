package lab.Treino_API.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lab.Treino_API.domain.model.Exercicio;
import lab.Treino_API.domain.model.Treino;

public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
    List<Exercicio> findByTreino(Treino treino);
    List<Exercicio> findByTreinoId(Long treinoId);
}
