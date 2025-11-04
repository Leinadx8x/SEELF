// src/main/java/br/com/carlos/seelfapi/model/Produto.java
package br.com.carlos.seelfapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String sku;

    @Column(nullable = false)
    private String name;

    private String description;
    
    private BigDecimal price;
    
    private String imageUrl;
    
    private String category;

    @Column(name = "current_stock")
    private int currentStock;
    
    @Column(name = "minimum_stock")
    private int minimumStock;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // --- Adicionando o campo que estava causando o erro ---
    @Column(name = "codigo_de_barras", unique = true)
    private String codigoDeBarras;
    
    // Este método é chamado antes de o produto ser salvo pela primeira vez
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    // Este método é chamado antes de o produto ser atualizado
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}