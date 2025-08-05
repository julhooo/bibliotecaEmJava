package com.emakers.Biblioteca.service;

import com.emakers.Biblioteca.data.dtos.request.EmprestimoRequestDTO;
import com.emakers.Biblioteca.data.dtos.response.EmprestimoResponseDTO;
import com.emakers.Biblioteca.data.entity.Emprestimo;
import com.emakers.Biblioteca.data.entity.Livro;
import com.emakers.Biblioteca.data.entity.Pessoa;
import com.emakers.Biblioteca.data.repository.EmprestimoRepository;
import com.emakers.Biblioteca.data.repository.LivroRepository;
import com.emakers.Biblioteca.data.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;
    private PessoaRepository pessoaRepository;
    private LivroRepository livroRepository;

    public List<EmprestimoResponseDTO> getAllEmprestimos(){
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();

        return emprestimos.stream().map(EmprestimoResponseDTO::new).collect(Collectors.toList());
    }

    public EmprestimoResponseDTO getEmprestimoById(Long idEmprestimo){
        Emprestimo emprestimo = returnEmprestimo(idEmprestimo);

        return new EmprestimoResponseDTO(emprestimo);
    }

    public EmprestimoResponseDTO createEmprestimo(EmprestimoRequestDTO emprestimoRequestDTO){
        Pessoa pessoa = returnPessoa(emprestimoRequestDTO);
        Livro livro = returnLivro(emprestimoRequestDTO);
        Emprestimo emprestimo = new Emprestimo(pessoa, livro);

        emprestimoRepository.save(emprestimo);

        return new EmprestimoResponseDTO(emprestimo);
    }

    public EmprestimoResponseDTO updateEmprestimo(Long idEmprestimo, EmprestimoRequestDTO emprestimoRequestDTO){
        Emprestimo emprestimo = returnEmprestimo(idEmprestimo);

        emprestimo.setPessoa(returnPessoa(emprestimoRequestDTO));
        emprestimo.setLivro(returnLivro(emprestimoRequestDTO));

        emprestimoRepository.save(emprestimo);

        return new EmprestimoResponseDTO(emprestimo);
    }

    public String deleteEmprestimo(Long idEmprestimo){
        Emprestimo emprestimo = returnEmprestimo(idEmprestimo);
        emprestimoRepository.delete(emprestimo);

        return "Empréstimo " + idEmprestimo + " foi deletado!";
    }

    public Pessoa returnPessoa (EmprestimoRequestDTO emprestimoRequestDTO){
        return pessoaRepository.findById(emprestimoRequestDTO.idpessoa()).orElseThrow(()-> new RuntimeException("Pessoa não encontrada."));
    }
    public Livro returnLivro (EmprestimoRequestDTO emprestimoRequestDTO){
        return livroRepository.findById(emprestimoRequestDTO.idpessoa()).orElseThrow(()-> new RuntimeException("Livro não encontrado."));
    }
    public Emprestimo returnEmprestimo (Long idEmprestimo){
        return emprestimoRepository.findById(idEmprestimo).orElseThrow(()-> new RuntimeException("Empréstimo não encontrado."));
    }
}
