package com.Jayafexaap.Jayafexaap.Servicios;

import com.Jayafexaap.Jayafexaap.Entidades.CategoriaMaterial;
import com.Jayafexaap.Jayafexaap.Repositorios.CategoriaMaterialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaMaterialServicio {


    @Autowired
    private CategoriaMaterialRepositorio categoriaMaterialRepositorio;

    // Guardar o actualizar una CategoriaMaterial
    public CategoriaMaterial saveCategoriaMaterial(CategoriaMaterial categoriaMaterial) {
        return categoriaMaterialRepositorio.save(categoriaMaterial);
    }

    // Obtener una CategoriaMaterial por ID
    public Optional<CategoriaMaterial> getCategoriaMaterialById(int id_categoria) {
        return categoriaMaterialRepositorio.findById(id_categoria);
    }

    // Obtener todas las CategoriaMaterial
    public List<CategoriaMaterial> getAllCategoriaMaterial() {
        return categoriaMaterialRepositorio.findAll();
    }

    // Eliminar una CategoriaMaterial por ID
    public void deleteCategoriaMaterial(int id_categoria) {
        categoriaMaterialRepositorio.deleteById(id_categoria);
    }

    // Verificar si una CategoriaMaterial existe por ID
    public boolean existsById(int id_categoria) {
        return categoriaMaterialRepositorio.existsById(id_categoria);
    }

    // Metho to update a Categoria Material by Id
    public CategoriaMaterial updateCategoriaMaterialById(String id_categoria, CategoriaMaterial updatedCategoriaMaterial){
        // Check if the CategoriaMaterial exists
        return categoriaMaterialRepositorio.findById(Integer.valueOf(id_categoria)).map(existingCategoriaMaterial ->{
            //Check fields
            if (updatedCategoriaMaterial.getNombre() != null) {
                existingCategoriaMaterial.setNombre(updatedCategoriaMaterial.getNombre());
            }
            if (updatedCategoriaMaterial.getDescripcion() != null) {
                existingCategoriaMaterial.setDescripcion(updatedCategoriaMaterial.getDescripcion());
            }

            // Save the updated Categoria Material
            return categoriaMaterialRepositorio.save(existingCategoriaMaterial);
        }).orElseThrow(() -> new RuntimeException("Categoria Material with ID " + id_categoria + " not found"));
    }
}