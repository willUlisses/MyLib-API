package br.com.will.MyLibraryApi.Controller;

import br.com.will.MyLibraryApi.Model.Book;
import br.com.will.MyLibraryApi.Model.DTOs.BookDTO;
import br.com.will.MyLibraryApi.Model.DTOs.BookWithCopiesDTO;
import br.com.will.MyLibraryApi.Service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/title")
    public ResponseEntity<Book> findByTitle(@RequestBody String title) {
        return new ResponseEntity<>(service.findBookByTitle(title), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookDTO data) {
        Book book = new Book(data.title(), data.authors());
        return new ResponseEntity<>(service.createBook(book), HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<Book> createBookWithCopies(@RequestBody BookWithCopiesDTO data) {
        Book newBook = new Book(data.title(), data.authors());
        return new ResponseEntity<>(service.createBookWithCopies(newBook, data.numberOfCopies()), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        service.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
