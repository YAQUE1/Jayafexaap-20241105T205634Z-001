package com.Jayafexaap.Jayafexaap.Controladores;

import com.Jayafexaap.Jayafexaap.Entidades.Material;
import com.Jayafexaap.Jayafexaap.Entidades.Predio;
import com.Jayafexaap.Jayafexaap.Entidades.Recoleccion;
import com.Jayafexaap.Jayafexaap.Servicios.RecoleccionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/recolecciones")
public class RecoleccionControlador {

    @Autowired
    private RecoleccionServicio recoleccionServicio;

    @PostMapping
    public ResponseEntity<Recoleccion> crearRecoleccion(@RequestBody Recoleccion recoleccion) {
        Recoleccion recoleccions = recoleccionServicio.crearRecoleccion(
                recoleccion.getPredio().getId_predio(),
                recoleccion.getMaterial().getId_material(),
                recoleccion.getPeso(),
                recoleccion.getFecha(),
                recoleccion.getHora()
        );

        return ResponseEntity.ok(recoleccions);
    }


    @GetMapping()
    public ResponseEntity<List<Recoleccion>> obtenerTodosLosMateriales() {
        List<Recoleccion> recoleccion = recoleccionServicio.obtenerTodasLasRecolecciones();
        return ResponseEntity.ok(recoleccion);
    }

    // Metodo para obtener un material por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Recoleccion> obtenerRecoleccion(@PathVariable int id) {
        Recoleccion recoleccion = recoleccionServicio.obtenerRecoleccionesPorId(id);
        return ResponseEntity.ok(recoleccion);
    }

    // Obtener todas las recolecciones por predio
    @GetMapping("/predio/{id-predio}")
    public ResponseEntity<List<Recoleccion>> obtenerRecoleccionesPorPredio(@PathVariable Predio predio) {
        List<Recoleccion> recolecciones = recoleccionServicio.obtenerRecoleccionesPorPredio(predio);
        return ResponseEntity.ok(recolecciones);
    }

    // Obtener todas las recolecciones por material
    @GetMapping("/material/{id-material}")
    public ResponseEntity<List<Recoleccion>> obtenerRecoleccionesPorMaterial(@PathVariable Material material) {
        List<Recoleccion> recolecciones = recoleccionServicio.obtenerRecoleccionesPorMaterial(material);
        return ResponseEntity.ok(recolecciones);
    }

    @GetMapping("/rango-fechas") // ruta: /rango-fechas?startDate=2024-10-01&endDate=2024-10-27
    public ResponseEntity<List<Recoleccion>> obtenerRecoleccionesPorRangoFechas(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        List<Recoleccion> recolecciones = recoleccionServicio.obtenerRecoleccionesPorRangoFechas(start, end);
        return ResponseEntity.ok(recolecciones);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recoleccion> actualizarRecoleccion(@PathVariable int id, @RequestBody Recoleccion updateRecoleccion) {
        Recoleccion recoleccionActualizada = recoleccionServicio.updateRecoleccion(id, updateRecoleccion);
        return ResponseEntity.ok(recoleccionActualizada);
    }

    // Método para eliminar una recolección
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRecoleccion(@PathVariable int id) {
        recoleccionServicio.eliminarRecoleccion(id);
        return ResponseEntity.noContent().build(); // Devuelve un estado 204 No Content
    }
}