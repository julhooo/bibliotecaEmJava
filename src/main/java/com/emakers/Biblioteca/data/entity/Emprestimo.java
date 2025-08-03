package com.emakers.Biblioteca.data.entity;

import com.emakers.Biblioteca.data.dtos.request.EmprestimoRequestDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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

    @Builder
    public Emprestimo(EmprestimoRequestDTO emprestimorequestdto){
        this.pessoa = emprestimorequestdto.pessoa();
        this.livro = emprestimorequestdto.livro();
    }

}