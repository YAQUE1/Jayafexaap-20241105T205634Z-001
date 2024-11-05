package com.Jayafexaap.Jayafexaap.Repositorios;

import com.Jayafexaap.Jayafexaap.Entidades.Predio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredioRepositorio extends JpaRepository<Predio, Integer> {

    // Obtener predios con usuarios
    @Query("SELECT p FROM Predio p JOIN FETCH p.usuario")
    List<Predio> findAllWithUsuarios();

    // Obtener predios por contrato
    Predio findByContrato(String contrato);

    // Obtener predios por dirección
    List<Predio> findByDireccion(String direccion);

    // Obtener predios por documento del usuario
    @Query("SELECT p FROM Predio p WHERE p.usuario.documento = :documentoUsuario")
    List<Predio> findByUsuarioDocumento(@Param("documentoUsuario") String documentoUsuario);
}