package lab.login_auth_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lab.login_auth_api.domain.UserData;

public interface UserDataRepository extends JpaRepository<UserData, String> {
    // Adicione consultas personalizadas se necessário
}
