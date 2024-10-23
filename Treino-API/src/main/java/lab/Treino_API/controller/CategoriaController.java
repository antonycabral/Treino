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
import lab.Treino_API.Service.CategoriaService;
import lab.Treino_API.domain.model.Categoria;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categorias = categoriaService.findAll();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Categoria>> getCategoriaById(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaService.findById(id);
        if (categoria.isPresent()) {
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
        Categoria novaCategoria = categoriaService.save(categoria);
        return new ResponseEntity<>(novaCategoria, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        Optional<Categoria> categoriaExiste = categoriaService.findById(id);
        if (categoriaExiste.isPresent()) {
            Categoria categoriaAtualizada = categoriaExiste.get();
            categoriaAtualizada.setNome(categoria.getNome());
            Categoria atualizada = categoriaService.save(categoriaAtualizada);
            return new ResponseEntity<>(atualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Método para buscar categorias por nome
    @GetMapping("/buscarPorNome")
    public ResponseEntity<List<Categoria>> buscarPorNome(@RequestParam String nome) {
        List<Categoria> categorias = categoriaService.findByNome(nome);
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }
}