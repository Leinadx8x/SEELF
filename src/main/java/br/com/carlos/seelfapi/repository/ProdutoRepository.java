package br.com.carlos.seelfapi.repository;

import br.com.carlos.seelfapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {


    Optional<Produto> findBySku(String sku);


    Optional<Produto> findByCodigoDeBarras(String codigoDeBarras);

}