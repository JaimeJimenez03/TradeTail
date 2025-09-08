package app.platform.services;

import app.platform.models.Category;
import app.platform.models.Shop;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ShopService {

    List<Shop> getShops();

    Shop findById(long id);

    Shop insertShop(Shop shop);

    List<String> getT_contact();

    List<Category> getCategories();

    Optional<Category> getCategoryById(long id);
}
