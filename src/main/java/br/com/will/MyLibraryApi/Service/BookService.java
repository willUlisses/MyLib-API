package br.com.will.MyLibraryApi.Service;

import br.com.will.MyLibraryApi.Handlers.Exceptions.BookAlreadyExistsException;
import br.com.will.MyLibraryApi.Handlers.Exceptions.BookNotFoundException;
import br.com.will.MyLibraryApi.Model.Book;
import br.com.will.MyLibraryApi.Model.BookCopy;
import br.com.will.MyLibraryApi.Model.Enumerations.CopyStatus;
import br.com.will.MyLibraryApi.Repository.BookRepository;
import br.com.will.MyLibraryApi.Repository.CopyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CopyRepository copyRepository;

    public BookService(BookRepository bookRepository, CopyRepository copyRepository) {
        this.bookRepository = bookRepository;
        this.copyRepository = copyRepository;
    }

    public Book createBook(Book book) {
        Optional<Book> alreadyExistingBook = bookRepository.findByTitle(book.getTitle());
        if (alreadyExistingBook.isPresent()) {
            throw new BookAlreadyExistsException("This book already exists, try to use a PUT or PATCH endpoint");
        }
        return bookRepository.save(book);
    }

    public Book createBookWithCopies(Book book, int numberOfCopies) {
        Book savedBook = bookRepository.save(book);

        List<BookCopy> copies = new ArrayList<>(); // we create a list of copies that will be added with the book
        for (int i = 0; i < numberOfCopies; i ++) {
            BookCopy newCopy = new BookCopy();
            newCopy.setBook(savedBook);
            newCopy.setStatus(CopyStatus.AVAILABLE);
            newCopy.setInventoryCode(savedBook.getTitle().substring(0,2) + "-" + (i + 1));
            copies.add(newCopy);
        }
        copyRepository.saveAll(copies);

        savedBook.setCopies(copies);
        return savedBook;
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
