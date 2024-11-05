package com.Jayafexaap.Jayafexaap.Servicios;

import com.Jayafexaap.Jayafexaap.Entidades.Predio;
import com.Jayafexaap.Jayafexaap.Entidades.Usuario;
import com.Jayafexaap.Jayafexaap.Repositorios.PredioRepositorio;
import com.Jayafexaap.Jayafexaap.Repositorios.UsuarioRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PredioServicio {

    @Autowired
    private PredioRepositorio predioRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    @Transactional
    public void savePredio(Predio predio) {
        // Recupera el usuario existente por su ID
        Usuario usuario = usuarioRepositorio.findById(predio.getUsuario().getDocumento())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        predio.setUsuario(usuario);
        predioRepositorio.save(predio);
    }



    // Get a Predio by id
    public Optional<Predio> getPredioById(int id) {
        return predioRepositorio.findById(id);
    }

    // Get all Predios
    public List<Predio> getAllPredios() {
        return predioRepositorio.findAll();
    }

    // Get all Predios with Usuarios (custom query)
    public List<Predio> getAllPrediosWithUsuarios() {
        return predioRepositorio.findAllWithUsuarios();
    }

    // Find Predio by contrato
    public Predio getPredioByContrato(String contrato) {
        return predioRepositorio.findByContrato(contrato);
    }

    // Find Predios by direccion
    public List<Predio> getPrediosByDireccion(String direccion) {
        return predioRepositorio.findByDireccion(direccion);
    }

    // Borrar Predio por id
    public void deletePredio(int id) {
        predioRepositorio.deleteById(id);
    }

    // Actualizar Predio
    public Predio updatePredio(int id, Predio updatedPredio) {
        return predioRepositorio.findById(id).map(existingPredio -> {
            if (updatedPredio.getContrato() != null) {
                existingPredio.setContrato(updatedPredio.getContrato());
            }
            if (updatedPredio.getDireccion() != null) {
                existingPredio.setDireccion(updatedPredio.getDireccion());
            }
            if (updatedPredio.getUso() != null) {
                existingPredio.setUso(updatedPredio.getUso());
            }
            return predioRepositorio.save(existingPredio);
        }).orElseThrow(() -> new RuntimeException("Predio with ID " + id + " not found"));
    }
}