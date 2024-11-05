package com.Jayafexaap.Jayafexaap.Repositorios;


import com.Jayafexaap.Jayafexaap.Entidades.Material;
import com.Jayafexaap.Jayafexaap.Entidades.Predio;
import com.Jayafexaap.Jayafexaap.Entidades.Recoleccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecoleccionRepositorio extends JpaRepository<Recoleccion, Integer> {

    List<Recoleccion> findByPredio(Predio predio);

    List<Recoleccion> findByMaterial(Material material);

    List<Recoleccion> findByFechaBetween(LocalDate startDate, LocalDate endDate);
}
