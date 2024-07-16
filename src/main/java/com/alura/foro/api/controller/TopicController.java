package com.alura.foro.api.controller;

import com.alura.foro.api.dto.topico.*;
import com.alura.foro.api.modelo.Curso;
import com.alura.foro.api.modelo.Topico;
import com.alura.foro.api.modelo.Usuario;
import com.alura.foro.api.repository.CursoRepository;
import com.alura.foro.api.repository.TopicoRepository;
import com.alura.foro.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicController {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public TopicController(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository, CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    @PostMapping
    public ResponseEntity<RespuestaTopico> registrar(@RequestBody @Valid RegistroTopico datosRegistro, UriComponentsBuilder uri) {
        Usuario autor = usuarioRepository.getReferenceById(datosRegistro.autorId());
        Curso curso = cursoRepository.getReferenceById(datosRegistro.cursoId());
        Topico topico = topicoRepository.save(new Topico(datosRegistro, autor, curso));
        RespuestaTopico datosRespuesta = new RespuestaTopico(topico);
        URI url = uri.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<ListadoTopico>> listar(@PageableDefault(size = 10)Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(ListadoTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaTopicoId> retornaDatos(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new RespuestaTopicoId(topico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RespuestaTopico> actualizar(@RequestBody @Valid ActualizarTopico datosActualizar) {
        Usuario autor = usuarioRepository.getReferenceById(datosActualizar.autorId());
        Curso curso = cursoRepository.getReferenceById(datosActualizar.cursoId());
        Topico topico = topicoRepository.getReferenceById(datosActualizar.id());
        topico.actualizarDatos(datosActualizar, autor, curso);
        return ResponseEntity.ok( new RespuestaTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        //topicoRepository.delete(topico);
        topico.cerrarTopico();
        return ResponseEntity.noContent().build();
    }
}
