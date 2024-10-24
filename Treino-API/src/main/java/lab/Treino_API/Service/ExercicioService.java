package lab.Treino_API.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab.Treino_API.domain.model.Exercicio;
import lab.Treino_API.domain.repository.ExercicioRepository;

@Service
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    public List<Exercicio> findAll() {
        return exercicioRepository.findAll();
    }

    public Exercicio save(Exercicio exercicio) {
        return exercicioRepository.save(exercicio);
    }

    public Optional<Exercicio> findById(Long id) {
        return exercicioRepository.findById(id);
    }

    public void deleteById(Long id) {
        exercicioRepository.deleteById(id);
    }

    public List<Exercicio> findByTreinoId(Long treinoId) {
        return exercicioRepository.findByTreinoId(treinoId); // Chame o método do repositório
    }

}
