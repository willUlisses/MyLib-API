package br.com.will.MyLibraryApi.Controller;

import br.com.will.MyLibraryApi.Model.DTOs.AuthenticationDTO;
import br.com.will.MyLibraryApi.Model.DTOs.LoginResponseDTO;
import br.com.will.MyLibraryApi.Model.DTOs.UserDTO;
import br.com.will.MyLibraryApi.Model.User;
import br.com.will.MyLibraryApi.Repository.AuthorRepository;
import br.com.will.MyLibraryApi.Repository.UserRepository;
import br.com.will.MyLibraryApi.Security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final UserRepository userRepository;
    private final AuthorRepository authorRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(UserRepository repository, AuthenticationManager authenticationManager, TokenService tokenService,
                          AuthorRepository authorRepository) {
        this.userRepository = repository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.authorRepository = authorRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserDTO data) {
        if (userRepository.findByLogin(data.login()) != null) // we see if there's no user with the login passed via body
            return ResponseEntity.badRequest().build();

        User newUser = new User(data.login(), data.password(), data.role()); //we get all the stuff in the body and create a user
        userRepository.save(newUser); // we save this user in the database
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                data.login(),
                data.password()
        ); //get all user stuff into a usernamePasswordToken
        Authentication authenticationManager = this.authenticationManager.authenticate(usernamePassword); // we auth this usernamePassword

        String token = tokenService.generateToken((User) authenticationManager.getPrincipal()); // we generate the JWT with the user
        return ResponseEntity.ok(new LoginResponseDTO(token)); //we return the token at the body and an OK http status
    }
}
