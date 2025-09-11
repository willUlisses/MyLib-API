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

    @ManyToMany // livros podem ter N autores e autor pode ter N livros
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_author")
    )
    private List<Author> author;
    @Column(name = "available_copies")
    private int availableCopies;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true) // loan vai conter a FK do livro
    private List<Loan> loans = new ArrayList<>();
}
