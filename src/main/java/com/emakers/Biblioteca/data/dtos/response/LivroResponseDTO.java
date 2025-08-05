package com.emakers.Biblioteca.data.dtos.response;

import com.emakers.Biblioteca.data.entity.Livro;

public record LivroResponseDTO(

        String nome,

        String autor,

        int lancamento

) {
    public LivroResponseDTO(Livro livro){
        this(
                livro.getNome(),
                livro.getAutor(),
                livro.getLancamento()
        );
    }
}
