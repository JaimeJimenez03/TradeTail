package app.platform.auth;


import app.platform.models.User;

import java.io.Serializable;
import java.util.Optional;

public class AutenticationResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String token;
    private Optional<User> user;
    public AutenticationResponse(String token, Optional<User> user){
        this.token = token;
        this.user = user;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public Optional<User> getUser() {
        return user;
    }

    public void setUser(Optional<User> user) {
        this.user = user;
    }
}