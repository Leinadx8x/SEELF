package br.com.carlos.seelfapi.service;

import br.com.carlos.seelfapi.model.Produto;
import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    Produto salvar(Produto produto);

    List<Produto> buscarTodos();

    Optional<Produto> buscarPorId(Long id);

    void deletar(Long id);

    Produto atualizar(Long id, Produto produtoDetails);
}