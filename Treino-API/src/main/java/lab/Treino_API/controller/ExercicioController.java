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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lab.Treino_API.Service.ExercicioService;
import lab.Treino_API.Service.TreinoService;
import lab.Treino_API.domain.model.Exercicio;

@RestController
@RequestMapping("/api/exercicios")
public class ExercicioController {


    @Autowired
    private TreinoService treinoService;

    @Autowired
    private ExercicioService exercicioService;

    // Listar todos os exercícios
    @GetMapping
    public ResponseEntity<List<Exercicio>> todosExercicios() {
        List<Exercicio> exercicios = exercicioService.findAll();
        return new ResponseEntity<>(exercicios, HttpStatus.OK);
    }

    // Listar exercício por ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Exercicio>> exercicioPorId(@PathVariable long id) {
        Optional<Exercicio> exercicio = exercicioService.findById(id);
        if (exercicio.isPresent()) {
            return new ResponseEntity<>(exercicio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Cadastrar novo exercício
    @PostMapping
    public ResponseEntity<Exercicio> cadastrarExercicio(@RequestBody Exercicio exercicio) {
        Exercicio exercicioCadastro = exercicioService.save(exercicio);
        return new ResponseEntity<>(exercicioCadastro, HttpStatus.CREATED);
    }

    // Deletar exercício por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarExercicio(@PathVariable long id) {
        exercicioService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Atualizar exercício
    @PutMapping("/{id}")
    public ResponseEntity<Exercicio> atualizarExercicio(@PathVariable Long id, @RequestBody Exercicio exercicio) {
        Optional<Exercicio> exercicioExiste = exercicioService.findById(id);

        if (exercicioExiste.isPresent()) {
            Exercicio exercicioAtualizado = exercicioExiste.get();
            exercicioAtualizado.setNome(exercicio.getNome());
            exercicioAtualizado.setCarga(exercicio.getCarga());
            exercicioAtualizado.setSeries(exercicio.getSeries());
            exercicioAtualizado.setTempo(exercicio.getTempo());
            exercicioAtualizado.setDescanso(exercicio.getDescanso());
            Exercicio atualizado = exercicioService.save(exercicioAtualizado);
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Novo método para listar exercícios de um treino específico
    @GetMapping("/{id}/exercicios")
    public ResponseEntity<List<Exercicio>> exerciciosPorTreinoId(@PathVariable Long id) {
        List<Exercicio> exercicios = exercicioService.findByTreinoId(id);
        if (exercicios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(exercicios, HttpStatus.OK);
        }
    }
}
