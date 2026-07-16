package com.leilane.Biblioteca.repository;

import com.leilane.Biblioteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Integer> {
}
