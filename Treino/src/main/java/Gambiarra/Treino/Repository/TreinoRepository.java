package Gambiarra.Treino.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Gambiarra.Treino.model.Treino;

public interface TreinoRepository extends JpaRepository<Treino, String> {
        List<Treino> findByUsuarioId(String usuarioId);

        @Query("SELECT COUNT(t) FROM Treino t WHERE t.usuario.id = :userId")
        int countByUsuarioId(String userId);
        
        @Query("SELECT COUNT(t) FROM Treino t " +
                "WHERE t.usuario.id = :userId " +
                "AND CAST(t.dataCriacao AS date) >= :inicioDaSemana " +
                "GROUP BY FUNCTION('DAYOFWEEK', t.dataCriacao) " +
                "ORDER BY FUNCTION('DAYOFWEEK', t.dataCriacao)")
        List<Integer> countTreinosPorDiaSemana(String userId, LocalDate inicioDaSemana);
        
        @Query("SELECT COUNT(t) FROM Treino t " +
                "WHERE t.usuario.id = :userId " +
                "AND CAST(t.dataCriacao AS date) >= :inicioDoMes " +
                "GROUP BY FUNCTION('DAY', t.dataCriacao) " +
                "ORDER BY FUNCTION('DAY', t.dataCriacao)")
        List<Integer> countTreinosPorDiaMes(String userId, LocalDate inicioDoMes);
}
