package br.com.will.MyLibraryApi.Model;

import br.com.will.MyLibraryApi.Model.Enumerations.CopyStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book_copies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String inventoryCode;

    @Enumerated(EnumType.STRING)
    private CopyStatus status = CopyStatus.AVAILABLE;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToMany(mappedBy = "bookCopy")
    private List<Loan> loans = new ArrayList<>();


}
