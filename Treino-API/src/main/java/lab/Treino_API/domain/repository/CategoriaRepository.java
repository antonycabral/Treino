package lab.Treino_API.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lab.Treino_API.domain.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByNomeContainingIgnoreCase(String nome);
}
