package service;

import repository.TaskRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import entity.TaskEntity;
import enums.StatusTaskEnum;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

	@InjectMocks
	private TaskService taskService;

	@Mock
	private TaskRepository taskRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void shouldCreateTask() {
		TaskEntity task = new TaskEntity(2L, "Teste", "Descrição", StatusTaskEnum.PENDENTE);
		when(taskRepository.save(any())).thenReturn(task);

		TaskEntity result = taskService.createTask(task);

		assertNotNull(result);
		assertEquals("Teste", result.getTitle());
	}

	@Test
	void shouldReturnAllTasks() {
		when(taskRepository.findAll()).thenReturn(List.of(new TaskEntity()));

		List<TaskEntity> result = taskService.listTasks();

		assertFalse(result.isEmpty());
	}

	@Test
	void shouldFindTaskById() {
		TaskEntity task = new TaskEntity(1L, "Teste", "Descrição", StatusTaskEnum.PENDENTE);
		when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

		TaskEntity result = taskService.findTaskById(1L);

		assertNotNull(result);
		assertEquals(1L, result.getId());
	}

	@Test
	void shouldDeleteTaskSuccessfully() {
		when(taskRepository.existsById(1L)).thenReturn(true);
		doNothing().when(taskRepository).deleteById(1L);

		assertDoesNotThrow(() -> taskService.deleteTask(1L));
		verify(taskRepository, times(1)).deleteById(1L);
	}

	@Test
	void shouldThrowExceptionWhenDeletingNonExistingTask() {
		when(taskRepository.existsById(99L)).thenReturn(false);
		EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
			taskService.deleteTask(99L);
		});

		assertEquals("99", thrown.getMessage());
		verify(taskRepository, never()).deleteById(any());
	}

	@Test
	void shouldUpdateTaskStatus() {
		TaskEntity existingTask = new TaskEntity(1L, "Teste", "Descrição", StatusTaskEnum.PENDENTE);
		when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
		when(taskRepository.save(any())).thenReturn(existingTask);

		TaskEntity result = taskService.updateStatus(1L, "CONCLUIDA");

		assertNotNull(result);
		assertEquals(StatusTaskEnum.CONCLUIDA, result.getStatus());
		verify(taskRepository, times(1)).save(existingTask); // Verifica que foi salvo
	}

	@Test
	void shouldThrowExceptionWhenUpdatingNonExistingTask() {
		when(taskRepository.findById(99L)).thenReturn(Optional.empty());
		EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
			taskService.updateStatus(99L, "CONCLUIDA");
		});

		assertEquals("99", thrown.getMessage());
		verify(taskRepository, never()).save(any());
	}

}
