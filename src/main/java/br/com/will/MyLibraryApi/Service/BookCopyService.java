package br.com.will.MyLibraryApi.Service;

import br.com.will.MyLibraryApi.Model.Book;
import br.com.will.MyLibraryApi.Model.BookCopy;
import br.com.will.MyLibraryApi.Model.Enumerations.CopyStatus;
import br.com.will.MyLibraryApi.Repository.BookRepository;
import br.com.will.MyLibraryApi.Repository.CopyRepository;
import org.springframework.stereotype.Service;

@Service
public class BookCopyService {

    private final BookRepository bookRepository;
    private final CopyRepository copyRepository;


    public BookCopyService(BookRepository bookRepository, CopyRepository copyRepository) {
        this.bookRepository = bookRepository;
        this.copyRepository = copyRepository;
    }

    public Book addCopies(Long bookId, int numberOfCopies) {
        Book bookToUpdate = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        for (int i = 0; i < numberOfCopies; i++) {
            BookCopy additionalCopy = new BookCopy();
            additionalCopy.setInventoryCode(bookToUpdate.getTitle().substring(0,2) + "-" + bookToUpdate.getCopies().size() + 1);
            additionalCopy.setBook(bookToUpdate);
            additionalCopy.setStatus(CopyStatus.AVAILABLE);
            copyRepository.save(additionalCopy);
            bookToUpdate.getCopies().add(additionalCopy);
        }

        return bookRepository.save(bookToUpdate);
    }


    public void deleteCopyById(Long copyId) {
        copyRepository.deleteById(copyId);
    }

}
