package br.com.carlos.seelfapi.service;

import br.com.carlos.seelfapi.model.Funcionario;
import br.com.carlos.seelfapi.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public Funcionario salvar(Funcionario funcionario) {
        // Futuramente, aqui entrará a lógica para criptografar a senha, por exemplo.
        return funcionarioRepository.save(funcionario);
    }

    @Override
    public List<Funcionario> buscarTodos() {
        return funcionarioRepository.findAll();
    }

    @Override
    public Optional<Funcionario> buscarPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

    @Override
    public void deletar(Long id) {
        funcionarioRepository.deleteById(id);
    }

    @Override
    public Funcionario atualizar(Long id, Funcionario funcionarioDetails) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com o id: " + id));

        funcionario.setNomeCompleto(funcionarioDetails.getNomeCompleto());
        funcionario.setEmail(funcionarioDetails.getEmail());
        funcionario.setFuncao(funcionarioDetails.getFuncao());

        return funcionarioRepository.save(funcionario);
    }
}