package com.leilane.Biblioteca.controller;

import com.leilane.Biblioteca.model.Emprestimo;
import com.leilane.Biblioteca.model.Livro;
import com.leilane.Biblioteca.model.Usuario;
import com.leilane.Biblioteca.repository.EmprestimoRepository;
import com.leilane.Biblioteca.repository.LivroRepository;
import com.leilane.Biblioteca.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.Date;

@RestController
public class EmprestimoController{

    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LivroRepository livroRepository;

    public EmprestimoController(EmprestimoRepository emprestimoRepository, UsuarioRepository usuarioRepository, LivroRepository livroRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioRepository = usuarioRepository;
        this.livroRepository = livroRepository;
    }

    @GetMapping("/emprestimos")
    public Object[] allLoans(){

        return emprestimoRepository.findAll().toArray();
    }

    @PostMapping("/addLoan/{idUser}/{idBook}")
    public Emprestimo addLoan(
            @PathVariable int idUser,
            @PathVariable int idBook
    ){
        Usuario usuario = usuarioRepository.findById(idUser).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.") );
        Livro livro = livroRepository.findById(idBook).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado.") );
            if(!livro.isAvailable()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Livro já está emprestado.");
        }
        livro.setAvailable(false);
        livroRepository.save(livro);
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivro(livro);
        emprestimo.setUsuario(usuario);
        emprestimo.setDateLoan(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,7);
        emprestimo.setDateExpectedReturn(calendar.getTime());
        emprestimoRepository.save(emprestimo);

        return emprestimo;
    }

    @PostMapping("/returnLoan/{idLoan}")
        public Emprestimo returnLoan(@PathVariable int idLoan){
        Emprestimo emprestimo = emprestimoRepository.findById(idLoan).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empréstimo não encontrado.") );
        emprestimo.setDateReturn(new Date());
        Livro livro = emprestimo.getLivro();
        livro.setAvailable(true);
        livroRepository.save(livro);
        emprestimoRepository.save(emprestimo);
        return emprestimo;
    }

    @DeleteMapping("/removeLoan/{idToBeRemoved}")
    public void removeLoan(@PathVariable int idToBeRemoved){
        if(emprestimoRepository.existsById(idToBeRemoved)){
            emprestimoRepository.deleteById(idToBeRemoved);
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O empréstimo não existe.");
        }
        System.out.println("Empréstimo " + idToBeRemoved + " Removido!");
    }
}
