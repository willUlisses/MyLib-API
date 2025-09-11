package br.com.will.MyLibraryApi.Service;

import br.com.will.MyLibraryApi.Exception.BookAlreadyExistsException;
import br.com.will.MyLibraryApi.Exception.BookNotFoundException;
import br.com.will.MyLibraryApi.Model.Book;
import br.com.will.MyLibraryApi.Repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book postBook(Book book) {
        Optional<Book> alreadyExistingBook = bookRepository.findByTitle(book.getTitle());
        if (alreadyExistingBook.isPresent()) {
            throw new BookAlreadyExistsException("This book already exists, try to use a PUT or PATCH endpoint");
        }
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book book) {
        return bookRepository.findById(id)
                .map(bookToUpdate -> {
                    bookToUpdate.setTitle(book.getTitle());
                    bookToUpdate.setAuthor(book.getAuthor());
                    bookToUpdate.setAvailableCopies(book.getAvailableCopies() + 1);

                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new IllegalArgumentException("Book with id " + id + " not found"));
    }

    public Book findBookByTitle(String title) {
        Optional<Book> optionalBook = bookRepository.findByTitle(title);
        return optionalBook.orElseThrow(() -> new BookNotFoundException("Couldn't find a book with the title " + title));
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

}
