package app.platform.models.Municipality_group;

import javax.persistence.*;

@Entity
public class Municipality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    private int cmun;

    private int dc;

    private String name;


    public Municipality() {}

    public Municipality(Long id, Province province, int cmun, int dc, String name) {
        this.id = id;
        this.province = province;
        this.cmun = cmun;
        this.dc = dc;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public int getCmun() {
        return cmun;
    }

    public void setCmun(int cmun) {
        this.cmun = cmun;
    }

    public int getDc() {
        return dc;
    }

    public void setDc(int dc) {
        this.dc = dc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Municipality{" +
                "id=" + id +
                ", province=" + province +
                ", cmun=" + cmun +
                ", dc=" + dc +
                ", name='" + name + '\'' +
                '}';
    }
}

