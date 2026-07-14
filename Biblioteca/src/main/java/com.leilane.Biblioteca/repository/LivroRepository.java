package com.leilane.Biblioteca.repository;

import com.leilane.Biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Integer>{   //interface

}
