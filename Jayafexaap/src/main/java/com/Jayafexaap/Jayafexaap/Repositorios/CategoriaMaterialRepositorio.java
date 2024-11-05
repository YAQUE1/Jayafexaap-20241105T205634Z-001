package com.Jayafexaap.Jayafexaap.Repositorios;

import com.Jayafexaap.Jayafexaap.Entidades.CategoriaMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaMaterialRepositorio extends JpaRepository<CategoriaMaterial,Integer> {

}