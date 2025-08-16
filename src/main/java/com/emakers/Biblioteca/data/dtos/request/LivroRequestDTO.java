package com.emakers.Biblioteca.data.dtos.request;

public record LivroRequestDTO(

        String nome,

        String autor,

        int lancamento,

        int quantidade

) {
}
