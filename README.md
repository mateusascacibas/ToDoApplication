# 📌 API de Gestão de Tarefas (To-Do) - Spring Boot

[![Java](https://img.shields.io/badge/Java-17-orange)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.2-brightgreen)](https://spring.io/projects/spring-boot)
[![JUnit](https://img.shields.io/badge/JUnit-5-blue)](https://junit.org/junit5/)  
[![H2 Database](https://img.shields.io/badge/H2%20Database-inMemory-lightgrey)](https://www.h2database.com/)  
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

---

## 📖 Descrição
Este projeto é uma **API RESTful para a gestão de tarefas (To-Do)**, desenvolvida com **Spring Boot**.  

A API permite **CRUD completo** das tarefas, incluindo:
✅ Criar tarefas  
✅ Listar todas as tarefas  
✅ Buscar uma tarefa específica pelo ID  
✅ Atualizar o status da tarefa  
✅ Excluir uma tarefa  

O banco de dados utilizado é **H2 Database**, facilitando a execução do projeto sem necessidade de configurações adicionais.  

Os testes são feitos com **JUnit e Mockito**, garantindo a **qualidade e estabilidade** da aplicação.

---

## 🎯 Objetivo
Este projeto foi desenvolvido como parte de um **desafio técnico** para uma vaga de desenvolvedor Java.  
O objetivo é demonstrar boas práticas em desenvolvimento **Spring Boot**, incluindo:
- Arquitetura limpa (**Clean Code**)
- **Testes unitários** cobrindo o comportamento dos serviços
- Uso correto de **Spring Data JPA**
- **Tratamento de erros** com `@RestControllerAdvice`

---

## 🛠️ Tecnologias Utilizadas
- **Java 17**  
- **Spring Boot 3.1.2**  
- **Spring Web** (API REST)  
- **Spring Data JPA** (Persistência de dados)  
- **Banco de Dados H2** (Em memória)  
- **JUnit 5** e **Mockito** (Testes unitários)

---

## 📌 Como Executar o Projeto
### 🚀 1. Pré-requisitos
Antes de rodar o projeto, você precisará ter instalado:
- **JDK 17** ou superior  
- **Maven** (versão 3.6+ recomendada)  
- **Postman** (opcional, para testar os endpoints)

### 📥 2. Clonar o Repositório
```sh
git clone https://github.com/mateusascacibas/ToDoApplication.git
```

### ▶ 3. Executar o Projeto
O projeto pode ser iniciado via **Maven** ou diretamente no seu **IDE (IntelliJ, VS Code, Eclipse, etc.)**.

#### 📌 Usando Maven
```sh
mvn spring-boot:run
```
Ou, compilando e executando:
```sh
mvn clean package
java -jar target/todo-api.jar
```

#### 📌 Usando IDE
Basta rodar a classe principal:  
```java
com.todo.TodoProject.TodoProjectApplication
```

---

## 🔗 Acessando a API
Após iniciar a aplicação, você pode acessar os **endpoints da API** no seguinte endereço:  
**📍 URL Base:** `http://localhost:8080/api/tarefas`

### 📌 Endpoints Disponíveis
#### 🔹 1️⃣ Criar uma Nova Tarefa
📌 **POST** `/api/tarefas`  
**Body (JSON):**
```json
{
  "title": "Comprar pão",
  "description": "Comprar pão e leite no mercado",
  "status": "PENDENTE"
}
```

---

#### 🔹 2️⃣ Listar Todas as Tarefas
📌 **GET** `/api/tarefas`

---

#### 🔹 3️⃣ Buscar Tarefa por ID
📌 **GET** `/api/tarefas/{id}`

---

#### 🔹 4️⃣ Atualizar o Status da Tarefa
📌 **PUT** `/api/tarefas/{id}`  
**Body (JSON):**
```json
{
  "status": "CONCLUIDA"
}
```

---

#### 🔹 5️⃣ Excluir uma Tarefa
📌 **DELETE** `/api/tarefas/{id}`

---

## 📊 Testes Automatizados
Os testes garantem o funcionamento correto dos métodos:
✔ `createTask()`  
✔ `listTasks()`  
✔ `findTaskById()`  
✔ `updateStatus()`  
✔ `deleteTask()`  
✔ Tratamento de exceções com `@RestControllerAdvice`

Para rodar os testes:
```sh
mvn test
```

---

## 🛢️ Configuração do Banco de Dados (H2)
Este projeto utiliza **H2 Database** (modo em memória).  

---

## 👨‍💻 Autor
Feito por [**Mateus Ascacibas**](https://github.com/mateusascacibas) 🤓🚀  
Se gostou do projeto, deixe uma ⭐ no repositório!  

