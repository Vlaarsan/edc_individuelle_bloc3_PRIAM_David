package fr.visiplus.bab.services;

import fr.visiplus.bab.dtos.BookDTO;
import fr.visiplus.bab.dtos.ReservationDTO;
import fr.visiplus.bab.dtos.UserDTO;
import fr.visiplus.bab.entities.Book;
import fr.visiplus.bab.entities.BookStatus;
import fr.visiplus.bab.entities.Reservation;
import fr.visiplus.bab.entities.User;
import fr.visiplus.bab.repositories.BookRepository;
import fr.visiplus.bab.repositories.ReservationRepository;
import fr.visiplus.bab.repositories.UserRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

  private BookRepository bookRepository;
  private ReservationRepository reservationRepository;
  private UserRepository userRepository;

  public ReservationService(
    final BookRepository bookRepository,
    final ReservationRepository reservationRepository,
    final UserRepository userRepository
  ) {
    this.bookRepository = bookRepository;
    this.reservationRepository = reservationRepository;
    this.userRepository = userRepository;
  }

  @Transactional
  public ReservationDTO bookByBookIdAndUserId(
    final Long bookId,
    final Long userId
  ) throws Exception {
    User user = userRepository.getReferenceById(userId);
    Book book = bookRepository.getReferenceById(bookId);

    if (book.getStatus().equals(BookStatus.BORROWED)) {
      throw new Exception("Livre déjà emprunté");
    } else if (book.getStatus().equals(BookStatus.BOOKED)) {
      LocalDate resaDate = book.getReservation().getDateResa();
      LocalDate today = LocalDate.now();

      long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(
        resaDate,
        today
      );

      if (daysBetween < 7) {
        throw new Exception("Le livre est réservé depuis moins de 7 jours");
      } else {
        reservationRepository.deleteById(book.getReservation().getId());
      }
    }

    book.setStatus(BookStatus.BOOKED);

    Reservation resa = new Reservation();
    resa.setDateResa(LocalDate.now());
    resa.setBook(book);
    resa.setUser(user);

    resa = reservationRepository.save(resa);
    bookRepository.save(book);

    return convert(resa);
  }

  private ReservationDTO convert(final Reservation reservation) {
    UserDTO userDTO = new UserDTO(
      reservation.getUser().getId(),
      reservation.getUser().getUsername()
    );
    BookDTO bookDTO = new BookDTO(
      reservation.getBook().getId(),
      reservation.getBook().getName(),
      reservation.getBook().getDescription(),
      reservation.getBook().getStatus()
    );
    ReservationDTO resaDTO = new ReservationDTO(
      reservation.getId(),
      reservation.getDateResa(),
      userDTO,
      bookDTO
    );
    return resaDTO;
  }
}
