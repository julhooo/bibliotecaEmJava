package com.emakers.Biblioteca.data.dtos.request;

import com.emakers.Biblioteca.data.entity.Livro;
import com.emakers.Biblioteca.data.entity.Pessoa;

public record EmprestimoRequestDTO(

    Pessoa pessoa,

    Livro livro

) {
}
