package app.platform.security.service;

import app.platform.models.User;
import app.platform.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MiUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = usuarioRepository.findUserByEmail(email);  // Busca por email
        user.orElseThrow(() -> new UsernameNotFoundException("No se encontró el usuario con email: " + email));

        return user.map(MiUserDetails::new).get();
    }
}