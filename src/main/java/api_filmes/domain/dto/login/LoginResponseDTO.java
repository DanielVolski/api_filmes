package api_filmes.domain.dto.login;

import api_filmes.domain.dto.user.UserResponseDTO;

public class LoginResponseDTO {
    
    private String token;
    private UserResponseDTO user;
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public UserResponseDTO getUser() {
        return user;
    }
    public void setUser(UserResponseDTO user) {
        this.user = user;
    }

}
