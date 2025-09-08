package app.platform.repositories;

import app.platform.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop,Long> {

    Shop findById(long id);

    @Query("SELECT t_contact FROM Shop ")
    ArrayList<String> getT_contact();
}
