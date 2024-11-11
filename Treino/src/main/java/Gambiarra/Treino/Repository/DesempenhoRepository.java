package Gambiarra.Treino.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Gambiarra.Treino.model.Desempenho;

public interface DesempenhoRepository extends JpaRepository<Desempenho, String> {

}
