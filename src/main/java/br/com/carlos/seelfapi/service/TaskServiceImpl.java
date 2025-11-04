package br.com.carlos.seelfapi.service;

import br.com.carlos.seelfapi.model.Task;
import br.com.carlos.seelfapi.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task createTask(Task task) {
        // Define a data de criação e o status inicial
        task.setCreatedAt(LocalDateTime.now());
        task.setStatus("pending"); // O frontend espera "pending"
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com o id: " + id));

        // Guarda o status antigo para comparar
        String oldStatus = task.getStatus();
        
        // Atualiza os campos
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setPriority(taskDetails.getPriority());
        task.setStatus(taskDetails.getStatus());

        // Lógica para data de conclusão (igual ao frontend)
        if ("completed".equals(taskDetails.getStatus()) && !"completed".equals(oldStatus)) {
            task.setCompletedAt(LocalDateTime.now());
        } else if ("pending".equals(taskDetails.getStatus()) && !"pending".equals(oldStatus)) {
            task.setCompletedAt(null);
        }

        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}