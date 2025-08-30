package br.com.will.MyLibraryApi.Service;

import br.com.will.MyLibraryApi.Exception.UserNotFoundException;
import br.com.will.MyLibraryApi.Model.User;
import br.com.will.MyLibraryApi.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        List<User> users = repository.findAll();
        if (users.isEmpty()) return null;

        return users;
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException("Resource ID not found"));
    }

    public User post(User user) {
        if (repository.existsById(user.getId()))
            throw new IllegalArgumentException("This user already exists");

        return repository.save(user);
    }

    public void deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else
            throw new IllegalArgumentException("There is no user with resource id " + id);
    }

}
