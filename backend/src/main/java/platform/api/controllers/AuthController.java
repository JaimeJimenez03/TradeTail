package platform.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import platform.auth.AutenticationLogin;
import platform.auth.AutenticationResponse;
import platform.models.User;
import platform.security.service.MiUserDetailsService;
import platform.security.utils.JwtUtil;
import platform.services.imp.UserServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;


@RestController
@RequestMapping()
public class AuthController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private MiUserDetailsService miUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestPart("user") String userJson, @RequestPart(value = "media/images/avatar", required = false) MultipartFile avatar) {

        User user;
        try {
            user = new ObjectMapper().readValue(userJson, User.class);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Invalid user data.");
        }

        Optional<User> user1 = userService.findByUsername(user.getUsername());

        if (user1.isPresent() && user1.get().isActive()) {
            return ResponseEntity.badRequest().body("Nombre de usuario ya registrado.");
        }

        if (avatar != null && !avatar.isEmpty()) {
            Path dirImages = Paths.get("src/main/resources/media/images/avatar");
            String routeAbs = dirImages.toFile().getAbsolutePath();
            try {
                byte[] bytesImg = avatar.getBytes();
                Path routeCom = Paths.get(routeAbs + "/" + avatar.getOriginalFilename());
                Files.write(routeCom, bytesImg);
                user.setAvatar(avatar.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(500).body("Error al guardar la imagen.");
            }
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setRole("user");
        user.setCity(user.getCity().toUpperCase());
        user.setMunicipality(user.getMunicipality().toUpperCase());
        userService.insertUser(user);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AutenticationLogin autLogin) throws Exception {


        Optional<User> user = userService.findByUsername(autLogin.getUsername());
        User userToken = userService.loadUsername(autLogin.getUsername());
        final UserDetails userDetails = miUserDetailsService.loadUserByUsername(autLogin.getUsername());
        final String token = jwtUtil.creatToken(userDetails);

        userToken.setToken(token);
        userService.insertUser(userToken);

        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(autLogin.getUsername(), autLogin.getPassword())
            );

        } catch (BadCredentialsException ex) {
            return ResponseEntity.badRequest().body("Error al introducir usuario  o contraseña ");
        }




        return ResponseEntity.ok(new AutenticationResponse(token, user));
    }


    @PostMapping("/getToken")
    public ResponseEntity<?> getToken(@RequestBody String token) {

        if(token == null) {
            return ResponseEntity.ok("Usuario no encontrado");
        }

        Optional<User> user = userService.findByToken(token);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/removeToken")
    public ResponseEntity<?> removeToken(@RequestBody User user) {

        if(!user.isActive()) {
            return ResponseEntity.ok("Usuario no encontrado");
        }

        user.setToken(null);
        userService.insertUser(user);

        return ResponseEntity.ok("Sesión cerrada con éxito");
    }


}
