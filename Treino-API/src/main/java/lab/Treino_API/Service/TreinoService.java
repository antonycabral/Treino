package lab.Treino_API.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab.Treino_API.domain.model.Treino;
import lab.Treino_API.domain.repository.TreinoRepository;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    public List<Treino> findAll(){
        return treinoRepository.findAll();
    }

    public Treino save(Treino treino){
        return treinoRepository.save(treino);
    }

    public Optional<Treino> findById(Long id){
        return treinoRepository.findById(id);
    }

    public void deleteById(Long id){
        treinoRepository.deleteById(id);
    }

    public List<Treino> findByTipo(String tipo) {
        return treinoRepository.findByTipoContainingIgnoreCase(tipo);
    }
}
