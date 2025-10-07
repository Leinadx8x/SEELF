package br.com.carlos.seelfapi.service;

import br.com.carlos.seelfapi.model.MovimentacaoEstoque;
import java.util.List;

public interface MovimentacaoEstoqueService {
    MovimentacaoEstoque registrar(MovimentacaoEstoque movimentacao);
    List<MovimentacaoEstoque> buscarTodas();
}