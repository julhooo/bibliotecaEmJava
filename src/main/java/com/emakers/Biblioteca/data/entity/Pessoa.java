package com.emakers.Biblioteca.data.entity;


import com.emakers.Biblioteca.data.dtos.request.CreatePessoaDTO;
import com.emakers.Biblioteca.data.dtos.request.PessoaRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.UserDatabase;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name ="pessoa")

public class Pessoa implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPessoa;

    @Column (name = "nome", nullable = false, length = 100)
    private String nome;

    @Column (name = "email", nullable = false, length = 100)
    private String email;

    @Column (name = "senha", nullable = false)
    private String senha;

    @Column (name = "cpf", nullable = false, length = 11)
    private String cpf;

    @Column (name = "role", nullable = false, length = 4)
    private PessoaRole role;

    @Builder
    public Pessoa(CreatePessoaDTO createPessoaDTO) {
        this.nome = createPessoaDTO.nome();
        this.email = createPessoaDTO.email();
        this.cpf = createPessoaDTO.cpf();
        this.senha = createPessoaDTO.senha();
        this.role = createPessoaDTO.role();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == PessoaRole.ADMIN) return List.of(new SimpleGrantedAuthority( "ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
