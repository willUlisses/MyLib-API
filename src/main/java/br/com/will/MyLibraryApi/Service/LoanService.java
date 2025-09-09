package br.com.will.MyLibraryApi.Service;

import br.com.will.MyLibraryApi.Model.Book;
import br.com.will.MyLibraryApi.Model.Loan;
import br.com.will.MyLibraryApi.Repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private final LoanRepository repository;

    public LoanService(LoanRepository repository) {
        this.repository = repository;
    }

    public Loan postLoan(Loan loan) {
        if (repository.existsById(loan.getId())){
            throw new IllegalArgumentException("A Loan with the id " + loan.getId() + " already exists");
        }

        return repository.save(loan);
    }

    public List<Loan> findAllLoans() {
        if (repository.findAll().isEmpty()) {
            throw new NoSuchElementException("There are no loans registered in the system");
        }

        return repository.findAll();
    }

    public List<Loan> findByBook(Book book) {
        if (!repository.findAll().isEmpty()){
            return repository.findAll().stream()
                    .filter(loan -> loan.getBook().equals(book))
                    .toList();
        } else
            throw new NoSuchElementException("Couldn't find any loan from the book " + book.getTitle());
    }



}
