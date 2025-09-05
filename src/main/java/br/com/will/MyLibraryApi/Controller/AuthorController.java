package br.com.will.MyLibraryApi.Controller;

import br.com.will.MyLibraryApi.Model.Author;
import br.com.will.MyLibraryApi.Model.DTOs.AuthorDTO;
import br.com.will.MyLibraryApi.Service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Author>> findAllAuthors() {
        return ResponseEntity.ok(service.findAllAuthors());
    }

    @PostMapping
    public ResponseEntity<Author> post(@RequestBody AuthorDTO data) {
        Author authorToCreate = new Author(data.name(), data.nationality());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(authorToCreate.getId())
                .toUri();
        return ResponseEntity.created(location).body(authorToCreate);
    }


}
