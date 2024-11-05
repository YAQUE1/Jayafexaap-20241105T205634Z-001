package com.Jayafexaap.Jayafexaap.Controladores;

import com.Jayafexaap.Jayafexaap.Entidades.Predio;
import com.Jayafexaap.Jayafexaap.Servicios.PredioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/predios")
public class PredioControlador {

    @Autowired
    private PredioServicio predioServicio;

    // Crear o actualizar un Predio
    @PostMapping
    public ResponseEntity<Predio> createPredio(@RequestBody Predio predio) {
        predioServicio.savePredio(predio);
        return ResponseEntity.status(HttpStatus.CREATED).body(predio);
    }

    // Obtener un Predio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Predio> getPredioById(@PathVariable int id) {
        Optional<Predio> predio = predioServicio.getPredioById(id);
        return predio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener todos los Predios
    @GetMapping
    public ResponseEntity<List<Predio>> getAllPredios() {
        List<Predio> predios = predioServicio.getAllPredios();
        return new ResponseEntity<>(predios, HttpStatus.OK);
    }

    // Obtener todos los Predios con Usuarios
    @GetMapping("/usuarios")
    public ResponseEntity<List<Predio>> getAllPrediosWithUsuarios() {
        List<Predio> predios = predioServicio.getAllPrediosWithUsuarios();
        return new ResponseEntity<>(predios, HttpStatus.OK);
    }

    // Buscar Predio por contrato
    @GetMapping("/contrato/{contrato}")
    public ResponseEntity<Predio> getPredioByContrato(@PathVariable String contrato) {
        Predio predio = predioServicio.getPredioByContrato(contrato);
        return predio != null ? ResponseEntity.ok(predio) : ResponseEntity.notFound().build();
    }

    // Buscar Predios por dirección
    @GetMapping("/direccion")
    public ResponseEntity<List<Predio>> getPrediosByDireccion(@RequestParam String direccion) {
        List<Predio> predios = predioServicio.getPrediosByDireccion(direccion);
        return new ResponseEntity<>(predios, HttpStatus.OK);
    }

    // Eliminar un Predio por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePredio(@PathVariable int id) {
        predioServicio.deletePredio(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Actualizar un Predio por ID
    @PutMapping("/{id}")
    public ResponseEntity<Predio> updatePredio(@PathVariable int id, @RequestBody Predio predio) {
        Predio updatedPredio = predioServicio.updatePredio(id, predio);
        return new ResponseEntity<>(updatedPredio, HttpStatus.OK);
    }
}