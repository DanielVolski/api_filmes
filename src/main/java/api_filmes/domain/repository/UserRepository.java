package api_filmes.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import api_filmes.domain.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
}