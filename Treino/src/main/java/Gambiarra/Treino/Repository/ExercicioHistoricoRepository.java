package Gambiarra.Treino.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Gambiarra.Treino.model.ExercicioHistorico;

public interface ExercicioHistoricoRepository extends JpaRepository<ExercicioHistorico, String> {
    List<ExercicioHistorico> findByExercicioIdOrderByDataAlteracaoDesc(String exercicioId);
}