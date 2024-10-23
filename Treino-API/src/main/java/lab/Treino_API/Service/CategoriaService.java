package lab.Treino_API.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab.Treino_API.domain.model.Categoria;
import lab.Treino_API.domain.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> findById(Long id){
        return categoriaRepository.findById(id);
    }

    public Categoria save(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public void deleteById(Long id){
        categoriaRepository.deleteById(id);
    }

    public List<Categoria> findByNome(String nome) {
        return categoriaRepository.findByNomeContainingIgnoreCase(nome);
    }
}
