package br.com.carlos.seelfapi.repository;

import br.com.carlos.seelfapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Novo método para contar tarefas por status
    long countByStatus(String status);
}