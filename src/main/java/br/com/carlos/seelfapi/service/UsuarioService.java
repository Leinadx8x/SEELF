package br.com.carlos.seelfapi.service;

import br.com.carlos.seelfapi.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario salvar(Usuario usuario);
    List<Usuario> buscarTodos();
    Optional<Usuario> buscarPorId(Long id);
    void deletar(Long id);
    Usuario atualizar(Long id, Usuario usuarioDetails);
}