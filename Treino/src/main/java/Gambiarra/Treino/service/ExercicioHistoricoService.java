package Gambiarra.Treino.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Gambiarra.Treino.Repository.ExercicioHistoricoRepository;
import Gambiarra.Treino.model.Exercicio;
import Gambiarra.Treino.model.ExercicioHistorico;

@Service
public class ExercicioHistoricoService {
    @Autowired
    private ExercicioHistoricoRepository historicoRepository;
    
    @Autowired
    private ExercicioService exercicioService;

    public ExercicioHistorico registrarAlteracao(String exercicioId, ExercicioHistorico historico) {
        Exercicio exercicio = exercicioService.findById(exercicioId)
            .orElseThrow(() -> new RuntimeException("Exercício não encontrado"));
        historico.setExercicio(exercicio);
        historico.setDataAlteracao(LocalDateTime.now());
        return historicoRepository.save(historico);
    }

    public List<ExercicioHistorico> getHistoricoExercicio(String exercicioId) {
        return historicoRepository.findByExercicioIdOrderByDataAlteracaoDesc(exercicioId);
    }
}