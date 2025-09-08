package app.platform.api.controllers;

import app.platform.models.User;
import app.platform.services.impl.UserServiceImpl;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    private final Path root = Paths.get("uploads/avatar");


    public UserController() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear la carpeta uploads/avatar");
        }
    }


    @GetMapping("/list")
    public ResponseEntity <List<User>> gettAllUsers() {

        List<User> usersList = new ArrayList<>(userService.getAllUsers());

        return new ResponseEntity<>(new ArrayList<>(usersList), HttpStatus.OK);
    }


    @PostMapping("/getUser/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {

        Optional<User> user = userService.findById(id);

        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }




    @PostMapping("/{userId}/avatar")
    public ResponseEntity<?> uploadUserAvatar(@PathVariable String userId, @RequestParam("file") MultipartFile file) {
        try {
            Optional<User> user = userService.findById(Long.parseLong(userId));

            String newFilename = userId + "_" + file.getOriginalFilename();

            if (user.isPresent()) {
                String oldAvatar = user.get().getAvatar();

                
                if (oldAvatar != null && oldAvatar.equals(newFilename)) {
                    Path oldAvatarPath = this.root.resolve(oldAvatar);
                    try {
                        Files.deleteIfExists(oldAvatarPath);
                    } catch (IOException e) {
                        System.err.println("Error al eliminar el avatar anterior: " + e.getMessage());
                    }
                }

                // Guardar nuevo archivo
                Files.copy(file.getInputStream(), this.root.resolve(newFilename), StandardCopyOption.REPLACE_EXISTING);

                Path newFilePath = this.root.resolve(newFilename);

                Thumbnails.of(file.getInputStream())
                        .size(1920, 1080)
                        .crop(net.coobird.thumbnailator.geometry.Positions.CENTER)
                        .toFile(newFilePath.toFile());


                // Actualizar usuario con el nuevo avatar
                user.get().setAvatar(newFilename);
                userService.updateUser(user.get());

                return ResponseEntity.ok("Avatar subido correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error al subir avatar");
        }
    }


    @GetMapping("/avatar/{filename:.+}")
    public ResponseEntity<Resource> getAvatar(@PathVariable String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

