package br.com.will.MyLibraryApi.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private TokenFilter filter;

    public SecurityConfig(TokenFilter filter) {
        this.filter = filter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/authors").permitAll()
                        .requestMatchers(HttpMethod.POST, "/authors").hasAnyRole("AUTHOR", "ADMIN")
                )
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                // we add a filter that auths the Username and his password and get his authorities too
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration)
    throws Exception {
        return authConfiguration.getAuthenticationManager(); //geramos nosso authenticationManager
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
