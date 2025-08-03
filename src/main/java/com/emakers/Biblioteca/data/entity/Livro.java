package com.emakers.Biblioteca.data.entity;


import com.emakers.Biblioteca.data.dtos.request.LivroRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
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

    @Builder
    public Livro(LivroRequestDTO livrorequestdto){
        this.nome = livrorequestdto.nome();
        this.autor = livrorequestdto.autor();
        this.lancamento = livrorequestdto.lancamento();
    }

}
