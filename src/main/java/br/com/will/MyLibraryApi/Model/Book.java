package br.com.will.MyLibraryApi.Model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book_tab")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "book_title", length = 100, nullable = false)
    private String title;

    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "IBC")
    private Long internalBookCode;

    @ManyToMany // livros podem ter N autores e autor pode ter N livros
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_author")
    )
    private List<Author> authors = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true) // copy is going to contain book FK
    private List<BookCopy> copies = new ArrayList<>();

}
