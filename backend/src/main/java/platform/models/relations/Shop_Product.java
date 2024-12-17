package platform.models.relations;

import platform.models.Product;
import platform.models.Shop;
import platform.models.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Shop_Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "id_shop")
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    public Shop_Product() {
    }


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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Shop_Product{" +
                "id=" + id +
                ", shop=" + shop +
                ", product=" + product +
                '}';
    }
}