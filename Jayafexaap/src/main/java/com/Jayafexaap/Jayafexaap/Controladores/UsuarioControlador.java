package com.Jayafexaap.Jayafexaap.Controladores;

import com.Jayafexaap.Jayafexaap.Entidades.Usuario;
import com.Jayafexaap.Jayafexaap.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    // Crear o actualizar un Usuario
    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario){
        Usuario savedUsuario = usuarioServicio.saveUsuario(usuario);
        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
    }

    // Obtener un Usuario por documento
    @GetMapping("/{documento}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable String documento){
        Optional<Usuario> usuario = usuarioServicio.getUsuariosById(documento);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener todos los Usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        List<Usuario> usuarios = usuarioServicio.getAllUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // Eliminar un Usuario por documento
    @DeleteMapping("/{documento}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String documento) {
        usuarioServicio.deleteUsuario(documento);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Actualizar un Usuario por documento
    @PutMapping("/{documento}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable String documento, @RequestBody Usuario usuario) {
        Usuario updatedUsuario = usuarioServicio.updateUsuarioById(documento, usuario);
        return new ResponseEntity<>(updatedUsuario, HttpStatus.OK);
    }

    // Buscar usuario por email
    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> getUsuarioByEmail(@PathVariable String email) {
        Optional<Usuario> usuario = usuarioServicio.getUsuarioByEmail(email);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Buscar usuarios por nombre o apellido
    @GetMapping("/search")
    public ResponseEntity<List<Usuario>> getUsuariosByNombreOrApellido(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellido) {
        List<Usuario> usuarios = usuarioServicio.getUsuariosByNombreOrApellido(nombre, apellido);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // Verificar si un usuario existe por documento
    @GetMapping("/exists/{documento}")
    public ResponseEntity<Boolean> existsByDocumento(@PathVariable String documento) {
        boolean exists = usuarioServicio.usuarioExistsByDocumento(documento);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    // Buscar usuarios por nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Usuario>> getUsuariosByNombre(@PathVariable String nombre) {
        List<Usuario> usuarios = usuarioServicio.getUsuariosByNombre(nombre);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
}