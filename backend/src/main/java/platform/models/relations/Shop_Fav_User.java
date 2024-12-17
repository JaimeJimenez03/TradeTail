package platform.models.relations;

import platform.models.Product;
import platform.models.Shop;
import platform.models.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity

public class Shop_Fav_User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_shop")
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Shop_Fav_User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Shop_Fav_User{" +
                "id=" + id +
                ", shop=" + shop +
                ", user=" + user +
                '}';
    }
}
