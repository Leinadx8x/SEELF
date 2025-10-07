package br.com.carlos.seelfapi.model;

import br.com.carlos.seelfapi.model.enums.TipoMovimentacao;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "movimentacoes_estoque")
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto product;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacao type;

    private int quantity;
    private LocalDate date;
}