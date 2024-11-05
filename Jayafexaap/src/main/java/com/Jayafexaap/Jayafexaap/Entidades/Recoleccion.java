package com.Jayafexaap.Jayafexaap.Entidades;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Recoleccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_recoleccion;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate fecha;

    @Column(columnDefinition = "TIME", nullable = false)
    private LocalTime hora;

    @Column(nullable = false)
    private float peso;

    @Column(nullable = false)
    private int puntos;

    @ManyToOne(targetEntity = Predio.class)
    @JoinColumn(name = "Predio_Id", referencedColumnName = "id_predio", nullable = false)
    private Predio predio;

    @JoinColumn(name = "Material_Id", referencedColumnName = "id_material", nullable = false)
    @ManyToOne(targetEntity = Material.class)
    private Material material;

    public Recoleccion() {
    }

    public Recoleccion(int id_recoleccion, LocalDate fecha, LocalTime hora, float peso, int puntos, Predio predio, Material material) {
        this.id_recoleccion = id_recoleccion;
        this.fecha = fecha;
        this.hora = hora;
        this.peso = peso;
        this.puntos = puntos;
        this.predio = predio;
        this.material = material;
    }

    public int getId_recoleccion() {
        return id_recoleccion;
    }

    public void setId_recoleccion(int id_recoleccion) {
        this.id_recoleccion = id_recoleccion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Recoleccion(float peso){
        this.peso = peso;
        this.puntos = calcularPuntos(peso);

    }

    public int calcularPuntos(float peso){
        return (int) peso;
    }

    public Predio getPredio() {
        return predio;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Recoleccion{" +
                "id_recoleccion=" + id_recoleccion +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", peso=" + peso +
                ", puntos=" + puntos +
                ", predio=" + predio +
                ", material=" + material +
                '}';
    }
}
