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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimentacao tipo;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario usuario;

    @ManyToOne //
    @JoinColumn(name = "sapato_id", nullable = false)
    private Sapato sapato;

    @Column(nullable = false)
    private int quantidade;
}