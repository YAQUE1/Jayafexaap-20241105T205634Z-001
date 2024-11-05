package com.Jayafexaap.Jayafexaap.Entidades;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class CategoriaMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_categoria;

    @Enumerated(EnumType.STRING)
    private Categoria nombre;

    @Column(nullable = true, length = 255)
    private String descripcion;

    @OneToMany(targetEntity = Material.class, fetch = FetchType.LAZY, mappedBy = "categoriaMaterial")
    private List<Material> material;

    public CategoriaMaterial(int id_categoria, Categoria nombre, String descripcion) {
        this.id_categoria = id_categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public Categoria getNombre() {
        return nombre;
    }

    public void setNombre(Categoria nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoriaMaterial() {

    }

    @Override
    public String toString() {
        return "CategoriaMaterial{" +
                "id_categoria=" + id_categoria +
                ", nombre=" + nombre +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}