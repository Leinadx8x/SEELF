package br.com.carlos.seelfapi.repository;

import br.com.carlos.seelfapi.model.Conquista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConquistaRepository extends JpaRepository<Conquista, Long> {
    // Busca conquistas de um usuário específico
    List<Conquista> findByUsuarioId(Long usuarioId);

    // Verifica se o usuário já tem uma conquista com esse título (para não ganhar 2 vezes)
    boolean existsByUsuarioIdAndTitulo(Long usuarioId, String titulo);
}