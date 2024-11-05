package com.Jayafexaap.Jayafexaap.Repositorios;


import com.Jayafexaap.Jayafexaap.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {

    // Buscar usuario por email
    Optional<Usuario> findByEmail(String email);

    // Buscar usuarios por nombre o apellido
    List<Usuario> findByNombreOrApellido(String nombre, String apellido);

    // Verificar si un usuario con un documento dado existe
    boolean existsByDocumento(String documento);

    List<Usuario> findByNombre(String nombre);
}
