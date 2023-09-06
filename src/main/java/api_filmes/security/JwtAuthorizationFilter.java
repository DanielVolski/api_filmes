package api_filmes.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import api_filmes.domain.entities.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter{

    private JwtUtil jwtUtil;
    private UserDetailsSecurityServer userDetailsSecurityServer;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsSecurityServer userDetailsSecurityServer, JwtUtil jwtUtil) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userDetailsSecurityServer = userDetailsSecurityServer;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            UsernamePasswordAuthenticationToken auth = getAuthenticationToken(header.substring(7));
            if (auth != null && auth.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        if (jwtUtil.isValidToken(token)) {
            String email = jwtUtil.getUserName(token);
            User user = (User) userDetailsSecurityServer.loadUserByUsername(email);
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }
        return null;
    }
}
