package app.platform.models;

import app.platform.models.Municipality_group.Municipality;
import app.platform.models.Municipality_group.Province;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Shop")
public class Shop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;
    private String name;
    private String description;
    private ArrayList<String> t_contact;
    private String address;
    private Double latitude;
    private Double longitude;
    private Double stars;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "user_shop",
            joinColumns = @JoinColumn(name = "shop_id"), // FK de esta entidad
            inverseJoinColumns = @JoinColumn(name = "user_id") // FK de la otra entidad
    )
    private Set<User> users = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "municipality_id")
    private Municipality municipality;

    public Shop() {}

    public Shop(Long id, String image, String name, String description, ArrayList<String> t_contact, String address, Double latitude, Double longitude, Double stars, Category category, Set<User> users, Municipality municipality) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;
        this.t_contact = t_contact;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.stars = stars;
        this.category = category;
        this.users = users;
        this.municipality = municipality;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getT_contact() {
        return t_contact;
    }

    public void setT_contact(ArrayList<String> t_contact) {
        if (t_contact != null) {
            this.t_contact = t_contact.stream()
                    .map(phone -> phone.startsWith("+34") ? phone : "+34" + phone.replaceAll("\\D", ""))
                    .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
        } else {
            this.t_contact = new ArrayList<>();
        }
    }

    public String getAddress() {

        return municipality != null ? municipality.getName() : null;

        //return municipality.getName() + ", " + municipality.getProvince().getName() + ", " + municipality.getProvince().getCcaa().getName(): null;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getStars() {
        return stars;
    }

    public void setStars(Double stars) {
        this.stars = stars;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    public Municipality getTown() {
        return municipality;
    }

    public void setTown(Municipality municipality) {
        this.municipality = municipality;
    }



    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", t_contact=" + t_contact +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", stars=" + stars +
                ", category=" + category +
                ", users=" + users +
                ", municipality=" + municipality +
                '}';
    }


}
