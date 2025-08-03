package com.emakers.Biblioteca.data.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name ="pessoa")

public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPessoa;

    @Column (name = "nome", nullable = false, length = 100)
    private String nome;

    @Column (name = "email", nullable = false, length = 100)
    private String email;

    @Column (name = "senha", nullable = false, length = 20)
    private String senha;

    @Column (name = "cpf", nullable = false, length = 11)
    private String cpf;

}
