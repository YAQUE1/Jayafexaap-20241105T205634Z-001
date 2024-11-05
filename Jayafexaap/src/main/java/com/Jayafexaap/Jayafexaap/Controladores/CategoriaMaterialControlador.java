package com.Jayafexaap.Jayafexaap.Controladores;

import com.Jayafexaap.Jayafexaap.Entidades.CategoriaMaterial;
import com.Jayafexaap.Jayafexaap.Servicios.CategoriaMaterialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/categoriaMaterial")
public class CategoriaMaterialControlador {

    @Autowired
    private CategoriaMaterialServicio categoriaMaterialServicio;

    // Crear o actualizar una CategoriaMaterial
    @PostMapping
    public ResponseEntity<CategoriaMaterial> createCategoriaMaterial(@RequestBody CategoriaMaterial categoriaMaterial) {
        CategoriaMaterial savedCategoriaMaterial = categoriaMaterialServicio.saveCategoriaMaterial(categoriaMaterial);
        return new ResponseEntity<>(savedCategoriaMaterial, HttpStatus.CREATED);
    }

    // Obtener una CategoriaMaterial por ID
    @GetMapping("/{id_categoria}")
    public ResponseEntity<CategoriaMaterial> getCategoriaMaterialById(@PathVariable int id_categoria) {
        Optional<CategoriaMaterial> categoriaMaterial = categoriaMaterialServicio.getCategoriaMaterialById(id_categoria);
        return categoriaMaterial.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    // Obtener todas las CategoriaMaterial
    @GetMapping
    public ResponseEntity<List<CategoriaMaterial>> getAllCategoriaMaterial() {
        List<CategoriaMaterial> categoriasMaterial = categoriaMaterialServicio.getAllCategoriaMaterial();
        return new ResponseEntity<>(categoriasMaterial, HttpStatus.OK);
    }


    // Eliminar una CategoriaMaterial por ID
    @DeleteMapping("/{id_categoria}")
    public ResponseEntity<Void> deleteCategoriaMaterial(@PathVariable int id_categoria) {
        if (!categoriaMaterialServicio.existsById(id_categoria)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoriaMaterialServicio.deleteCategoriaMaterial(id_categoria);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id_categoria}")
    public ResponseEntity<CategoriaMaterial> updateCategoriaMaterial(@PathVariable String id_categoria, @RequestBody CategoriaMaterial categoriaMaterial) {
        CategoriaMaterial partialUpdateCategoriaMaterial = new CategoriaMaterial();
        if (categoriaMaterial.getNombre() != null) {
            partialUpdateCategoriaMaterial.setNombre(categoriaMaterial.getNombre());
        }
        if (categoriaMaterial.getDescripcion() != null) {
            partialUpdateCategoriaMaterial.setDescripcion(categoriaMaterial.getDescripcion());
        }

        CategoriaMaterial updatedCategoriaMaterial = categoriaMaterialServicio.updateCategoriaMaterialById(id_categoria, partialUpdateCategoriaMaterial);
        return new ResponseEntity<>(updatedCategoriaMaterial, HttpStatus.OK);
    }
}
