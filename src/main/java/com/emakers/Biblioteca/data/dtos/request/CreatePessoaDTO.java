package com.emakers.Biblioteca.data.dtos.request;

import com.emakers.Biblioteca.data.entity.PessoaRole;

public record CreatePessoaDTO(

        String nome,

        String cpf,

        String email,

        String senha,

        PessoaRole role

) {
}
