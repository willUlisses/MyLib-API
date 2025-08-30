package br.com.will.MyLibraryApi.Security;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Value("S{api.security.token.secret}")
    private String secret;





}
