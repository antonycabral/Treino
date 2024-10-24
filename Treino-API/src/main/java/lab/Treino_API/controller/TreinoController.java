package lab.Treino_API.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lab.Treino_API.Service.TreinoService;
import lab.Treino_API.domain.model.Treino;


@RestController
@RequestMapping("/api/treinos")
public class TreinoController {
    @Autowired
    private TreinoService treinoService;

    @GetMapping
    public ResponseEntity<List<Treino>> todosTreinos() {
        List<Treino> treinos = treinoService.findAll();
        return new ResponseEntity<>(treinos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Treino> treinoPorId(@PathVariable long id) {
        Optional<Treino> treino = treinoService.findById(id);
        return treino.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Treino> cadastrarTreino(@RequestBody Treino treino) {
        Treino treinoCadastro = treinoService.save(treino);
        return new ResponseEntity<>(treinoCadastro, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTreino(@PathVariable long id) {
        treinoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    

    
}