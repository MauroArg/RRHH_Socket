package com.bitlab.entity;
import org.json.simple.JSONArray;

/**
 * @author Gustavo Gómez
 * class: Empleado
 * fecha: 2020-07-02
 */
public class Employe {
    private int emp_id;
    private String emp_codigo;
    private String emp_nombres;
    private String emp_apellidos;
    private String emp_dui;
    private String emp_nit;
    private String emp_correo;
    private String emp_telefono;
    private double emp_sueldo;
    private String emp_direccion;
    private byte emp_estado;
    private Departament departamento;
    private int emp_jef_id;

    public Employe() {
        
    }

    public Employe(int emp_id, String emp_codigo, String emp_nombres, String emp_apellidos, String emp_dui, String emp_nit, String emp_correo, String emp_telefono, double emp_sueldo, String emp_direccion, byte emp_estado, Departament departamento, int emp_jef_id) {
        this.emp_id = emp_id;
        this.emp_codigo = emp_codigo;
        this.emp_nombres = emp_nombres;
        this.emp_apellidos = emp_apellidos;
        this.emp_dui = emp_dui;
        this.emp_nit = emp_nit;
        this.emp_correo = emp_correo;
        this.emp_telefono = emp_telefono;
        this.emp_sueldo = emp_sueldo;
        this.emp_direccion = emp_direccion;
        this.emp_estado = emp_estado;
        this.departamento = departamento;
        this.emp_jef_id = emp_jef_id;
    }
    
    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }


    public String getEmp_codigo() {
        return emp_codigo;
    }

    public void setEmp_codigo(String emp_codigo) {
        this.emp_codigo = emp_codigo;
    }

    public String getEmp_nombres() {
        return emp_nombres;
    }

    public void setEmp_nombres(String emp_nombres) {
        this.emp_nombres = emp_nombres;
    }

    public String getEmp_apellidos() {
        return emp_apellidos;
    }

    public void setEmp_apellidos(String emp_apellidos) {
        this.emp_apellidos = emp_apellidos;
    }

    public String getEmp_dui() {
        return emp_dui;
    }

    public void setEmp_dui(String emp_dui) {
        this.emp_dui = emp_dui;
    }

    public String getEmp_nit() {
        return emp_nit;
    }

    public void setEmp_nit(String emp_nit) {
        this.emp_nit = emp_nit;
    }

    public String getEmp_correo() {
        return emp_correo;
    }

    public void setEmp_correo(String emp_correo) {
        this.emp_correo = emp_correo;
    }

    public String getEmp_telefono() {
        return emp_telefono;
    }

    public void setEmp_telefono(String emp_telefono) {
        this.emp_telefono = emp_telefono;
    }

    public double getEmp_sueldo() {
        return emp_sueldo;
    }

    public void setEmp_sueldo(double emp_sueldo) {
        this.emp_sueldo = emp_sueldo;
    }

    public String getEmp_direccion() {
        return emp_direccion;
    }

    public void setEmp_direccion(String emp_direccion) {
        this.emp_direccion = emp_direccion;
    }

    public byte getEmp_estado() {
        return emp_estado;
    }

    public void setEmp_estado(byte emp_estado) {
        this.emp_estado = emp_estado;
    }

    public Departament getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departament departamento) {
        this.departamento = departamento;
    }

    public int getEmp_jef_id() {
        return emp_jef_id;
    }

    public void setEmp_jef_id(int emp_jef_id) {
        this.emp_jef_id = emp_jef_id;
    }
}
