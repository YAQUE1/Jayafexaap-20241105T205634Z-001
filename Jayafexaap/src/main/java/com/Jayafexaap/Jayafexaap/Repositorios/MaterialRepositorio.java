package com.Jayafexaap.Jayafexaap.Repositorios;

import com.Jayafexaap.Jayafexaap.Entidades.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterialRepositorio extends JpaRepository<Material, Integer> {
    Optional<Material> findByNombre(String nombre);
}