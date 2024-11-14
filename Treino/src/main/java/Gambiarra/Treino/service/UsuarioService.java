package Gambiarra.Treino.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Gambiarra.Treino.Repository.UsuarioRepository;
import Gambiarra.Treino.model.Usuario;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
    
    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }


    public Optional<Usuario> buscarPorId(String id) {
        return usuarioRepository.findById(id);
    }

    public Usuario atualizarUsuario(String id, Usuario usuario) {
        Usuario usuarioExistente = buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    
        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setIdade(usuario.getIdade());
        usuarioExistente.setPeso(usuario.getPeso());
        usuarioExistente.setAltura(usuario.getAltura());
        
        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            usuarioExistente.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
    
        return usuarioRepository.save(usuarioExistente);
    }

    public void deletarUsuario(String id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

}
