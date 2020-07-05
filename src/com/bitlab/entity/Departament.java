package com.bitlab.entity;

/**
 * @author Gustavo Gómez
 * class: Departament
 * fecha: 2020-07-03
 */
public class Departament {
    private int dep_id;
    private String dep_nombre;

    public Departament() {
        
    }

    public Departament(int dep_id, String dep_nombre) {
        this.dep_id = dep_id;
        this.dep_nombre = dep_nombre;
    }
    public int getDep_id() {
        return dep_id;
    }

    public void setDep_id(int dep_id) {
        this.dep_id = dep_id;
    }

    public String getDep_nombre() {
        return dep_nombre;
    }

    public void setDep_nombre(String dep_nombre) {
        this.dep_nombre = dep_nombre;
    }
}
