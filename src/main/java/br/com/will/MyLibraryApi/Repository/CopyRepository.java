package br.com.will.MyLibraryApi.Repository;

import br.com.will.MyLibraryApi.Model.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopyRepository extends JpaRepository<BookCopy, Long> {



}
