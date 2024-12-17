package platform.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import platform.auth.AutenticationLogin;
import platform.auth.AutenticationResponse;
import platform.models.Shop;
import platform.models.User;
import platform.services.imp.ShopServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    ShopServiceImpl shopService;


    @GetMapping("/getAll")
    public ResponseEntity<List<Shop>> getAll() {
        try {
            List<Shop> listShops = shopService.findAllShops();
            return ResponseEntity.ok(listShops);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PostMapping("/category")
    public ResponseEntity<List<Shop>> getShopsByCategory(@RequestBody Long category) {
        try {
            List<Shop> listShops = shopService.findShopsByCategory(category);

            return ResponseEntity.ok(listShops);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

