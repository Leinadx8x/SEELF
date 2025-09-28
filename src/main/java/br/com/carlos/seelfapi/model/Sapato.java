package br.com.carlos.seelfapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sapatos")
public class Sapato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String sku;

    @Column(unique = true)
    private String codigoDeBarras;

    private String descricao;
    private String cor;
    private String tamanho;
    private int quantidade;
    private double precoCusto;
    private double precoVenda;

    private LocalDateTime dataCadastro;
    private LocalDateTime ultimaAtualizacao;
}