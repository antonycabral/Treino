package Gambiarra.Treino.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Gambiarra.Treino.model.Exercicio;

public interface ExercicioRepository extends JpaRepository<Exercicio, String> {

}
