package platform.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.models.Shop;
import platform.models.User;
import platform.repository.ShopRepository;
import platform.services.ShopService;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopRepository shopRepository;


    @Override
    public List<Shop> findAllShops() {
        return shopRepository.findAll();
    }

    @Override
    public Shop findShopById(Long id) {
        return shopRepository.findShopById(id);
    }

    @Override
    public List<Shop> findShopsByCategory(Long category) {
        return shopRepository.findShopsByCategory(category);
    }

    @Override
    public List<Shop> findShopsByTraderId(Long id) {
        return shopRepository.findByTrader(id);
    }
}
