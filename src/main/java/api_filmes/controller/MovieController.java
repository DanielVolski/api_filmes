package api_filmes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api_filmes.domain.dto.movie.MovieRequestDTO;
import api_filmes.domain.dto.movie.MovieResponseDTO;
import api_filmes.domain.service.MovieService;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin("*")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getAll() {
        List<MovieResponseDTO> teste = movieService.getAll();
        return ResponseEntity.ok(teste);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getById(id));
    }

    @PostMapping
    public ResponseEntity<MovieResponseDTO> create(@RequestBody MovieRequestDTO dto) {
        MovieResponseDTO movie = movieService.create(dto);
        return new ResponseEntity<MovieResponseDTO>(movie, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> update(@PathVariable Long id, @RequestBody MovieRequestDTO dto) {
        MovieResponseDTO movie = movieService.update(id, dto);
        return new ResponseEntity<MovieResponseDTO>(movie, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        movieService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
