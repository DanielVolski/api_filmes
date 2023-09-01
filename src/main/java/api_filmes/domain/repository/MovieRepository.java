package api_filmes.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import api_filmes.domain.entities.Movie;


public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findById(Long id);
}
