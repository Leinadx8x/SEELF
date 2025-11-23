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

    @Autowired
    private GamificationService gamificationService; // Gamificação injetada aqui

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
        task.setCreatedAt(LocalDateTime.now());
        task.setStatus("pending");
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada: " + id));

        String oldStatus = task.getStatus();

        // Atualiza dados
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setPriority(taskDetails.getPriority());
        task.setStatus(taskDetails.getStatus());

        // Lógica de data de conclusão
        if ("completed".equals(taskDetails.getStatus()) && !"completed".equals(oldStatus)) {
            task.setCompletedAt(LocalDateTime.now());
        } else if ("pending".equals(taskDetails.getStatus()) && !"pending".equals(oldStatus)) {
            task.setCompletedAt(null);
        }

        Task tarefaSalva = taskRepository.save(task);


        if ("completed".equals(tarefaSalva.getStatus())) {
            gamificationService.verificarConquistaPrimeiraTarefa();
        }
        // -----------------------------------------------------------

        return tarefaSalva;
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}