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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private LivroRepository livroRepository;

    public List<EmprestimoResponseDTO> getAllEmprestimos(){
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();

        return emprestimos.stream().map(EmprestimoResponseDTO::new).collect(Collectors.toList());
    }

    public EmprestimoResponseDTO getEmprestimoById(Long idEmprestimo){
        Emprestimo emprestimo = returnEmprestimo(idEmprestimo);

        return new EmprestimoResponseDTO(emprestimo);
    }

    public List<EmprestimoResponseDTO> getAllEmprestimoById(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetails) auth.getPrincipal()).getUsername();

        List<Emprestimo> todosEmprestimos = emprestimoRepository.findAll();

        List<EmprestimoResponseDTO> emprestimos = new ArrayList<>();
        for (Emprestimo e : todosEmprestimos) {
            if (e.getPessoa().getEmail().equals(email)) {
                emprestimos.add(new EmprestimoResponseDTO(e));
            }
        }
        return emprestimos;
    }

    public String createEmprestimo(EmprestimoRequestDTO emprestimoRequestDTO){
        Pessoa pessoa = returnPessoa(emprestimoRequestDTO);
        Livro livro = returnLivro(emprestimoRequestDTO);
        if(!livro.isDisponivel()){
            return "Todos estão emprestados";
        }
        if(pessoa.getEmprestimos()==5){
            return "Limite de empréstimos atingido";
        }
        else {

            livro.setEmprestados(livro.getEmprestados()+1);
            if(livro.getEmprestados()==livro.getQuantidade()){
                livro.setDisponivel(false);
            }
            livroRepository.save(livro);
            pessoa.setEmprestimos(pessoa.getEmprestimos()+1);
            pessoaRepository.save(pessoa);
            Emprestimo emprestimo = new Emprestimo(pessoa, livro);
            emprestimoRepository.save(emprestimo);

            return "Empréstimo realizado com sucesso!";
        }
    }

    public EmprestimoResponseDTO updateEmprestimo(Long idEmprestimo, EmprestimoRequestDTO emprestimoRequestDTO){
        Emprestimo emprestimo = returnEmprestimo(idEmprestimo);

        emprestimo.setPessoa(returnPessoa(emprestimoRequestDTO));
        emprestimo.setLivro(returnLivro(emprestimoRequestDTO));

        emprestimoRepository.save(emprestimo);

        return new EmprestimoResponseDTO(emprestimo);
    }

    public String  renovar(Long idEmprestimo){
        Emprestimo emprestimo = returnEmprestimo(idEmprestimo);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetails) auth.getPrincipal()).getUsername();

        if(!Objects.equals(emprestimo.getUsername(), email)){
            return "Empréstimo de outro usuário";
        } else {
            emprestimo.setDatadevolucao(emprestimo.getDatadevolucao().plusDays(7));
            emprestimoRepository.save(emprestimo);
            return "Renovação concluída, nova data de devolução: " + emprestimo.getDatadevolucao();
        }
    }

    public String deleteEmprestimo(Long idEmprestimo){
        Emprestimo emprestimo = returnEmprestimo(idEmprestimo);
        Pessoa pessoa = emprestimo.getPessoa();
        Livro livro = emprestimo.getLivro();

        livro.setEmprestados(livro.getEmprestados()-1);
        livro.setDisponivel(true);
        livroRepository.save(livro);
        pessoa.setEmprestimos(pessoa.getEmprestimos()-1);
        pessoaRepository.save(pessoa);

        emprestimoRepository.delete(emprestimo);

        return "Empréstimo " + idEmprestimo + " foi finalizado!";
    }

    public Pessoa returnPessoa (EmprestimoRequestDTO emprestimoRequestDTO){
        return pessoaRepository.findById(emprestimoRequestDTO.idpessoa()).orElseThrow(()-> new RuntimeException("Pessoa não encontrada."));
    }
    public Livro returnLivro (EmprestimoRequestDTO emprestimoRequestDTO){
        return livroRepository.findById(emprestimoRequestDTO.idlivro()).orElseThrow(()-> new RuntimeException("Livro não encontrado."));
    }
    public Emprestimo returnEmprestimo (Long idEmprestimo){
        return emprestimoRepository.findById(idEmprestimo).orElseThrow(()-> new RuntimeException("Empréstimo não encontrado."));
    }
}
