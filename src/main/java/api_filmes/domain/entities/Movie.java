package api_filmes.domain.entities;

import java.util.Date;
import java.util.List;

public class Movie {
    private Long id;
    private String title;
    private String director;
    private Date releaseDate;
    private List<String> writers;
    private List<String> genres;
    private List<String> stars;
    
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
    public List<String> getWriters() {
        return writers;
    }
    public void setWriters(List<String> writers) {
        this.writers = writers;
    }
    public List<String> getGenres() {
        return genres;
    }
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
    public List<String> getStars() {
        return stars;
    }
    public void setStars(List<String> stars) {
        this.stars = stars;
    }

    
}
