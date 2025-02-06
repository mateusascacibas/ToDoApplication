package controller; // Certifique-se de que o pacote está correto

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.TodoProject.TodoProjectApplication;

import entity.TaskEntity;
import enums.StatusTaskEnum;
import service.TaskService;

@SpringBootTest(classes = TodoProjectApplication.class)
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private TaskEntity task;

    @BeforeEach
    void setUp() {
        task = new TaskEntity(1L, "Test Task", "Task Description", StatusTaskEnum.PENDENTE);
    }

    @Test
    void shouldCreateTask() throws Exception {
        when(taskService.createTask(any(TaskEntity.class))).thenReturn(task);

        mockMvc.perform(post("/api/tarefas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.task.title").value("Test Task"));
    }

    @Test
    void shouldGetAllTasks() throws Exception {
        when(taskService.listTasks()).thenReturn(List.of(task));

        mockMvc.perform(get("/api/tarefas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Task"));
    }

    @Test
    void shouldGetTaskById() throws Exception {
        when(taskService.findTaskById(1L)).thenReturn(task);

        mockMvc.perform(get("/api/tarefas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Task"));
    }

    @Test
    void shouldUpdateTaskStatus() throws Exception {
        when(taskService.updateStatus(eq(1L), anyString())).thenReturn(task);

        mockMvc.perform(put("/api/tarefas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"status\":\"CONCLUIDA\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.task.status").value("PENDENTE"));
    }

    @Test
    void shouldDeleteTask() throws Exception {
        doNothing().when(taskService).deleteTask(1L);

        mockMvc.perform(delete("/api/tarefas/1"))
                .andExpect(status().isOk());
    }
}
