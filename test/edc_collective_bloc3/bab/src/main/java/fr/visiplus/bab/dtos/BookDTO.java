package fr.visiplus.bab.dtos;

import fr.visiplus.bab.entities.BookStatus;

public class BookDTO {

	private Long id;

	private String name;

	private String description;

	private BookStatus status;

	public BookDTO(Long id, String name, String description, BookStatus status) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BookStatus getStatus() {
		return status;
	}

	public void setStatus(BookStatus status) {
		this.status = status;
	}	

}