package Gambiarra.Treino.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Gambiarra.Treino.Repository.UsuarioRepository;
import Gambiarra.Treino.config.security.JwtService;
import Gambiarra.Treino.dtos.AuthResponse;
import Gambiarra.Treino.dtos.LoginRequest;
import Gambiarra.Treino.model.Usuario;

@Service
public class AuthService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtService jwtService;

    public Usuario cadastrar(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }
        
        // Encrypt password before saving
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public AuthResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.email())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            
        if (!passwordEncoder.matches(request.password(), usuario.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }
        
        String token = jwtService.generateToken(usuario.getEmail());
        return new AuthResponse(token);
    }
}