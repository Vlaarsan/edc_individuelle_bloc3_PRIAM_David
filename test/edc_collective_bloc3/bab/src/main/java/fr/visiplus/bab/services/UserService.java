package fr.visiplus.bab.services;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.visiplus.bab.dtos.BookDTO;
import fr.visiplus.bab.dtos.LoginRequest;
import fr.visiplus.bab.dtos.ReservationDTO;
import fr.visiplus.bab.dtos.UserDTO;
import fr.visiplus.bab.entities.Reservation;
import fr.visiplus.bab.entities.User;
import fr.visiplus.bab.repositories.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	public UserService(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Optional<UserDTO> login(final LoginRequest login) {
		Optional<User> user = this.userRepository.findByUsernameAndPassword(login.getUsername(), login.getPassword());
		UserDTO userDTO = null;
		if (user.isPresent()) {
			userDTO = convert(user.get());
		}
		return Optional.ofNullable(userDTO);
	}

	private UserDTO convert(final User user) {		
		UserDTO userDTO = new UserDTO(user.getId(), user.getUsername());
		userDTO.getReservations().addAll(
				user.getReservations().stream().map((resa) -> convert(resa)).collect(Collectors.toList())
				);
		return userDTO;
	}
	
	private ReservationDTO convert(final Reservation reservation) {
		BookDTO bookDTO = new BookDTO(
				reservation.getBook().getId(),
				reservation.getBook().getName(),
				reservation.getBook().getDescription(),
				reservation.getBook().getStatus());
		ReservationDTO resaDTO = new ReservationDTO(reservation.getId(), reservation.getDateResa(), bookDTO);
		return resaDTO;
	}

}
