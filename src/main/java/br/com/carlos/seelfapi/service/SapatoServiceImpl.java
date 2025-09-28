package br.com.carlos.seelfapi.service;

import br.com.carlos.seelfapi.model.Sapato;
import br.com.carlos.seelfapi.repository.SapatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SapatoServiceImpl implements SapatoService {

    @Autowired
    private SapatoRepository sapatoRepository;

    @Override
    public Sapato salvar(Sapato sapato) {
        // Regra de negócio: Definir a data de cadastro ao salvar um novo sapato
        sapato.setDataCadastro(LocalDateTime.now());
        sapato.setUltimaAtualizacao(LocalDateTime.now());
        return sapatoRepository.save(sapato);
    }

    @Override
    public List<Sapato> buscarTodos() {
        return sapatoRepository.findAll();
    }

    @Override
    public Optional<Sapato> buscarPorId(Long id) {
        return sapatoRepository.findById(id);
    }

    @Override
    public void deletar(Long id) {
        sapatoRepository.deleteById(id);
    }

    @Override
    public Sapato atualizar(Long id, Sapato sapatoDetails) {

        Sapato sapato = sapatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sapato não encontrado com o id: " + id));

        sapato.setSku(sapatoDetails.getSku());
        sapato.setDescricao(sapatoDetails.getDescricao());
        sapato.setCor(sapatoDetails.getCor());
        sapato.setTamanho(sapatoDetails.getTamanho());
        sapato.setQuantidade(sapatoDetails.getQuantidade());
        sapato.setPrecoCusto(sapatoDetails.getPrecoCusto());
        sapato.setPrecoVenda(sapatoDetails.getPrecoVenda());
        sapato.setUltimaAtualizacao(LocalDateTime.now());

        // Salva o sapato atualizado
        return sapatoRepository.save(sapato);
    }
}