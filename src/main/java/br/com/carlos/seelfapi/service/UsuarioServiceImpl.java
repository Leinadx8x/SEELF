package br.com.carlos.seelfapi.service;

import br.com.carlos.seelfapi.model.Usuario;
import br.com.carlos.seelfapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario salvar(Usuario usuario) {
        // Futuramente, aqui entrará a lógica para criptografar a senha, por exemplo.
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario atualizar(Long id, Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com o id: " + id));

        usuario.setNomeCompleto(usuarioDetails.getNomeCompleto());
        usuario.setEmail(usuarioDetails.getEmail());
        usuario.setFuncao(usuarioDetails.getFuncao());

        return usuarioRepository.save(usuario);
    }
}