package lab.login_auth_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lab.login_auth_api.domain.Exercicio;

public interface ExercicioRepository extends JpaRepository<Exercicio, String> {
    
}
