package api_filmes.domain.entities;

import java.util.Date;
import java.util.List;

public class User {
    private String id;
    private String name;
    private String password;
    private Date activationDate;
    private Date inactivationDate;
    private List<Movie> movies;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getActivationDate() {
        return activationDate;
    }
    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }
    public Date getInactivationDate() {
        return inactivationDate;
    }
    public void setInactivationDate(Date inactivationDate) {
        this.inactivationDate = inactivationDate;
    }

    
}
