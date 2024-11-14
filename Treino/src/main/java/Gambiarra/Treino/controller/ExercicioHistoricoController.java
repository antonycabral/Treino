package Gambiarra.Treino.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Gambiarra.Treino.model.ExercicioHistorico;
import Gambiarra.Treino.service.ExercicioHistoricoService;

@RestController
@RequestMapping("/api/exercicio-historico")
public class ExercicioHistoricoController {
    @Autowired
    private ExercicioHistoricoService historicoService;

    @PostMapping("/{exercicioId}")
    public ResponseEntity<ExercicioHistorico> registrarAlteracao(
            @PathVariable String exercicioId,
            @RequestBody ExercicioHistorico historico) {
        return ResponseEntity.ok(historicoService.registrarAlteracao(exercicioId, historico));
    }

    @GetMapping("/exercicio/{exercicioId}")
    public ResponseEntity<List<ExercicioHistorico>> getHistorico(@PathVariable String exercicioId) {
        return ResponseEntity.ok(historicoService.getHistoricoExercicio(exercicioId));
    }
}