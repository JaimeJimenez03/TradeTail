package app.platform.api.controllers;


import app.platform.auth.AutenticationLogin;
import app.platform.auth.AutenticationResponse;
import app.platform.models.User;
import app.platform.security.service.MiUserDetailsService;
import app.platform.security.utils.JwtUtil;
import app.platform.security.service.msg.OtpService;
import app.platform.security.service.msg.SmsService;
import app.platform.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
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

    private final OtpService otpService;
    private final SmsService smsService;

    public AuthController(OtpService otpService, SmsService smsService) {
        this.otpService = otpService;
        this.smsService = smsService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) throws Exception {
        try {
            Optional<User> user1 = userService.findByEmail(user.getEmail());

            if (user1.isPresent()) {
                return ResponseEntity.badRequest().body("El email ya está registrado");
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActive(true);
            user.setRole("USER");

            userService.insertUser(user);

            return ResponseEntity.ok(user);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado: " + ex.getMessage());
        }


    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AutenticationLogin autLogin) throws Exception {


        try {
            Optional<User> user = userService.findByEmail(autLogin.getEmail());

            if (!user.isPresent()) {
                return ResponseEntity.badRequest().body("El email no está registrado");
            }

            User userToken = userService.loadUser(user.get().getUsername());
            final UserDetails userDetails = miUserDetailsService.loadUserByUsername(user.get().getEmail());
            final String token = jwtUtil.creatToken(userDetails);

            userToken.setToken(token);
            userService.updateUser(userToken);

            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(autLogin.getEmail(), autLogin.getPassword())
            );

            return ResponseEntity.ok(new AutenticationResponse(token, user));

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Contraseña incorrecta");
        } catch (DataAccessException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se puede conectar a la base de datos");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado: " + ex.getMessage());
        }

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
        userService.updateUser(user);

        return ResponseEntity.ok("Sesión cerrada con éxito");
    }




    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String phoneNumber) {
        String otp = otpService.generateOtp(phoneNumber);
        smsService.sendSms(phoneNumber, "Tu código de verificación es: " + otp);
        return "OTP enviado al número " + phoneNumber;
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String phoneNumber, @RequestParam String otp) {
        if (otpService.validateOtp(phoneNumber, otp)) {
            return "Número verificado ✅";
        }
        return "OTP inválido ❌";
    }


}
