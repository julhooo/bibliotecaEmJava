package com.emakers.Biblioteca.controller;

import com.emakers.Biblioteca.data.dtos.request.PessoaRequestDTO;
import com.emakers.Biblioteca.data.dtos.response.PessoaResponseDTO;
import com.emakers.Biblioteca.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<PessoaResponseDTO>> getAllPessoas(){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getallpessoas());
    }

    @GetMapping(value = "/{idPessoa}")
    public ResponseEntity<PessoaResponseDTO> getPessoaById(@PathVariable Long idPessoa){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getPessoaById(idPessoa));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<PessoaResponseDTO> createPessoa(@RequestBody PessoaRequestDTO pessoaRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.createPessoa(pessoaRequestDTO));
    }

    @PutMapping(value = "/update/{idPessoa}")
    public ResponseEntity<PessoaResponseDTO> updatePessoa(@PathVariable Long idPessoa, @RequestBody PessoaRequestDTO pessoaRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.updatePessoa(idPessoa,pessoaRequestDTO));
    }

    @DeleteMapping(value = "/delete/{idPessoa}")
    public ResponseEntity<String> deletePessoa (@PathVariable Long idPessoa){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.deletePessoa(idPessoa));
    }

}
