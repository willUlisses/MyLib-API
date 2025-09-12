package br.com.will.MyLibraryApi.Service;

import br.com.will.MyLibraryApi.Handlers.Exceptions.BookAlreadyExistsException;
import br.com.will.MyLibraryApi.Handlers.Exceptions.BookNotFoundException;
import br.com.will.MyLibraryApi.Model.Book;
import br.com.will.MyLibraryApi.Repository.BookRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(@NotNull Book book) {
        Optional<Book> alreadyExistingBook = bookRepository.findByTitle(book.getTitle());
        if (alreadyExistingBook.isPresent()) {
            throw new BookAlreadyExistsException("This book already exists, try to use a PUT or PATCH endpoint");
        }
        return bookRepository.save(book);
    }

    public Book findBookByTitle(String title) {
        Optional<Book> optionalBook = bookRepository.findByTitle(title);
        return optionalBook.orElseThrow(() -> new BookNotFoundException("Couldn't find a book with the title " + title));
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findAll() {
        if (bookRepository.findAll().isEmpty()) {
            throw new NoSuchElementException("Couldn't find any book registered");
        }

        return bookRepository.findAll();
    }

}
