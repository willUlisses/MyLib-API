package br.com.will.MyLibraryApi.Service;

import br.com.will.MyLibraryApi.Exception.BookNotFoundException;
import br.com.will.MyLibraryApi.Model.Book;
import br.com.will.MyLibraryApi.Repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book postBook(Book book) {
        if (book.getTitle() == null) {
            throw new BookNotFoundException("Couldn't find any book with this title");
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
        return bookRepository.findAll();
    }

}
