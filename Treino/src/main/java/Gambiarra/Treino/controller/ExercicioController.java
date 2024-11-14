package Gambiarra.Treino.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Gambiarra.Treino.model.Exercicio;
import Gambiarra.Treino.service.ExercicioService;

@RestController
@RequestMapping("/api/exercicios")
@CrossOrigin(origins = "http://localhost:4200")
public class ExercicioController {

    @Autowired
    private ExercicioService exercicioService;

    @PostMapping("/treino/{treinoId}")
    public ResponseEntity<Exercicio> criarExercicio(@PathVariable String treinoId, @RequestBody Exercicio exercicio) {
        Exercicio novoExercicio = exercicioService.criarExercicio(treinoId, exercicio);
        return ResponseEntity.ok(novoExercicio);
    }

    @GetMapping("/treino/{treinoId}")
    public ResponseEntity<List<Exercicio>> listarExerciciosPorTreino(@PathVariable String treinoId) {
        List<Exercicio> exercicios = exercicioService.listarPorTreino(treinoId);
        return ResponseEntity.ok(exercicios);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarExercicio(@PathVariable String id) {
        exercicioService.deletarExercicio(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercicio> atualizarExercicio(@PathVariable String id, @RequestBody Exercicio exercicio) {
        return ResponseEntity.ok(exercicioService.atualizarExercicio(id, exercicio));
    }
}