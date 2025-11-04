// src/main/java/br/com/carlos/seelfapi/service/ProdutoServiceImpl.java
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
        // A lógica de data agora está na própria classe Produto com @PrePersist
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
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o id: " + id));

        
        produto.setSku(produtoDetails.getSku());
        produto.setName(produtoDetails.getName());
        produto.setDescription(produtoDetails.getDescription());
        produto.setCategory(produtoDetails.getCategory());
        produto.setPrice(produtoDetails.getPrice());
        produto.setImageUrl(produtoDetails.getImageUrl());
        produto.setCurrentStock(produtoDetails.getCurrentStock());
        produto.setMinimumStock(produtoDetails.getMinimumStock());
        
       
        return produtoRepository.save(produto);
    }
}