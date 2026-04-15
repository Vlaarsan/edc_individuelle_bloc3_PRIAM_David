package fr.visiplus.bab.controllers;

import fr.visiplus.bab.dtos.BookDTO;
import fr.visiplus.bab.dtos.ReservationDTO;
import fr.visiplus.bab.services.BookService;
import fr.visiplus.bab.services.ReservationService;
import java.util.List;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

  private BookService bookService;
  private ReservationService reservationService;

  public BookController(
    final BookService bookService,
    final ReservationService reservationService
  ) {
    this.bookService = bookService;
    this.reservationService = reservationService;
  }

  @GetMapping
  public List<BookDTO> getAllBooks() {
    return bookService.getAllBooks();
  }

  @GetMapping("/{userId}")
  public Set<BookDTO> getBooksByUserId(
    @PathVariable(name = "userId") Long userId
  ) {
    return bookService.getBooksByUserId(userId);
  }

  @PostMapping("/reserve/{bookId}/{userId}")
  public ResponseEntity<ReservationDTO> book(
    @PathVariable(name = "bookId") Long bookId,
    @PathVariable(name = "userId") Long userId
  ) {
    try {
      return ResponseEntity.ok(
        reservationService.bookByBookIdAndUserId(bookId, userId)
      );
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
  }

  @GetMapping("/book/notget")
  public List<BookDTO> getBooksNotRetrieved() {
    return bookService.getBooksNotRetrieved();
  }

  @GetMapping("/book/unavailable")
  public List<BookDTO> getUnavailableBooks() {
    return bookService.getUnavailableBooks();
  }
}
