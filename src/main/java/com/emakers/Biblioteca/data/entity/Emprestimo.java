package com.emakers.Biblioteca.data.entity;

import com.emakers.Biblioteca.data.dtos.request.EmprestimoRequestDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    @Column (name = "DataRetirada")
    LocalDate data = LocalDate.now();

    @Column (name = "DataDevolucao")
    LocalDate datadevolucao = data.plusDays(7);

    @Column (name = "username")
    String username;

    @Builder
    public Emprestimo(Pessoa pessoa, Livro livro){
        this.pessoa = pessoa;
        this.livro = livro;
        username = pessoa.getUsername();
    }

}