package lab.Treino_API.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lab.Treino_API.domain.model.Exercicio;

public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
    List<Exercicio> findByNomeContainingIgnoreCase(String nome);
}
