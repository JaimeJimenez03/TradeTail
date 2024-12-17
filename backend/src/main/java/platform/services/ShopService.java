package platform.services;

import platform.models.Shop;
import platform.models.User;

import java.util.List;

public interface ShopService {

    List<Shop> findAllShops();

    Shop findShopById(Long id);

    List<Shop> findShopsByCategory(Long category);

    List<Shop> findShopsByTraderId(Long id);


}
