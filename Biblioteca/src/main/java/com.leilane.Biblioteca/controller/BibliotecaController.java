package com.leilane.Biblioteca.controller;

import com.leilane.Biblioteca.model.Livro;
import com.leilane.Biblioteca.repository.LivroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
public class BibliotecaController {

    private final LivroRepository livroRepository;


    public BibliotecaController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;

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
        if (livroRepository.existsById(idToBeRemoved)) {
            livroRepository.deleteById(idToBeRemoved);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não existe");
        }
        System.out.println("Livro " + idToBeRemoved + " Removido!");

    }

    @GetMapping("/livros/title/{title}")
    public List<Livro> findByTitle(@PathVariable String title) {
        return livroRepository.findByTitleContainingIgnoreCase(title);
    }


}