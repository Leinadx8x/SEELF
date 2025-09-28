package br.com.carlos.seelfapi.service;

import br.com.carlos.seelfapi.model.Sapato;
import java.util.List;
import java.util.Optional;

public interface SapatoService {

    Sapato salvar(Sapato sapato);

    List<Sapato> buscarTodos();

    Optional<Sapato> buscarPorId(Long id);

    void deletar(Long id);

    Sapato atualizar(Long id, Sapato sapatoDetails);
}