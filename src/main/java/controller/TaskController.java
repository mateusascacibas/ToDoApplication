package controller;

import jakarta.validation.Valid;
import service.TaskService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import entity.TaskEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tarefas")
public class TaskController {

	private final TaskService taskService;

	public TaskController(TaskService tarefaService) {
		this.taskService = tarefaService;
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> createTask(@Valid @RequestBody TaskEntity tarefa) {
		TaskEntity savedTask = taskService.createTask(tarefa);
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Tarefa cadastrada com sucesso!");
		response.put("task", savedTask);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<TaskEntity>> listarTlistTasksarefas() {
		return ResponseEntity.ok(taskService.listTasks());
	}

	@GetMapping("/{id}")
	public ResponseEntity<TaskEntity> findTaskById(@PathVariable Long id) {
		return ResponseEntity.ok(taskService.findTaskById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> updateStatus(@PathVariable Long id,
			@RequestBody Map<String, String> body) {
		String novoStatus = body.get("status");
		TaskEntity updatedTask = taskService.updateStatus(id, novoStatus);
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Status atualizado com sucesso!");
		response.put("task", updatedTask);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> excluirTarefa(@PathVariable Long id) {
		taskService.deleteTask(id);
		Map<String, String> response = new HashMap<>();
		response.put("message", "Tarefa excluída com sucesso!");
		return ResponseEntity.ok(response);
	}
}
