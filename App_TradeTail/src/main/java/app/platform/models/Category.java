package app.platform.models;

import app.platform.confg.StringListConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name = "Category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String avatar;

    @Convert(converter = StringListConverter.class)
    private ArrayList<String> subCategories;

    public Category() {
    }

    public Category(Long id, String name, String avatar, ArrayList<String> subCategories) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.subCategories = subCategories;
    }

    public Category(Long id, String name, ArrayList<String> subCategories) {
        this.id = id;
        this.name = name;
        this.subCategories = subCategories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getSubCategories() {
        return subCategories;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setSubCategories(ArrayList<String> subCategories) {
        this.subCategories = subCategories;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", subCategories=" + subCategories +
                '}';
    }
}
