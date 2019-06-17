package br.ufrn.imd.app.model;

import java.io.Serializable;

/**
 * Represents the username in session.
 * <p>
 * Also used for modeling the username entity to the persistence.
 */
public class User implements Serializable {
    private String username;
    private String password;

    public User(String username) {
        this.username = username;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}