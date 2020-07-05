package com.bitlab.entity;

/**
 * @author Gustavo Gómez
 * class: User
 * fecha: 2020-07-03
 */
public class User {
    private int us_id;
    private String us_usuario;
    private String us_contra;
    private String us_correo;
    Rol rol;

    public User() {
        
    }

    public User(int us_id, String us_usuario, String us_contra, String us_correo, Rol rol) {
        this.us_id = us_id;
        this.us_usuario = us_usuario;
        this.us_contra = us_contra;
        this.us_correo = us_correo;
        this.rol = rol;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
  
    public User(String us_usuario, String us_contra) {
        this.us_usuario = us_usuario;
        this.us_contra = us_contra;
    }

    public User(Rol rol) {
        this.rol = rol;
    }

    public User(int us_id, Rol rol) {
        this.us_id = us_id;
        this.rol = rol;
    }
    
    
    
    public int getUs_id() {
        return us_id;
    }

    public void setUs_id(int us_id) {
        this.us_id = us_id;
    }
    
    public String getUs_usuario() {
        return us_usuario;
    }

    public void setUs_usuario(String us_usuario) {
        this.us_usuario = us_usuario;
    }

    public String getUs_contra() {
        return us_contra;
    }

    public void setUs_contra(String us_contra) {
        this.us_contra = us_contra;
    }

    public String getUs_correo() {
        return us_correo;
    }

    public void setUs_correo(String us_correo) {
        this.us_correo = us_correo;
    }
}
