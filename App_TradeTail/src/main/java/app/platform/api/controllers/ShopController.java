package app.platform.api.controllers;

import app.platform.models.Category;
import app.platform.models.Shop;
import app.platform.services.impl.ShopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shops")
public class ShopController {

    @Autowired
    private ShopServiceImpl shopService;

    @GetMapping("")
    public ResponseEntity<?> getShops() {
        List<Shop> shops = new ArrayList<>(shopService.getShops());

        return new ResponseEntity<>(new ArrayList<>(shops), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addShop(@RequestBody Shop shop) {

        shopService.insertShop(shop);

        List<Shop> shops = new ArrayList<>(shopService.getShops());
        return new ResponseEntity<>(new ArrayList<>(shops), HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getCategories() {
        List<Category> categories = new ArrayList<>(shopService.getCategories());

        return new ResponseEntity<>(new ArrayList<>(categories), HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<?> getCategory(@RequestBody Long id) {
        if (id == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }

        Optional<Category> category = shopService.getCategoryById(id);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
