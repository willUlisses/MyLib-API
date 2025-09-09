package br.com.will.MyLibraryApi.Repository;

import br.com.will.MyLibraryApi.Model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
}
