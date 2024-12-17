package platform.services;

import platform.models.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    User loadUsername(String username);
    Optional<User> findByToken(String token);

     User insertUser(User user);
}
