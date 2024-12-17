package platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import platform.models.Shop;
import platform.models.User;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    Shop findShopById(Long id);

    @Query(value = "SELECT s.* FROM shop s WHERE  s.category = :category", nativeQuery = true)
    List<Shop> findShopsByCategory(Long category);


    @Query(value = "SELECT s.* FROM shop s " +
            "JOIN user_shop us ON s.id = us.id_shop " +
            "JOIN user u ON us.id_user = u.id " +
            "WHERE u.id = :id",
            nativeQuery = true)
    List<Shop> findByTrader( Long id);
}
