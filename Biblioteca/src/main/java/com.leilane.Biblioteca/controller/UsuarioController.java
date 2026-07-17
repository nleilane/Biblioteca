package com.leilane.Biblioteca.controller;

import com.leilane.Biblioteca.model.Usuario;
import com.leilane.Biblioteca.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/usuarios")
    public Object[] registeredUsers() {

        return usuarioRepository.findAll().toArray();
    }

    @PostMapping("/addUser")
    public Usuario addUser(
            @RequestBody
            Usuario userToBeAdd
    ) {
        usuarioRepository.save(userToBeAdd);
        return userToBeAdd;

    }

    @DeleteMapping("/removeUser/{idToBeRemoved}")
    public void removeUser(@PathVariable int idToBeRemoved) {
        if (usuarioRepository.existsById(idToBeRemoved)) {
            usuarioRepository.deleteById(idToBeRemoved);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuáruio não existe");
        }
        System.out.println("Usuário " + idToBeRemoved + "Removido!");
    }
}
