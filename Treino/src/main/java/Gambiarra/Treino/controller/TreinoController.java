package Gambiarra.Treino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Gambiarra.Treino.model.Treino;
import Gambiarra.Treino.service.TreinoService;

import java.util.List;

@RestController
@RequestMapping("/api/treinos")
@CrossOrigin(origins = "*")
public class TreinoController {

    @Autowired
    private TreinoService treinoService;

    @PostMapping
    public ResponseEntity<Treino> criar(@RequestBody Treino treino) {
        return ResponseEntity.ok(treinoService.criar(treino));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Treino>> listarPorUsuario(@PathVariable String usuarioId) {
        return ResponseEntity.ok(treinoService.listarPorUsuario(usuarioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Treino> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(treinoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        treinoService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Treino> atualizar(@PathVariable String id, @RequestBody Treino treino) {
        treino.setId(id);
        return ResponseEntity.ok(treinoService.atualizar(treino));
    }
}
