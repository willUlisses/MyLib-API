package br.com.will.MyLibraryApi.Service;

import br.com.will.MyLibraryApi.Repository.CopyRepository;
import org.springframework.stereotype.Service;

@Service
public class CopyService {

    private final CopyRepository repository;

    public CopyService(CopyRepository repository) {
        this.repository = repository;
    }

    // create copies method
    // update copies method
    // delete copies method


}
