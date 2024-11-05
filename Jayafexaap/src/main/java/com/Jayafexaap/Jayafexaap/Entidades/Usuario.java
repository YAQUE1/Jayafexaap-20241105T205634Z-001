package com.Jayafexaap.Jayafexaap.Entidades;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Usuario {

    @Id
    private String documento;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false, length = 20)
    private String celular;

    @Column(nullable = false, length = 100)
    private String direccion;

    @OneToMany(targetEntity = Predio.class, fetch = FetchType.LAZY,mappedBy = "usuario")
    private List<Predio> predio;

    public Usuario(){

    }

    public Usuario(String documento, String nombre, String apellido, String email, String celular, String direccion) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.celular = celular;
        this.direccion = direccion;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "documento='" + documento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + celular + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}