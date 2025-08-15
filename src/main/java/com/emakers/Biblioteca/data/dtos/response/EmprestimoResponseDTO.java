package com.emakers.Biblioteca.data.dtos.response;

import com.emakers.Biblioteca.data.entity.Emprestimo;
import com.emakers.Biblioteca.data.entity.Livro;
import com.emakers.Biblioteca.data.entity.Pessoa;

import java.time.LocalDate;

public record EmprestimoResponseDTO(

        String nomepessoa,
        String email,
        String nomelivro,
        String autor,
        LocalDate datadevolucao

) {
    public EmprestimoResponseDTO(Emprestimo emprestimo) {
        this(
                emprestimo.getPessoa().getNome(),
                emprestimo.getPessoa().getEmail(),
                emprestimo.getLivro().getNome(),
                emprestimo.getLivro().getAutor(),
                emprestimo.getDatadevolucao()
        );
    }
}
