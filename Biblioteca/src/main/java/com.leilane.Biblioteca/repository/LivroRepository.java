package com.leilane.Biblioteca.repository;

import com.leilane.Biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Integer>{
    List<Livro> findByTitleContainingIgnoreCase(String title);   //interface

}
