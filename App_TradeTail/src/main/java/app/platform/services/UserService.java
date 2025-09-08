package app.platform.services;

import app.platform.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<User> getAllUsers();

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    User loadUser(String username);

    Optional<User> findByToken(String token);

    User insertUser(User user);

    User updateUser(User user);


}

