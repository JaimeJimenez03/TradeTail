package app.platform.auth;

import java.io.Serializable;

public class AutenticationLogin implements Serializable {
    private static final long serialVersionUID = 1L;


    private String email;
    private String password;

    public AutenticationLogin() { }

    public AutenticationLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() { return email; }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}