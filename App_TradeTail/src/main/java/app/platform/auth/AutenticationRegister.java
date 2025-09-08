package app.platform.auth;


import java.io.Serializable;

public class AutenticationRegister implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long id;
    private String username;
    private String name;
    private String first_name;
    private String last_name;
    private String email;
    private String telephone_number;
    private String city;
    private String municipality;
    private String password;
    private String avatar;

    private String role;

    public AutenticationRegister(){}

    public AutenticationRegister(Long id, String username, String name, String first_name, String last_name, String email, String telephone_number, String city, String municipality, String password, String avatar, String role) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.telephone_number = telephone_number;
        this.city = city;
        this.municipality = municipality;
        this.password = password;
        this.avatar = avatar;
        this.role = role;
    }

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
        this.telephone_number = telephone_number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}