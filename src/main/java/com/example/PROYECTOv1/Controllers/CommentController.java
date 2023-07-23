package com.example.PROYECTOv1.Controllers;

import com.example.PROYECTOv1.Entities.Comment;
import com.example.PROYECTOv1.Repositories.CommentRepository;
import com.example.PROYECTOv1.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v2/comentarios")
public class CommentController {
    @Autowired
    private CommentService comentarioService;

    @GetMapping("/anidados")
    public List<Comment> obtenerComentarios() {
        return comentarioService.obtenerComentarios();
    }

    @PostMapping()
    public Comment guardarComentario(@RequestBody Comment comentario) {
        return comentarioService.guardarComentario(comentario);
    }

    @DeleteMapping("/{id}")
    public void eliminarComentario(@PathVariable Long id) {
        comentarioService.eliminarComentario(id);
    }

    @GetMapping("/{id}")
    public Comment obtenerComentarioPorId(@PathVariable Long id) {
        return comentarioService.obtenerComentarioPorId(id);
    }

    @PostMapping("/{parentId}/responder")
    public Comment responderComentario(@PathVariable Long parentId, @RequestBody Comment respuesta) {
        return comentarioService.responderComentario(parentId, respuesta);
    }

    @GetMapping("/comentariosanidados")
    public List<Map<String, Object>> obtenerComentariosAnidados() {
    List<Comment> comentariosPrincipales = comentarioService.obtenerComentarios();
    List<Map<String, Object>> comentariosAnidados = new ArrayList<>();
        for (Comment comentario : comentariosPrincipales) {
            Map<String, Object> comentarioMap = new HashMap<>();
            comentarioMap.put("id", comentario.getId());
            comentarioMap.put("contenido", comentario.getContenido());
            comentarioMap.put("fecha", comentario.getFecha());
            comentarioMap.put("res", comentario.getComentariosAnidados());
            comentarioMap.put("id_padre",comentario.getPadre());
            comentariosAnidados.add(comentarioMap);
        }
        return comentariosAnidados;
    }





}
