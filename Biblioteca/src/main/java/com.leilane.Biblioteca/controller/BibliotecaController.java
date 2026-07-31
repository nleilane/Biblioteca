package com.leilane.Biblioteca.controller;

import com.leilane.Biblioteca.model.Emprestimo;
import com.leilane.Biblioteca.model.Livro;
import com.leilane.Biblioteca.repository.EmprestimoRepository;
import com.leilane.Biblioteca.repository.LivroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;


@RestController
public class BibliotecaController {

    private final LivroRepository livroRepository;
    private final EmprestimoRepository emprestimoRepository;


    public BibliotecaController(LivroRepository livroRepository, EmprestimoRepository emprestimoRepository) {
        this.livroRepository = livroRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    @GetMapping("/livros")
    public Object[] registeredBook() {

        return livroRepository.findAll().toArray();
    }

    @PostMapping("/addBook")
    public Livro addBook(
            @RequestBody
            Livro bookToBeAdd
    ) {
        bookToBeAdd.setAvailable(true);
        livroRepository.save(bookToBeAdd);
        return bookToBeAdd;

    }

    @DeleteMapping("/removeBook/{idToBeRemoved}")
    public void removeBook(@PathVariable int idToBeRemoved) {
        if (!livroRepository.existsById(idToBeRemoved)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não existe");
        }
        if(emprestimoRepository.existsByLivroId(idToBeRemoved)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O livro está emprestado e não pode ser removido.");
        }
        livroRepository.deleteById(idToBeRemoved);
        System.out.println("Livro " + idToBeRemoved + " Removido!");

    }

    @GetMapping("/livros/title/{title}")
    public List<Livro> findByTitle(@PathVariable String title) {
        List<Livro> livros = livroRepository.findByTitleContaining(title);
        if(livros.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum livro encontrado com esse título.");
        }
            return livros;
    }

    @GetMapping("/livros/author/{author}")
    public List<Livro> findByAuthor(@PathVariable String author){
        List<Livro> livros = livroRepository.findByAuthorContaining(author);
        if (livros.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum autor encontrado com esse nome.");
        }
            return livros;
    }

    @GetMapping("/livros/available")
    public List<Livro> findByAvailableTrue(){
        List<Livro> livros = livroRepository.findByAvailableTrue();
        if(livros.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum livro disponível.");
        }
        return livros;
    }

    @GetMapping("/livros/gender/{genre}")
    public List<Livro> findByGenre(@PathVariable String genre){
        List<Livro> livros = livroRepository.findByGender(genre);
        if(livros.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum livro do gênero foi encontrado.");
        }
        return livros;
    }
}