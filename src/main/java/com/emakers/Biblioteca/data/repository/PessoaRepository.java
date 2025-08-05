package com.emakers.Biblioteca.data.repository;

import com.emakers.Biblioteca.data.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
}
