package com.Jayafexaap.Jayafexaap.Servicios;

import com.Jayafexaap.Jayafexaap.Entidades.Material;
import com.Jayafexaap.Jayafexaap.Entidades.Predio;
import com.Jayafexaap.Jayafexaap.Entidades.Recoleccion;
import com.Jayafexaap.Jayafexaap.Repositorios.MaterialRepositorio;
import com.Jayafexaap.Jayafexaap.Repositorios.PredioRepositorio;
import com.Jayafexaap.Jayafexaap.Repositorios.RecoleccionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecoleccionServicio {

    @Autowired
    private RecoleccionRepositorio recoleccionRepositorio;

    @Autowired
    private PredioRepositorio predioRepositorio;

    @Autowired
    private MaterialRepositorio materialRepositorio;

    public Recoleccion crearRecoleccion(int id_predio, int id_material, float peso, LocalDate fecha, LocalTime hora) {
        Optional<Predio> predioOpt = predioRepositorio.findById(id_predio);
        Optional<Material> materialOpt = materialRepositorio.findById(id_material);

        if (predioOpt.isPresent() && materialOpt.isPresent()) {
            Predio predio = predioOpt.get();
            Material material = materialOpt.get();
            int puntos = (int) peso;

            Recoleccion recoleccion = new Recoleccion();
            recoleccion.setPredio(predio);
            recoleccion.setMaterial(material);
            recoleccion.setPeso(peso);
            recoleccion.setPuntos(puntos);
            recoleccion.setFecha(fecha);
            recoleccion.setHora(hora);

            return recoleccionRepositorio.save(recoleccion);
        } else {
            throw new RuntimeException("Predio o Material no encontrado");
        }
    }

    public List<Recoleccion> obtenerRecoleccionesPorPredio(Predio predio) {
        return recoleccionRepositorio.findByPredio(predio);
    }

    public List<Recoleccion> obtenerRecoleccionesPorMaterial(Material material) {
        return recoleccionRepositorio.findByMaterial(material);
    }

    public List<Recoleccion> obtenerRecoleccionesPorRangoFechas(LocalDate startDate, LocalDate endDate) {
        return recoleccionRepositorio.findByFechaBetween(startDate, endDate);
    }

    public List<Recoleccion> obtenerTodasLasRecolecciones() {
        return recoleccionRepositorio.findAll();
    }

    public Recoleccion obtenerRecoleccionesPorId(int id) {
        return recoleccionRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Recoleccion no encontrado"));
    }

    public Recoleccion updateRecoleccion(int id, Recoleccion updateRecoleccion) {
        return recoleccionRepositorio.findById(id).map(existingRecoleccion -> {
            if (updateRecoleccion.getFecha() != null) {
                existingRecoleccion.setFecha(updateRecoleccion.getFecha());
            }
            if (updateRecoleccion.getHora() != null) {
                existingRecoleccion.setHora(updateRecoleccion.getHora());
            }
            if (updateRecoleccion.getPeso() >= 0) { // Asegúrate de que el peso no sea negativo
                existingRecoleccion.setPeso(updateRecoleccion.getPeso());
                existingRecoleccion.setPuntos(calcularPuntos(updateRecoleccion.getPeso())); // Actualiza puntos
            }
            if (updateRecoleccion.getPredio() != null) {
                existingRecoleccion.setPredio(updateRecoleccion.getPredio());
            }
            if (updateRecoleccion.getMaterial() != null) {
                existingRecoleccion.setMaterial(updateRecoleccion.getMaterial());
            }
            return recoleccionRepositorio.save(existingRecoleccion);
        }).orElseThrow(() -> new RuntimeException("Recolección con ID " + id + " no encontrada"));
    }

    // Metodo para calcular puntos
    public int calcularPuntos(float peso) {
        return (int) peso; // Ajusta esta lógica según tus necesidades
    }

    // Metodo para eliminar una recolección
    public void eliminarRecoleccion(int id) {
        // Verifica si la recolección existe antes de eliminar
        if (!recoleccionRepositorio.existsById(id)) {
            throw new RuntimeException("Recolección con ID " + id + " no encontrada");
        }
        recoleccionRepositorio.deleteById(id);
    }
}
