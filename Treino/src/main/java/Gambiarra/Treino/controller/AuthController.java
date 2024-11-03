package Gambiarra.Treino.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Gambiarra.Treino.Repository.UsuarioRepository;
import Gambiarra.Treino.config.security.TokenService;
import Gambiarra.Treino.dtos.LoginRequest;
import Gambiarra.Treino.dtos.RegisterRequestDTO;
import Gambiarra.Treino.dtos.ResponseDTO;
import Gambiarra.Treino.model.Usuario;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthController(UsuarioRepository repository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest body){
        Usuario user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getNome(), token, user.getId()));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
        try {
            if (this.repository.findByEmail(body.email()).isPresent()) {
                return ResponseEntity.badRequest().body("Email já cadastrado");
            }

            Usuario newUser = new Usuario();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setNome(body.nome());
            newUser.setPeso(body.peso());
            newUser.setIdade(body.idade());
            newUser.setAltura(body.altura());
            
            Usuario savedUser = this.repository.save(newUser);
            String token = this.tokenService.generateToken(savedUser);
            
            return ResponseEntity.ok(new ResponseDTO(savedUser.getEmail(), token, savedUser.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro no cadastro: " + e.getMessage());
        }
    }
}
