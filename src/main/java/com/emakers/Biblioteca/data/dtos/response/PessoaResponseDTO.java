package com.emakers.Biblioteca.data.dtos.response;

import com.emakers.Biblioteca.data.entity.Pessoa;

public record PessoaResponseDTO(

        String nome,

        String email,

        String cpf,

        int emprestimos


) {
    public PessoaResponseDTO(Pessoa pessoa){
        this(
                pessoa.getNome(),
                pessoa.getEmail(),
                pessoa.getCpf(),
                pessoa.getEmprestimos()
        );
    }
}
