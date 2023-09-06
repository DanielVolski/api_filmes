package api_filmes.security;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import api_filmes.common.DateConverter;
import api_filmes.domain.dto.login.LoginRequestDTO;
import api_filmes.domain.dto.login.LoginResponseDTO;
import api_filmes.domain.dto.user.UserResponseDTO;
import api_filmes.domain.entities.ResponseError;
import api_filmes.domain.entities.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("api/auth");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            LoginRequestDTO login = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDTO.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()); 
            Authentication auth = authenticationManager.authenticate(authToken);
            return auth;
        }
        catch(BadCredentialsException e) {
            throw new BadCredentialsException("Usuário ou senha inválidos");
        }
        catch(Exception e) {
            throw new InternalAuthenticationServiceException(e.getMessage());
        }   
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException{
        User user = (User) authResult.getPrincipal();
        String token = jwtUtil.generateToken(authResult);
        UserResponseDTO userResponse = new UserResponseDTO();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setActivationDate(user.getActivationDate());
        userResponse.setInactivationDate(user.getInactivationDate());
        LoginResponseDTO loginResponse = new LoginResponseDTO();
        loginResponse.setToken("Bearer " + token);
        loginResponse.setUser(userResponse);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(loginResponse));
    }    

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException{
        String dateTime = DateConverter.convertDateToDateTime(new Date());
        ResponseError error = new ResponseError(dateTime, 401, "Não autorizado", failed.getMessage());
        response.setStatus(401);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(error));
    }
}
