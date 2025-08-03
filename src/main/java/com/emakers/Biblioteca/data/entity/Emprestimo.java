package com.emakers.Biblioteca.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "emprestimo")

public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "idPessoa")
    private Pessoa pessoa;

    @ManyToOne()
    @JoinColumn(name = "idLivro")
    private Livro livro;

}