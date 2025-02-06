package service;

import entity.TaskEntity;
import enums.StatusTaskEnum;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import repository.TaskRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskEntity createTask(@Valid TaskEntity task) {
        return taskRepository.save(task);
    }

    public List<TaskEntity> listTasks() {
        return taskRepository.findAll();
    }

    public TaskEntity findTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public TaskEntity updateStatus(Long id, String novoStatus) {
    	TaskEntity task = findTaskById(id);
        task.setStatus(StatusTaskEnum.valueOf(novoStatus.toUpperCase()));
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
    	if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException(id.toString());
        }
        taskRepository.deleteById(id);
    }
}
