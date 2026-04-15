package fr.visiplus.bab.dtos;

import java.time.LocalDate;

public class ReservationDTO {

	private Long id;

	private LocalDate dateResa;

	private UserDTO userDTO;

	private BookDTO bookDTO;

	public ReservationDTO(final Long id, final LocalDate dateResa, final UserDTO userDTO, final BookDTO bookDTO) {
		this.id = id;
		this.dateResa = dateResa;
		this.userDTO = userDTO;
		this.bookDTO = bookDTO;
	}

	public ReservationDTO(final Long id, final LocalDate dateResa, final BookDTO bookDTO) {
		this.id = id;
		this.dateResa = dateResa;
		this.bookDTO = bookDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateResa() {
		return dateResa;
	}

	public void setDateResa(LocalDate dateResa) {
		this.dateResa = dateResa;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public BookDTO getBookDTO() {
		return bookDTO;
	}

	public void setBookDTO(BookDTO bookDTO) {
		this.bookDTO = bookDTO;
	}
}
