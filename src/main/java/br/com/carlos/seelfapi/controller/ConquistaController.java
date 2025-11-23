package br.com.carlos.seelfapi.controller;

import br.com.carlos.seelfapi.model.Conquista;
import br.com.carlos.seelfapi.repository.ConquistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conquistas")
public class ConquistaController {

    @Autowired
    private ConquistaRepository conquistaRepository;

    // Endpoint para o Front-end buscar as medalhas do usuário
    @GetMapping("/user/{usuarioId}")
    public List<Conquista> listarConquistasDoUsuario(@PathVariable Long usuarioId) {
        return conquistaRepository.findByUsuarioId(usuarioId);
    }
}