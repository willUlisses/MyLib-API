package br.com.will.MyLibraryApi.Security;

import br.com.will.MyLibraryApi.Repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class TokenFilter extends OncePerRequestFilter {

    private final UserRepository repository;
    private final TokenService tokenService;

    public TokenFilter(UserRepository repository, TokenService tokenService) {
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recoverToken(request); // we recover the token without the prefix
        String login = tokenService.verifyToken(token); // we read the token  and extract the user login
        UserDetails user = repository.findByLogin(login); //we find a user with this login and return a UserDetails

        if (user != null) {
            var authenticatedUser = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String requestAuthHeader = request.getHeader("Authorization");
        if (requestAuthHeader == null) return null;

        return requestAuthHeader.replace("Bearer ", "");
    }

}

