package br.com.carlos.seelfapi.controller;

import br.com.carlos.seelfapi.model.Usuario;
import br.com.carlos.seelfapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listarTodosUsuarios() {
        return usuarioService.buscarTodos();
    }


}