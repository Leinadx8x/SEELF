package br.com.carlos.seelfapi.model;

import br.com.carlos.seelfapi.model.enums.TipoMovimentacao;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "movimentacoes_estoque")
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto product;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimentacao type;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private String responsibleUser;

    private String notes;

    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp;

    @PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();
    }
}