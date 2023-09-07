package api_filmes.domain.dto.user;

import java.util.Date;

public class UserRequestDTO {
    private String name;
    private String email;
    private String password;
    private Date inactivationDate;
    private Date activationDate;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getInactivationDate() {
        return inactivationDate;
    }
    public void setInactivationDate(Date inactivationDate) {
        this.inactivationDate = inactivationDate;
    }
    public Date getActivationDate() {
        return activationDate;
    }
    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }
        
    
}
