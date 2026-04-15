package fr.visiplus.bab.services;

import fr.visiplus.bab.dtos.BookDTO;
import fr.visiplus.bab.entities.Book;
import fr.visiplus.bab.entities.BookStatus;
import fr.visiplus.bab.entities.User;
import fr.visiplus.bab.repositories.BookRepository;
import fr.visiplus.bab.repositories.UserRepository;
import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  private BookRepository bookRepository;
  private UserRepository userRepository;

  public BookService(
    final BookRepository bookRepository,
    final UserRepository userRepository
  ) {
    this.bookRepository = bookRepository;
    this.userRepository = userRepository;
  }

  public List<BookDTO> getAllBooks() {
    return convert(bookRepository.findAll());
  }

  public Set<BookDTO> getBooksByUserId(final Long userId) {
    User user = userRepository.getReferenceById(userId);
    Set<BookDTO> books = new LinkedHashSet<BookDTO>();
    user
      .getReservations()
      .forEach(resa -> {
        if (!isNotGet(resa.getBook())) {
          books.add(convert(resa.getBook()));
        }
      });
    return books;
  }

  public List<BookDTO> getBookBookedButNotGet() {
    return bookRepository
      .findAll()
      .stream()
      .filter(book -> isNotGet(book))
      .map(book -> convert(book))
      .collect(Collectors.toList());
  }

  private boolean isNotGet(final Book book) {
    if (!book.getStatus().equals(BookStatus.BOOKED)) {
      return false;
    }

    LocalDate bookedDate = book.getReservation().getDateResa();
    LocalDate today = LocalDate.now();

    long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(
      bookedDate,
      today
    );

    return daysBetween > 21;
  }

  private List<BookDTO> convert(final List<Book> books) {
    return books
      .stream()
      .map(book -> convert(book))
      .collect(Collectors.toList());
  }

  private BookDTO convert(final Book book) {
    return new BookDTO(
      book.getId(),
      book.getName(),
      book.getDescription(),
      book.getStatus()
    );
  }

  public List<BookDTO> getUnavailableBooks() {
    return bookRepository
      .findAll()
      .stream()
      .filter(book -> !book.getStatus().equals(BookStatus.AVAILABLE))
      .map(this::convert)
      .toList();
  }
}
