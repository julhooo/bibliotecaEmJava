package com.emakers.Biblioteca.data.dtos.request;

public record PessoaRequestDTO(

        Long idPessoa,

        String nome,

        String email,

        String cpf

) {
}
