package app.platform.models;

import app.platform.models.Municipality_group.Municipality;
import app.platform.models.Municipality_group.Province;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "User")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String name;

    private String first_name;
    private String last_name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String telephone_number;
    @ManyToOne
    @JoinColumn(name = "municipality_id")
    private Municipality municipality;

    @ManyToMany(mappedBy = "users") //
    private Set<Shop> shops = new HashSet<>();

    @Column(nullable = false)
    private String password;

    private String token;
    private String avatar;
    private boolean isActive;
    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userApp", referencedColumnName = "id")
    private UserApp userApp;

    public static final String USER_CURRENT = "USER";
    public static final String USER_TRADER = "TRADER";
    public static final String USER_ADMIN = "ADMIN";

    public User() { }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone_number() {
        return telephone_number;
    }


    public void setTelephone_number(String telephone_number) {
        if (telephone_number == null) {
            this.telephone_number = null;
        } else if (telephone_number.startsWith("+34")) {
            this.telephone_number = telephone_number;
        } else {
            this.telephone_number = "+34" + telephone_number.replaceAll("\\D", "");
        }
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    public UserApp getUserApp() {
        return userApp;
    }

    public void setUserApp(UserApp userApp) {
        this.userApp = userApp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (USER_CURRENT.equals(role) || USER_ADMIN.equals(role) || USER_TRADER.equals(role)) {
            this.role = role;
        } else {
            throw new IllegalArgumentException("Invalid role: " + role + ". Only 'USER', 'TRADER' or 'ADMIN' are allowed.");
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", telephone_number='" + telephone_number + '\'' +
                ", municipality=" + municipality +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", avatar='" + avatar + '\'' +
                ", isActive=" + isActive +
                ", role='" + role + '\'' +
                ", userApp=" + userApp +
                '}';
    }
}