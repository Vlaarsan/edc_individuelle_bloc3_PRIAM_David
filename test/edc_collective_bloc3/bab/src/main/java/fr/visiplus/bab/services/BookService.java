package fr.visiplus.bab.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.visiplus.bab.dtos.BookDTO;
import fr.visiplus.bab.entities.Book;
import fr.visiplus.bab.entities.BookStatus;
import fr.visiplus.bab.entities.User;
import fr.visiplus.bab.repositories.BookRepository;
import fr.visiplus.bab.repositories.UserRepository;

@Service
public class BookService {
		
	private BookRepository bookRepository;
	private UserRepository userRepository;
	
	public BookService(
			final BookRepository bookRepository, 
			final UserRepository userRepository) {
		this.bookRepository = bookRepository;
		this.userRepository = userRepository;
	}
	
	public List<BookDTO> getAllBooks() {
		return convert(bookRepository.findAll());
	}

	public Set<BookDTO> getBooksByUserId(final Long userId) {		
		User user = userRepository.getReferenceById(userId);
		Set<BookDTO> books = new LinkedHashSet<BookDTO>();
		user.getReservations().forEach((resa) -> {
			if(!isNotGet(resa.getBook())) {
				books.add(convert(resa.getBook()));
			}
		});
		return books;
	}

	public List<BookDTO> getBookBookedButNotGet() {	
		return bookRepository.findAll().stream()
				.filter((book) -> isNotGet(book))
				.map((book) -> convert(book))
				.collect(Collectors.toList());		
	}
	
	private boolean isNotGet(final Book book) {
		boolean booked = book.getStatus().equals(BookStatus.BOOKED);
		if (booked) {
			LocalDate bookedDate = book.getReservation().getDateResa();
			LocalDate today = LocalDate.now();

			Period period = Period.between(bookedDate, today);
			if(period.getYears() > 0 || period.getMonths() > 0 || period.getDays() > 21) {			
				return true;
			}			
		}
		return false;
	}
	
	private List<BookDTO> convert(final List<Book> books) {
		return books.stream().map( (book) -> convert(book) ).collect(Collectors.toList());
	}
	
	private BookDTO convert(final Book book) {
		return new BookDTO(book.getId(), book.getName(), book.getDescription(), book.getStatus());
	}

}
