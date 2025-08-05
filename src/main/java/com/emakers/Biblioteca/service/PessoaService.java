package com.emakers.Biblioteca.service;

import com.emakers.Biblioteca.data.dtos.request.PessoaRequestDTO;
import com.emakers.Biblioteca.data.dtos.response.PessoaResponseDTO;
import com.emakers.Biblioteca.data.entity.Pessoa;
import com.emakers.Biblioteca.data.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ListResourceBundle;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<PessoaResponseDTO> getallpessoas(){
        List<Pessoa> pessoas = pessoaRepository.findAll();

        return pessoas.stream().map(PessoaResponseDTO::new).collect(Collectors.toList());
    }

    public PessoaResponseDTO getPessoaById(Long idPessoa){
        Pessoa pessoa = returnPessoa(idPessoa);

        return new PessoaResponseDTO(pessoa);
    }

    public PessoaResponseDTO createPessoa(PessoaRequestDTO pessoaRequestDTO){
        Pessoa pessoa = new Pessoa(pessoaRequestDTO);
        pessoaRepository.save(pessoa);

        return new PessoaResponseDTO(pessoa);
    }

    public PessoaResponseDTO updatePessoa(Long idPessoa,PessoaRequestDTO pessoaRequestDTO){
        Pessoa pessoa = returnPessoa(idPessoa);

        pessoa.setCpf(pessoaRequestDTO.cpf());
        pessoa.setEmail(pessoaRequestDTO.email());
        pessoa.setNome(pessoaRequestDTO.nome());

        pessoaRepository.save(pessoa);
        return new PessoaResponseDTO(pessoa);
    }

    public String deletePessoa (Long idPessoa){
        Pessoa pessoa = returnPessoa(idPessoa);
        pessoaRepository.delete(pessoa);

        return "Usuário " + idPessoa + " deletado com sucesso!";
    }

    public Pessoa returnPessoa (Long idPessoa){
        return pessoaRepository.findById(idPessoa).orElseThrow(()->new RuntimeException("Usuário não cadastrado."));
    }
}
