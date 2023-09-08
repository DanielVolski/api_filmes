package api_filmes.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import api_filmes.domain.entities.Movie;
import api_filmes.domain.entities.User;


public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findById(Long id);
    List<Movie> findByUser(User user);
}
