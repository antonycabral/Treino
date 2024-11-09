package Gambiarra.Treino.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Gambiarra.Treino.model.Exercicio;

public interface ExercicioRepository extends JpaRepository<Exercicio, String> {

    List<Exercicio> findByTreinoId(String treinoId);

}
