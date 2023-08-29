package api_filmes.domain.entities;

import java.util.Date;
import java.util.List;

public class Movie {
    private Long id;
    private String title;
    private String director;
    private Date releaseDate;   
    private List<Movie> Movies;
    
    public List<Movie> getMovies() {
        return Movies;
    }
    public void setMovies(List<Movie> movies) {
        Movies = movies;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public Date getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    
}
