package com.emakers.Biblioteca.data.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name ="livros")

public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idLivro;

    @Column (name = "nome", nullable = false, length = 100)
    private String nome;

    @Column (name = "autor", nullable = false, length = 100)
    private String autor;

    @Column (name = "lancamento", nullable = false)
    private int lancamento;

}
