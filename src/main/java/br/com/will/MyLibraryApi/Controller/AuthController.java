package br.com.will.MyLibraryApi.Controller;

import br.com.will.MyLibraryApi.Model.DTOs.UserDTO;
import br.com.will.MyLibraryApi.Model.User;
import br.com.will.MyLibraryApi.Repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    private UserRepository repository;
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    public AuthController(UserRepository repository, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserDTO data) {
        if (repository.findByLogin(data.login()) != null)
            return ResponseEntity.badRequest().build();

        User newUser = new User(data.login(), data.password(), data.role());
        repository.save(newUser);
        return ResponseEntity.ok(newUser);
    }



}
