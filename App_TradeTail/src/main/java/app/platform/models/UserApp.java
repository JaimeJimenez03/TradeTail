package app.platform.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UserApp")
public class UserApp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @OneToOne(mappedBy = "userApp")
    private User user;


    private String theme;

    public UserApp() {}

    public UserApp(Long id, User user, String theme) {
        this.id = id;
        this.user = user;
        this.theme = theme;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return "UserApp{" +
                "id=" + id +
                ", user=" + user +
                ", theme='" + theme + '\'' +
                '}';
    }
}
