package lab.Treino_API.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab.Treino_API.domain.model.Usuario;
import lab.Treino_API.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> findById(Long id){
        return usuarioRepository.findById(id);
    }

    public void deleteById(Long id){
        usuarioRepository.deleteById(id);
    }

}
