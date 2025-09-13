package br.com.will.MyLibraryApi.Repository;

import br.com.will.MyLibraryApi.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByIdIn(Collection<Long> id);
}
