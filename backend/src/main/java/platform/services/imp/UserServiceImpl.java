package platform.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.models.User;
import platform.repository.UserRepository;
import platform.services.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    @Override
    public User loadUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public Optional<User> findByToken(String token) {
        return userRepository.findUserByToken(token);
    }

    @Override
    public User insertUser(User user) {
        return userRepository.save(user);
    }
}
