package com.Jayafexaap.Jayafexaap.Entidades;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_material;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @ManyToOne(targetEntity = CategoriaMaterial.class)
    @JoinColumn(name = "categoria_material_id")
    private CategoriaMaterial categoriaMaterial;

    @OneToMany(mappedBy = "material",fetch = FetchType.LAZY)
    private List<Recoleccion> recoleccion;

    public Material(){

    }

    public Material(int id_material, String nombre, String descripcion, CategoriaMaterial categoriaMaterial) {
        this.id_material = id_material;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoriaMaterial = categoriaMaterial;
    }

    public int getId_material() {
        return id_material;
    }

    public void setId_material(int id_material) {
        this.id_material = id_material;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoriaMaterial getCategoriaMaterial() {
        return categoriaMaterial;
    }

    public void setCategoriaMaterial(CategoriaMaterial categoriaMaterial) {
        this.categoriaMaterial = categoriaMaterial;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id_material=" + id_material +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", material=" + categoriaMaterial +
                '}';
    }
}
