package com.bitlab.entity;

/**
 * @author Gustavo Gómez
 * class: User
 * fecha: 2020-07-03
 */
public class User {
    String username;
    String password;
    byte rol_id;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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

    public byte getRol_id() {
        return rol_id;
    }

    public void setRol_id(byte rol_id) {
        this.rol_id = rol_id;
    }
}
