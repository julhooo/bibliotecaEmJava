package com.emakers.Biblioteca.data.dtos.response;

import com.emakers.Biblioteca.data.entity.Livro;

public record LivroResponseDTO(

        String nome,

        String autor,

        int lancamento,

        int quantidade,

        boolean disponivel

) {
    public LivroResponseDTO(Livro livro){
        this(
                livro.getNome(),
                livro.getAutor(),
                livro.getLancamento(),
                livro.getQuantidade(),
                livro.isDisponivel()
        );
    }
}
