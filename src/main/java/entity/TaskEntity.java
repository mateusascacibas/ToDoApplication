package entity;

import enums.StatusTaskEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class TaskEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O título é obrigatório")
	private String title;

	private String description;

	@Enumerated(EnumType.STRING)
	private StatusTaskEnum status;
	
	public TaskEntity() {}
	
	public TaskEntity(long id, String title, String description, StatusTaskEnum status) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StatusTaskEnum getStatus() {
		return status;
	}

	public void setStatus(StatusTaskEnum status) {
		this.status = status;
	}

	
}
