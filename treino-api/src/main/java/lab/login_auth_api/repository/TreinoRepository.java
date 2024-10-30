package lab.login_auth_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lab.login_auth_api.domain.Treino;

public interface TreinoRepository extends JpaRepository<Treino, String> {
    // Adicione consultas personalizadas se necessário
}
