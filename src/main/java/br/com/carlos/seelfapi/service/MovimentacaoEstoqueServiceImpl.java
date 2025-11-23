package br.com.carlos.seelfapi.service;

// TODOS OS IMPORTS NECESSÁRIOS ESTÃO AQUI
import br.com.carlos.seelfapi.model.MovimentacaoEstoque;
import br.com.carlos.seelfapi.model.Produto;
import br.com.carlos.seelfapi.model.enums.TipoMovimentacao;
import br.com.carlos.seelfapi.repository.MovimentacaoEstoqueRepository;
import br.com.carlos.seelfapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class MovimentacaoEstoqueServiceImpl implements MovimentacaoEstoqueService {

    @Autowired
    private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    @Transactional
    public MovimentacaoEstoque registrar(MovimentacaoEstoque movimentacao) {

        // 1. Buscar o produto usando o ID que veio na movimentação
        // Usamos o método getId() manual que criamos em Produto
        Produto produto = produtoRepository.findById(movimentacao.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // 2. Lógica de atualização do estoque (Soma ou Subtrai)
        if (movimentacao.getType() == TipoMovimentacao.ENTRADA) {

            int novoEstoque = produto.getCurrentStock() + movimentacao.getQuantity();
            produto.setCurrentStock(novoEstoque);

        } else if (movimentacao.getType() == TipoMovimentacao.SAIDA || movimentacao.getType() == TipoMovimentacao.DEFEITO) {

            if (produto.getCurrentStock() < movimentacao.getQuantity()) {
                throw new RuntimeException("Estoque insuficiente para esta operação!");
            }
            int novoEstoque = produto.getCurrentStock() - movimentacao.getQuantity();
            produto.setCurrentStock(novoEstoque);
        }

        // 3. Salvar o produto atualizado (Essa era a peça que faltava para corrigir o bug!)
        produtoRepository.save(produto);

        // 4. Salvar e retornar o registro da movimentação
        return movimentacaoEstoqueRepository.save(movimentacao);
    }

    @Override
    public List<MovimentacaoEstoque> buscarTodas() {
        return movimentacaoEstoqueRepository.findAll();
    }
}