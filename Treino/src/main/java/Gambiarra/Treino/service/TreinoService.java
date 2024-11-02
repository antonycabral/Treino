package Gambiarra.Treino.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Gambiarra.Treino.Repository.TreinoRepository;
import Gambiarra.Treino.model.Treino;


import java.util.List;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    public Treino criar(Treino treino) {
        return treinoRepository.save(treino);
    }

    public List<Treino> listarPorUsuario(String usuarioId) {
        return treinoRepository.findByUsuarioId(usuarioId);
    }

    public Treino buscarPorId(String id) {
        return treinoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Treino não encontrado"));
    }

    public void deletar(String id) {
        treinoRepository.deleteById(id);
    }

    public Treino atualizar(Treino treino) {
        return treinoRepository.save(treino);
    }
}

