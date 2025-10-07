package br.com.carlos.seelfapi.controller;

import br.com.carlos.seelfapi.model.Produto;
import br.com.carlos.seelfapi.service.ProdutoService; // Verifique se o nome do serviço está atualizado
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products") // URL alinhada com o frontend
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService; // Nome do serviço atualizado

    @GetMapping
    public List<Produto> listarTodosProdutos() {
        return produtoService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto cadastrarNovoProduto(@RequestBody Produto produto) {
        return produtoService.salvar(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoDetails) {
        try {
            Produto produtoAtualizado = produtoService.atualizar(id, produtoDetails);
            return ResponseEntity.ok(produtoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProduto(@PathVariable Long id) {
        produtoService.deletar(id);
    }
}