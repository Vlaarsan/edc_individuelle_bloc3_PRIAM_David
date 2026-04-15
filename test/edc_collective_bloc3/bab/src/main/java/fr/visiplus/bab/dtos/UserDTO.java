package fr.visiplus.bab.dtos;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
	
	private Long id;
	
	private String username;
	
	private List<ReservationDTO> reservations = new ArrayList<ReservationDTO>();
	
	public UserDTO(Long id, String username) {
		this.id = id;
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public List<ReservationDTO> getReservations() {
		return reservations;
	}
	
	public void addReservation(ReservationDTO reservationDTO) {
		reservations.add(reservationDTO);
	}
	
}
