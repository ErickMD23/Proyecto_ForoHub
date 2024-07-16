package com.alura.foro.api.controller;

import com.alura.foro.api.dto.usuario.*;
import com.alura.foro.api.modelo.Usuario;
import com.alura.foro.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final UsuarioRepository usuarioRepository;

    public UserController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<RespuestaUsuario> registrar(@RequestBody RegistroUsuario datosRegistro, UriComponentsBuilder uri) {
        Usuario usuario = usuarioRepository.save(new Usuario(datosRegistro));
        RespuestaUsuario datosRespuesta = new RespuestaUsuario(usuario);
        URI url = uri.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<ListadoUsuario>> listar(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(usuarioRepository.findAll(paginacion).map(ListadoUsuario::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaUsuarioDeId> retornaDatos(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok(new RespuestaUsuarioDeId(usuario));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RespuestaUsuario> actualizar(@RequestBody ActualizarUsuario datosActualizar) {
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizar.id());
        usuario.actualizarDatos(datosActualizar);
        return ResponseEntity.ok( new RespuestaUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuario);
        return ResponseEntity.noContent().build();
    }
}
