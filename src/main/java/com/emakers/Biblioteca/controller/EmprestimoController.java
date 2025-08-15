package com.emakers.Biblioteca.controller;

import com.emakers.Biblioteca.data.dtos.request.EmprestimoRequestDTO;
import com.emakers.Biblioteca.data.dtos.response.EmprestimoResponseDTO;
import com.emakers.Biblioteca.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<EmprestimoResponseDTO>> getAllEmprestimos(){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.getAllEmprestimos());
    }

    @GetMapping(value = "/{idEmprestimo}")
    public ResponseEntity<EmprestimoResponseDTO> getEmprestimoById(@PathVariable Long idEmprestimo){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.getEmprestimoById(idEmprestimo));
    }

    @GetMapping(value = "/meusemprestimos")
    public ResponseEntity<List<EmprestimoResponseDTO>> getMyEmprestimos(){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.getAllEmprestimoById());
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> createEmprestimo(@RequestBody EmprestimoRequestDTO emprestimoRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.createEmprestimo(emprestimoRequestDTO));
    }

    @PutMapping(value = "/renovar/{idEmprestimo}")
    public ResponseEntity<String> renovarEmprestimo(@PathVariable Long idEmprestimo){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.renovar(idEmprestimo));
    }

    @PutMapping(value = "/update/{idEmprestimo}")
    public ResponseEntity<EmprestimoResponseDTO> updateEmprestimo(@PathVariable Long idEmprestimo, @RequestBody EmprestimoRequestDTO emprestimoRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.updateEmprestimo(idEmprestimo,emprestimoRequestDTO));
    }

    @DeleteMapping(value = "/devolucao/{idEmprestimo}")
    public ResponseEntity<String> deleteEmprestimo(@PathVariable Long idEmprestimo){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.deleteEmprestimo(idEmprestimo));
    }

}
