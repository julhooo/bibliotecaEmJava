package com.emakers.Biblioteca.data.repository;

import com.emakers.Biblioteca.data.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo,Long>{
    List<Emprestimo> findByPessoa_IdPessoa(Long idpessoa);
}
