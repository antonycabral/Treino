package Gambiarra.Treino.Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Gambiarra.Treino.model.Desempenho;

public interface DesempenhoRepository extends JpaRepository<Desempenho, String> {

    List<Desempenho> findByUsuarioId(String usuarioId);
    
    @Query("SELECT d FROM Desempenho d WHERE d.usuario.id = :usuarioId ORDER BY d.dataTreino DESC")
    List<Desempenho> findLatestByUsuarioId(@Param("usuarioId") String usuarioId, Pageable pageable);


}
