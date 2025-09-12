package br.com.will.MyLibraryApi.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_loan")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // N empréstimos se referem a um livro, um livro está em N empréstimos
    @JoinColumn(name = "book_copy_id") // FK do livro na tabela de junção
    private BookCopy bookCopy;

    @ManyToOne // N empréstimos para um usuario, um usuario tem N empréstimos
    @JoinColumn(name = "id_user") //FK do usuario na tabela de junção
    private User user;

    private final LocalDateTime loanDate = LocalDateTime.now();
    private final LocalDateTime returningDate = loanDate.plusMonths(2);


}
