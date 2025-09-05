package br.com.will.MyLibraryApi.Service;

import br.com.will.MyLibraryApi.Model.Author;
import br.com.will.MyLibraryApi.Repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public List<Author> findAllAuthors() {
        if (!repository.findAll().isEmpty()) {
            return repository.findAll();
        } else
            throw new NoSuchElementException("There are no authors registered in the system");
    }

    public Author post(Author author) {
        if (!repository.existsById(author.getId())){
            return repository.save(author);
        } else
            throw new IllegalArgumentException("Author resource id " + author.getId() + " already exists");
    }

}
