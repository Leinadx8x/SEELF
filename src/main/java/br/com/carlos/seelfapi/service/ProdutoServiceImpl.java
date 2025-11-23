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

    @Autowired
    private GamificationService gamificationService;

    @Override
    public Produto salvar(Produto produto) {
        Produto salvo = produtoRepository.save(produto);

        // 2. CHAMADA DA GAMIFICAÇÃO
        gamificationService.verificarConquistaPrimeiroProduto();

        return salvo;
    }

    @Override
    public List<Produto> buscarTodos() { return produtoRepository.findAll(); }

    @Override
    public Optional<Produto> buscarPorId(Long id) { return produtoRepository.findById(id); }

    @Override
    public void deletar(Long id) { produtoRepository.deleteById(id); }

    @Override
    public Produto atualizar(Long id, Produto produtoDetails) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));

        produto.setSku(produtoDetails.getSku());
        produto.setName(produtoDetails.getName());
        produto.setDescription(produtoDetails.getDescription());
        produto.setCategory(produtoDetails.getCategory());
        produto.setPrice(produtoDetails.getPrice());
        produto.setImageUrl(produtoDetails.getImageUrl());
        produto.setCurrentStock(produtoDetails.getCurrentStock());
        produto.setMinimumStock(produtoDetails.getMinimumStock());
        produto.setCodigoDeBarras(produtoDetails.getCodigoDeBarras());

        return produtoRepository.save(produto);
    }
}