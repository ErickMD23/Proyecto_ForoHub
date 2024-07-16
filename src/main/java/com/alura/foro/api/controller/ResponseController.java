package com.alura.foro.api.controller;

import com.alura.foro.api.dto.respuesta.*;
import com.alura.foro.api.modelo.Estado;
import com.alura.foro.api.modelo.Respuesta;
import com.alura.foro.api.modelo.Topico;
import com.alura.foro.api.modelo.Usuario;
import com.alura.foro.api.repository.RespuestaRepository;
import com.alura.foro.api.repository.TopicoRepository;
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
@RequestMapping("/respuestas")
public class ResponseController {

    private final RespuestaRepository respuestaRepository;
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;

    public ResponseController(RespuestaRepository respuestaRepository, TopicoRepository topicoRepository, UsuarioRepository usuarioRepository) {
        this.respuestaRepository = respuestaRepository;
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<DatosRetorno> registrar(@RequestBody DatosRegistro datosRegistro, UriComponentsBuilder uri) {
        Topico topico = topicoRepository.getReferenceById(datosRegistro.topicoId());
        if (topico.getEstado() == Estado.CERRADO) {
           return ResponseEntity.unprocessableEntity().build();
        }

        Usuario autor = usuarioRepository.getReferenceById(datosRegistro.autorId());
        Respuesta respuesta = respuestaRepository.save(new Respuesta(datosRegistro, topico, autor));
        topico.agregarRespuesta(respuesta);
        DatosRetorno datosRespuesta = new DatosRetorno(respuesta);
        URI url = uri.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListado>> listar(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(respuestaRepository.findAll(paginacion).map(DatosListado::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRetornoId> retornaDatos(@PathVariable Long id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRetornoId(respuesta));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRetorno> actualizar(@RequestBody ActualizarDatos datosActualizar) {
        Respuesta respuesta = respuestaRepository.getReferenceById(datosActualizar.id());
        Topico topico = topicoRepository.getReferenceById(datosActualizar.topicoId());
        Usuario autor = usuarioRepository.getReferenceById(datosActualizar.autorId());
        respuesta.actualizarDatos(datosActualizar, topico, autor);
        return ResponseEntity.ok( new DatosRetorno(respuesta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuestaRepository.delete(respuesta);
        return ResponseEntity.noContent().build();
    }
}
