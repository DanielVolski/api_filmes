/* package api_filmes.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import api_filmes.domain.dto.movie.MovieRequestDTO;
import api_filmes.domain.dto.movie.MovieResponseDTO;
import api_filmes.domain.entities.Movie;
import api_filmes.domain.entities.User;
import api_filmes.domain.exception.ResourceNotFoundException;
import api_filmes.domain.repository.MovieRepository;

@Service
public class MovieService implements ICRUDService<MovieRequestDTO, MovieResponseDTO> {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<MovieResponseDTO> getAll() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Movie> movies = movieRepository.findByUser(user);

        return movies.stream().map(
            movie -> mapper.map(movies, MovieResponseDTO.class)).collect(Collectors.toList()
        );     
    }

    @Override
    public MovieResponseDTO getById(Long id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);

        if (!optionalMovie.isPresent())
            throw new ResourceNotFoundException("Filme não encontrado!");

        return mapper.map(optionalMovie.get(), MovieResponseDTO.class);
    }

    @Override
    public MovieResponseDTO create(MovieRequestDTO dto) {
        Movie movie = mapper.map(dto, Movie.class);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        movie.setUser(user);
        movie.setRegisteredAt(new Date());
        movie.setId(null);
        movie = movieRepository.save(movie);

        return mapper.map(movie, MovieResponseDTO.class);
    }

    @Override
    public MovieResponseDTO update(Long id, MovieRequestDTO dto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MovieResponseDTO movieFromDB = getById(id);
        Movie movie = mapper.map(dto, Movie.class);

        movie.setUser(user);
        movie.setRegisteredAt(movieFromDB.getRegisteredAt());
        movie.setId(null);
        movie = movieRepository.save(movie);

        return mapper.map(movie, MovieResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        movieRepository.deleteById(id);
    }
}
 */