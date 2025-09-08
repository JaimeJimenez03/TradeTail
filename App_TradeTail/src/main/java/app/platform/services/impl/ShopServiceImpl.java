package app.platform.services.impl;

import app.platform.models.Category;
import app.platform.models.Shop;
import app.platform.repositories.CategoryRepository;
import app.platform.repositories.ShopRepository;
import app.platform.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Shop> getShops() {
        return shopRepository.findAll();
    }

    @Override
    public Shop findById(long id) {
        return shopRepository.findById(id);
    }

    @Override
    public Shop insertShop(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<String> getT_contact() {
        return shopRepository.getT_contact();
    }
}
