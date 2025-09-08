package app.platform.models.Municipality_group;

import javax.persistence.*;

@Entity
@Table(name = "Province")
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ccaa_id")
    private CCAA ccaa;

    private String name;


    public Province() {}

    public Province(Long id, CCAA ccaa, String name) {
        this.id = id;
        this.ccaa = ccaa;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CCAA getCcaa() {
        return ccaa;
    }

    public void setCcaa(CCAA ccaa) {
        this.ccaa = ccaa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Province{" +
                "id=" + id +
                ", ccaa=" + ccaa +
                ", name='" + name + '\'' +
                '}';
    }
}

