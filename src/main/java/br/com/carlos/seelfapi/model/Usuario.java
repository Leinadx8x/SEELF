package br.com.carlos.seelfapi.model;

import br.com.carlos.seelfapi.model.enums.PerfilUsuario;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCompleto; // Renomeado de 'name' para bater com o Service

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PerfilUsuario role; // Mantive 'role' no banco, mas vamos adaptar o getter/setter

    // --- GETTERS E SETTERS MANUAIS (Essenciais para corrigir o erro) ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // O Service chama de 'getFuncao/setFuncao', então criamos esses métodos
    // apontando para o campo 'role' para manter compatibilidade
    public PerfilUsuario getFuncao() {
        return role;
    }

    public void setFuncao(PerfilUsuario role) {
        this.role = role;
    }
}