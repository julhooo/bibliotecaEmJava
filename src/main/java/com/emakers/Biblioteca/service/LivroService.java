package com.emakers.Biblioteca.service;

import com.emakers.Biblioteca.data.dtos.request.LivroRequestDTO;
import com.emakers.Biblioteca.data.dtos.response.LivroResponseDTO;
import com.emakers.Biblioteca.data.entity.Livro;
import com.emakers.Biblioteca.data.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<LivroResponseDTO> getAllLivros(){
        List <Livro> livros = livroRepository.findAll();

        return livros.stream().map(LivroResponseDTO::new).collect(Collectors.toList());
    }

    public LivroResponseDTO getLivroByid(Long idLivro){
        Livro livro = returnLivro(idLivro);

        return new LivroResponseDTO(livro);
    }

    public LivroResponseDTO createLivro(LivroRequestDTO livroRequestDTO){
        Livro livro = new Livro(livroRequestDTO);
        livroRepository.save(livro);

        return new LivroResponseDTO(livro);
    }

    public LivroResponseDTO updateLivro(Long idLivro, LivroRequestDTO livroRequestDTO){
        Livro livro = returnLivro(idLivro);

        livro.setAutor(livroRequestDTO.autor());
        livro.setNome(livroRequestDTO.nome());
        livro.setLancamento(livro.getLancamento());

        livroRepository.save(livro);
        return new LivroResponseDTO(livro);
    }

    public String deleteLivro (Long idLivro) {
        Livro livro = returnLivro(idLivro);
        if (livro.getEmprestados() != 0) {
            return "O livro não pode ser removido enquanto estiver emprestado";
        } else {
            livroRepository.delete(livro);

            return "Livro " + idLivro + " deletado com sucesso!";
        }}

    public Livro returnLivro(Long idLivro){
        return livroRepository.findById(idLivro).orElseThrow(()->new RuntimeException("Livro não encontrado"));
    }

}
