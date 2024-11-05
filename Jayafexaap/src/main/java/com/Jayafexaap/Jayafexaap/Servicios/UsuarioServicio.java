package com.Jayafexaap.Jayafexaap.Servicios;

import com.Jayafexaap.Jayafexaap.Entidades.Usuario;
import com.Jayafexaap.Jayafexaap.Repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    // Update or create a usuario
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    // Get a Usuario by documento
    public Optional<Usuario> getUsuariosById(String documento) {
        return usuarioRepositorio.findById(documento);
    }

    // Get all Usuarios
    public List<Usuario> getAllUsuarios() {
        return usuarioRepositorio.findAll();
    }

    // Delete a Usuario by documento
    public void deleteUsuario(String documento) {
        usuarioRepositorio.deleteById(documento);
    }

    // Method to update a Usuario by documento
    public Usuario updateUsuarioById(String documento, Usuario updatedUsuario) {
        return usuarioRepositorio.findById(documento).map(existingUsuario -> {
            if (updatedUsuario.getNombre() != null) {
                existingUsuario.setNombre(updatedUsuario.getNombre());
            }
            if (updatedUsuario.getApellido() != null) {
                existingUsuario.setApellido(updatedUsuario.getApellido());
            }
            if (updatedUsuario.getCelular() != null) {
                existingUsuario.setCelular(updatedUsuario.getCelular());
            }
            if (updatedUsuario.getDireccion() != null) {
                existingUsuario.setDireccion(updatedUsuario.getDireccion());
            }
            if (updatedUsuario.getEmail() != null) {
                existingUsuario.setEmail(updatedUsuario.getEmail());
            }
            return usuarioRepositorio.save(existingUsuario);
        }).orElseThrow(() -> new RuntimeException("Usuario with Documento " + documento + " not found"));
    }

    // Buscar usuario por email
    public Optional<Usuario> getUsuarioByEmail(String email) {
        return usuarioRepositorio.findByEmail(email);
    }

    // Buscar usuarios por nombre o apellido
    public List<Usuario> getUsuariosByNombreOrApellido(String nombre, String apellido) {
        return usuarioRepositorio.findByNombreOrApellido(nombre, apellido);
    }

    // Verificar si un usuario con un documento dado existe
    public boolean usuarioExistsByDocumento(String documento) {
        return usuarioRepositorio.existsByDocumento(documento);
    }

    // Buscar usuarios por nombre
    public List<Usuario> getUsuariosByNombre(String nombre) {
        return usuarioRepositorio.findByNombre(nombre);
    }
}
