package br.com.carlos.seelfapi.repository;

import br.com.carlos.seelfapi.model.Sapato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SapatoRepository extends JpaRepository<Sapato, Long> {


    Optional<Sapato> findBySku(String sku);


    Optional<Sapato> findByCodigoDeBarras(String codigoDeBarras);

}