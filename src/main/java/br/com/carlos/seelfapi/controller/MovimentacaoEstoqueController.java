package br.com.carlos.seelfapi.controller;

import br.com.carlos.seelfapi.model.MovimentacaoEstoque;
import br.com.carlos.seelfapi.service.MovimentacaoEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movements")
public class MovimentacaoEstoqueController {

    @Autowired
    private MovimentacaoEstoqueService movimentacaoEstoqueService;

    @GetMapping
    public List<MovimentacaoEstoque> listarTodasMovimentacoes() {
        return movimentacaoEstoqueService.buscarTodas();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovimentacaoEstoque registrarNovaMovimentacao(@RequestBody MovimentacaoEstoque movimentacao) {
        return movimentacaoEstoqueService.registrar(movimentacao);
    }
}