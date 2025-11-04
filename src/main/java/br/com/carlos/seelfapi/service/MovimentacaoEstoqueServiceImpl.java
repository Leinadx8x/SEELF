package br.com.carlos.seelfapi.service;

import br.com.carlos.seelfapi.model.MovimentacaoEstoque;
import br.com.carlos.seelfapi.repository.MovimentacaoEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimentacaoEstoqueServiceImpl implements MovimentacaoEstoqueService {

    @Autowired
    private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    @Override
    public MovimentacaoEstoque registrar(MovimentacaoEstoque movimentacao) {
    
        return movimentacaoEstoqueRepository.save(movimentacao);
    }

    @Override
    public List<MovimentacaoEstoque> buscarTodas() {
        return movimentacaoEstoqueRepository.findAll();

    }
    
}