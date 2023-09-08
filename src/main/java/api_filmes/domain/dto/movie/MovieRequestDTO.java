package api_filmes.domain.dto.movie;

import java.util.Date;

public class MovieRequestDTO {
    private Long id;
    private String title;
    private String director;
    private String writer;
    private String genre;
    private Date releaseDate;
    private Date registeredAt;
    
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
    
    public String getWriter() {
        return writer;
    }
    public void setWriter(String writer) {
        this.writer = writer;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public Date getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    public Date getRegisteredAt() {
        return registeredAt;
    }
    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }
    
}
