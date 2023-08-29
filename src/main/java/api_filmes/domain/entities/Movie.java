package api_filmes.domain.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
@Entity
public class Movie {
    
    private Long id;
    @Column
    private String title;
    @Column
    private String director;
    @Column
    private Date releaseDate;   

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
