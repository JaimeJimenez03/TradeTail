package app.platform.models.Municipality_group;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "ccaa")
public class CCAA implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public CCAA() {}


    public CCAA(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "CCAA{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
