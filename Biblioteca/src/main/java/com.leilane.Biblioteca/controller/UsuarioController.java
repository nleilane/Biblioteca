package com.leilane.Biblioteca.controller;

import com.leilane.Biblioteca.model.Usuario;
import com.leilane.Biblioteca.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UsuarioController {

    private final UserRepository userRepository;

    public UsuarioController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/usuarios")
    public Object[] registeredName() {

        return registeredName();
    }

    @PostMapping("/addUser")
    public Usuario addUser(
            @RequestBody
            Usuario userToBeAdd
    ) {
        userRepository.save(userToBeAdd);
        return userToBeAdd;

    }

    @DeleteMapping("/removeUser/{idToBeRemoved}")
    public void removeUser(@PathVariable int idToBeRemoved) {
        if (userRepository.existsById(idToBeRemoved)) {
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuáruio não existe");
        }
        System.out.println("Usuário " + idToBeRemoved + "Removido!");
    }
}
