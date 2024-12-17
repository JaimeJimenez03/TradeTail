package platform.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import platform.models.User;
import platform.repository.UserRepository;

import java.util.Optional;


@Service
public class MiUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usuarioRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("No se encontro el usuario "+ username
                +" en la BD"));

        return user.map(MiUserDetails::new).get();

    }

}
