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

import lab.Treino_API.Service.UsuarioService;
import lab.Treino_API.domain.model.Usuario;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //listar todos usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> todosUsuario(){
        List<Usuario> usuarios = usuarioService.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    //Listar por id
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> usuarioPorId(@PathVariable long id){
        Optional<Usuario> usuario = usuarioService.findById(id);
        if(usuario.isPresent()){
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario){
        Usuario usuarioCadastro = usuarioService.save(usuario);
        return new ResponseEntity<>(usuarioCadastro, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable long id){
        usuarioService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        Optional<Usuario> usuarioExiste = usuarioService.findById(id);

        if (usuarioExiste.isPresent()) {
            Usuario usuarioAtualizado = usuarioExiste.get();
            usuarioAtualizado.setNomeCompleto(usuario.getNomeCompleto());
            usuarioAtualizado.setPeso(usuario.getPeso());
            usuarioAtualizado.setIdade(usuario.getIdade());
            usuarioAtualizado.setSexo(usuario.getSexo());
            Usuario atualizado = usuarioService.save(usuarioAtualizado);
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //buscar por nome
    @GetMapping("/nome")
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam String nome){
        List<Usuario> usuarios = usuarioService.findByNome(nome);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
}
