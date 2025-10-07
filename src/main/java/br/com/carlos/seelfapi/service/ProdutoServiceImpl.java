package br.com.carlos.seelfapi.service;

import br.com.carlos.seelfapi.model.Produto;
import br.com.carlos.seelfapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto salvar(Produto produto) {
        // Regra de negócio: Definir a data de cadastro ao salvar um novo sapato
        produto.setDataCadastro(LocalDateTime.now());
        produto.setUltimaAtualizacao(LocalDateTime.now());
        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    @Override
    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public Produto atualizar(Long id, Produto produtoDetails) {

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sapato não encontrado com o id: " + id));

        produto.setSku(produtoDetails.getSku());
        produto.setDescricao(produtoDetails.getDescricao());
        produto.setCor(produtoDetails.getCor());
        produto.setTamanho(produtoDetails.getTamanho());
        produto.setQuantidade(produtoDetails.getQuantidade());
        produto.setPrecoCusto(produtoDetails.getPrecoCusto());
        produto.setPrecoVenda(produtoDetails.getPrecoVenda());
        produto.setUltimaAtualizacao(LocalDateTime.now());

        // Salva o sapato atualizado
        return produtoRepository.save(produto);
    }
}