package com.Jayafexaap.Jayafexaap.Entidades;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Predio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_predio;

    @Column(unique = true, nullable = false)
    private String contrato;

    @Column(length = 255)
    private String direccion;

    @Enumerated(EnumType.STRING)
    private TipoUso uso;

    @ManyToOne(targetEntity = Usuario.class)
    private Usuario usuario;

    @OneToMany(mappedBy = "predio",fetch = FetchType.LAZY)
    private List<Recoleccion> recoleccion;

    public Predio(){

    }


    public Predio(int id_predio, String contrato, String direccion, TipoUso uso, Usuario usuario) {
        this.id_predio = id_predio;
        this.contrato = contrato;
        this.direccion = direccion;
        this.uso = uso;
        this.usuario = usuario;
    }

    public int getId_predio() {
        return id_predio;
    }

    public void setId_predio(int id_predio) {
        this.id_predio = id_predio;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public TipoUso getUso() {
        return uso;
    }

    public void setUso(TipoUso uso) {
        this.uso = uso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Predio{" +
                "id_predio=" + id_predio +
                ", contrato='" + contrato + '\'' +
                ", direccion='" + direccion + '\'' +
                ", uso=" + uso +
                ", Usuario=" + usuario +
                '}';
    }

}
