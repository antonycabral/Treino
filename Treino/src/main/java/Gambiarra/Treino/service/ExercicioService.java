package Gambiarra.Treino.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Gambiarra.Treino.Repository.ExercicioRepository;
import Gambiarra.Treino.Repository.TreinoRepository;
import Gambiarra.Treino.model.Exercicio;
import Gambiarra.Treino.model.Treino;

import java.util.List;
import java.util.Optional;

@Service
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Autowired
    private TreinoRepository treinoRepository;

    public Exercicio criarExercicio(String treinoId, Exercicio exercicio) {
        Treino treino = treinoRepository.findById(treinoId)
                .orElseThrow(() -> new RuntimeException("Treino não encontrado"));
        
        exercicio.setTreino(treino);
        return exercicioRepository.save(exercicio);
    }

    public List<Exercicio> listarPorTreino(String treinoId) {
        return exercicioRepository.findByTreinoId(treinoId);
    }

    public void deletarExercicio(String id) {
        exercicioRepository.deleteById(id);
    }

    public Optional<Exercicio> findById(String id) {
        return exercicioRepository.findById(id);
    }

    public Exercicio atualizarExercicio(String id, Exercicio exercicioAtualizado) {
        Exercicio exercicioExistente = exercicioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Exercício não encontrado"));
        
        exercicioExistente.setCarga(exercicioAtualizado.getCarga());
        exercicioExistente.setRepeticoes(exercicioAtualizado.getRepeticoes());
        
        return exercicioRepository.save(exercicioExistente);
    }
}