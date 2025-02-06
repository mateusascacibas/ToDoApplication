package exception;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import jakarta.persistence.EntityNotFoundException;
import service.TaskService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; // Adicione essa importação

@SpringBootTest(classes = TodoProjectApplication.class)
@AutoConfigureMockMvc
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc; 

    @MockBean
    private TaskService taskService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private TaskEntity invalidTask;

    @BeforeEach
    void setUp() {
        invalidTask = new TaskEntity(1L, "", "Descrição da tarefa", StatusTaskEnum.PENDENTE);
    }

    @Test
    void shouldHandleValidationException() throws Exception {
        mockMvc.perform(post("/api/tarefas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidTask)))
                .andExpect(status().isBadRequest()) // Espera 400 BAD REQUEST
                .andExpect(jsonPath("$.title").value("O título é obrigatório"));
    }

    @Test
    void shouldHandleInvalidFormatException() throws Exception {
        String invalidJson = "{\"title\":\"Tarefa Teste\", \"description\":\"Descrição da tarefa\", \"status\":\"INVALIDO\"}";

        mockMvc.perform(post("/api/tarefas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson))
                .andExpect(status().isBadRequest()) // Espera 400 BAD REQUEST
                .andExpect(jsonPath("$.status").value("O status fornecido é inválido. Valores aceitos: [PENDENTE, EM_ANDAMENTO, CONCLUIDA]"));
    }

    @Test
    void shouldHandleEntityNotFoundException() throws Exception {
        Long invalidId = 99L;
        org.mockito.Mockito.when(taskService.findTaskById(invalidId))
                .thenThrow(new EntityNotFoundException(invalidId.toString()));

        mockMvc.perform(get("/api/tarefas/" + invalidId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Task de id: 99 não encontrada."));
    }
}
