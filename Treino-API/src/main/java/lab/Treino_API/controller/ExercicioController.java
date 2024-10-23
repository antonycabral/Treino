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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lab.Treino_API.Service.ExercicioService;
import lab.Treino_API.domain.model.Exercicio;

@RestController
@RequestMapping("/exercicio")
public class ExercicioController {

    @Autowired
    private ExercicioService exercicioService;

    @GetMapping
    public ResponseEntity<List<Exercicio>> todosExercicios() {
        List<Exercicio> exercicios = exercicioService.findAll();
        return new ResponseEntity<>(exercicios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Exercicio>> exercicioPorId(@PathVariable long id) {
        Optional<Exercicio> exercicio = exercicioService.findById(id);
        if (exercicio.isPresent()) {
            return new ResponseEntity<>(exercicio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Exercicio> cadastrarExercicio(@RequestBody Exercicio exercicio) {
        Exercicio exercicioCadastro = exercicioService.save(exercicio);
        return new ResponseEntity<>(exercicioCadastro, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarExercicio(@PathVariable long id) {
        exercicioService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercicio> atualizarExercicio(@PathVariable Long id, @RequestBody Exercicio exercicio) {
        Optional<Exercicio> exercicioExiste = exercicioService.findById(id);
        if (exercicioExiste.isPresent()) {
            Exercicio exercicioAtualizado = exercicioExiste.get();
            exercicioAtualizado.setNome(exercicio.getNome());
            exercicioAtualizado.setSeries(exercicio.getSeries());
            exercicioAtualizado.setRepeticoes(exercicio.getRepeticoes());
            exercicioAtualizado.setCarga(exercicio.getCarga());
            exercicioAtualizado.setDescanso(exercicio.getDescanso());
            exercicioAtualizado.setEnergia(exercicio.getEnergia());
            exercicioAtualizado.setTreino(exercicio.getTreino());
            exercicioAtualizado.setCategoria(exercicio.getCategoria());
            Exercicio atualizado = exercicioService.save(exercicioAtualizado);
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarPorNome")
    public ResponseEntity<List<Exercicio>> buscarPorNome(@RequestParam String nome) {
        List<Exercicio> exercicios = exercicioService.findByNome(nome);
        return new ResponseEntity<>(exercicios, HttpStatus.OK);
    }

}
