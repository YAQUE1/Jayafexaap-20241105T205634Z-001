package com.Jayafexaap.Jayafexaap.Servicios;

import com.Jayafexaap.Jayafexaap.Entidades.CategoriaMaterial;
import com.Jayafexaap.Jayafexaap.Entidades.Material;
import com.Jayafexaap.Jayafexaap.Repositorios.CategoriaMaterialRepositorio;
import com.Jayafexaap.Jayafexaap.Repositorios.MaterialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServicio {

    @Autowired
    private MaterialRepositorio materialRepositorio;

    @Autowired
    private CategoriaMaterialRepositorio categoriaMaterialRepositorio;


    public Material guardarMaterial(Material material) {
        // Verifica que la categoría esté establecida
        if (material.getCategoriaMaterial() != null && material.getCategoriaMaterial().getId_categoria() > 0) {
            // Busca la categoría por ID
            CategoriaMaterial categoriaMaterial = categoriaMaterialRepositorio.findById(material.getCategoriaMaterial().getId_categoria())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            material.setCategoriaMaterial(categoriaMaterial); // Asigna la categoría al material
        }
        return materialRepositorio.save(material);
    }

    public Material actualizarMaterial(int id, Material materialDetalles) {
        Material materialExistente = materialRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Material no encontrado"));

        materialExistente.setNombre(materialDetalles.getNombre());
        materialExistente.setDescripcion(materialDetalles.getDescripcion());

        // Verifica que la categoría esté establecida
        if (materialDetalles.getCategoriaMaterial() != null && materialDetalles.getCategoriaMaterial().getId_categoria() > 0) {
            CategoriaMaterial categoriaMaterial = categoriaMaterialRepositorio.findById(materialDetalles.getCategoriaMaterial().getId_categoria())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            materialExistente.setCategoriaMaterial(categoriaMaterial); // Asigna la categoría actualizada
        }

        return materialRepositorio.save(materialExistente);
    }

    public List<Material> obtenerTodosLosMateriales() {
        return materialRepositorio.findAll();
    }

    public Material obtenerMaterialPorId(int id) {
        return materialRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Material no encontrado"));
    }

    public void eliminarMaterial(int id) {
        materialRepositorio.deleteById(id);
    }
}