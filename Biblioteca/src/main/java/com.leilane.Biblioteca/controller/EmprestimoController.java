package com.leilane.Biblioteca.controller;

import com.leilane.Biblioteca.repository.EmprestimoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmprestimoController {

    private final EmprestimoRepository emprestimoRepository;


    public EmprestimoController(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;

    }

    @GetMapping("/emprestimos")
    public Object[] registerLoan(){

        return registerLoan();
    }
}
