package com.Jayafexaap.Jayafexaap.Controladores;

import com.Jayafexaap.Jayafexaap.Entidades.Material;
import com.Jayafexaap.Jayafexaap.Servicios.MaterialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/materiales")
public class MaterialControlador {

    @Autowired
    private MaterialServicio materialServicio;



    // Metodo para crear un nuevo material
    @PostMapping()
    public ResponseEntity<Material> crearMaterial(@RequestBody Material material) {
        Material nuevoMaterial = materialServicio.guardarMaterial(material);
        return ResponseEntity.ok(nuevoMaterial);
    }

    // Metodo para obtener todos los materiales
    @GetMapping()
    public ResponseEntity<List<Material>> listarMateriales() {
        List<Material> materiales = materialServicio.obtenerTodosLosMateriales();
        return ResponseEntity.ok(materiales);
    }

    // Metodo para obtener un material por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Material> obtenerMaterial(@PathVariable int id) {
        Material material = materialServicio.obtenerMaterialPorId(id);
        return ResponseEntity.ok(material);
    }

    // Metodo para actualizar un material existente
    @PutMapping("/{id}")
    public ResponseEntity<Material> actualizarMaterial(@PathVariable int id, @RequestBody Material materialDetalles) {
        Material materialActualizado = materialServicio.actualizarMaterial(id, materialDetalles);
        return ResponseEntity.ok(materialActualizado);
    }

    // Metodo para eliminar un material por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMaterial(@PathVariable int id) {
        materialServicio.eliminarMaterial(id);
        return ResponseEntity.noContent().build(); // Retorna una respuesta vacía (204 No Content)
    }
}