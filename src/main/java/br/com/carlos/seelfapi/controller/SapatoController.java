package br.com.carlos.seelfapi.controller;

import br.com.carlos.seelfapi.model.Sapato;
import br.com.carlos.seelfapi.service.SapatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sapatos")
public class SapatoController {

    @Autowired
    private SapatoService sapatoService;

    // Endpoint para LISTAR TODOS os sapatos
    @GetMapping
    public List<Sapato> listarTodosSapatos() {
        return sapatoService.buscarTodos();
    }

    // Endpoint para BUSCAR UM sapato POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Sapato> buscarSapatoPorId(@PathVariable Long id) {
        Optional<Sapato> sapato = sapatoService.buscarPorId(id);
        return sapato.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para CADASTRAR um novo sapato
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sapato cadastrarNovoSapato(@RequestBody Sapato sapato) {
        return sapatoService.salvar(sapato);
    }

    // Endpoint para ATUALIZAR um sapato existente
    @PutMapping("/{id}")
    public ResponseEntity<Sapato> atualizarSapato(@PathVariable Long id, @RequestBody Sapato sapatoDetails) {
        try {
            Sapato sapatoAtualizado = sapatoService.atualizar(id, sapatoDetails);
            return ResponseEntity.ok(sapatoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para DELETAR um sapato
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarSapato(@PathVariable Long id) {
        sapatoService.deletar(id);
    }
}