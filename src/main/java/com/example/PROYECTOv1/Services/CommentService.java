package com.example.PROYECTOv1.Services;

import com.example.PROYECTOv1.Entities.Comment;
import com.example.PROYECTOv1.Repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentService {
    @Autowired
    CommentRepository comentarioRepository;

    public List<Comment> obtenerComentarios() {
        List<Comment> comentariosPrincipales = comentarioRepository.findByPadreIsNull();
        return construirComentariosAnidados(comentariosPrincipales);
    }

    private List<Comment> construirComentariosAnidados(List<Comment> comentarios) {
        for (Comment comentario : comentarios) {
            List<Comment> respuestas = comentario.getRespuestas();
            if (!respuestas.isEmpty()) {
                List<Comment> comentariosAnidados = construirComentariosAnidados(respuestas);
                comentario.setRespuestas(comentariosAnidados);
            }
        }
        return comentarios;
    }
    public Comment obtenerComentarioPorId(Long comentarioId) {
        return comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado con ID: " + comentarioId));
    }

    public Comment responderComentario(Long parentId, Comment respuesta) {
        Comment comentarioPadre = obtenerComentarioPorId(parentId);
        respuesta.setPadre(comentarioPadre);
        return comentarioRepository.save(respuesta);
    }
    public Comment guardarComentario(Comment comentario) {
        return comentarioRepository.save(comentario);
    }

    public void eliminarComentario(Long comentarioId) {
        comentarioRepository.deleteById(comentarioId);
    }
}
