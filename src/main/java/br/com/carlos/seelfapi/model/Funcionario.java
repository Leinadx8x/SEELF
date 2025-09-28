package br.com.carlos.seelfapi.model;

import br.com.carlos.seelfapi.model.enums.FuncaoFuncionario;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCompleto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuncaoFuncionario funcao;

    @Column(unique = true, nullable = false)
    private String email;
}