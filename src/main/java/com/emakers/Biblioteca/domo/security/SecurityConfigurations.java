package com.emakers.Biblioteca.domo.security;

import com.emakers.Biblioteca.controller.AuthenticationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                        "/swagger-ui.html",
                       "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/v3/api-docs.yaml"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/pessoa/create").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/pessoa/{idPessoa}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/pessoa/all").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/pessoa/update/{idPessoa}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/pessoa/delete/{idPessoa}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/livro/all").authenticated()
                        .requestMatchers(HttpMethod.GET, "/livro/{idLivro}").authenticated()
                        .requestMatchers(HttpMethod.POST, "/livro/create").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/livro/update/{idLivro}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/livro/delete/{idLivro}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/emprestimo/meusemprestimos").authenticated()
                        .requestMatchers(HttpMethod.GET, "/emprestimo/all").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/emprestimo/{idEmprestimo}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/emprestimo/create").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/emprestimo/renovar/{idEmprestimo}").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/emprestimo/update/{idEmprestimo}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/emprestimo/delete/{idEmprestimo}").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
