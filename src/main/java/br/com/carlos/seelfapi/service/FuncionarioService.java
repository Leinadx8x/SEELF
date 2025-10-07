package br.com.carlos.seelfapi.service;

import br.com.carlos.seelfapi.model.Funcionario;
import java.util.List;
import java.util.Optional;

public interface FuncionarioService {
    Funcionario salvar(Funcionario funcionario);
    List<Funcionario> buscarTodos();
    Optional<Funcionario> buscarPorId(Long id);
    void deletar(Long id);
    Funcionario atualizar(Long id, Funcionario funcionarioDetails);
}