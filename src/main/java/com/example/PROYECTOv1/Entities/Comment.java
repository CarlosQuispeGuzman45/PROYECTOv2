package com.example.PROYECTOv1.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contenido;

    @Column(nullable = false)
    private String fecha;

    @ManyToOne
    @JoinColumn(name = "id_padre")
    @JsonIgnore
    private Comment padre;

    @OneToMany(mappedBy = "padre", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> respuestas = new ArrayList<>();


    public List<Map<String, Object>> getComentariosAnidados() {
        List<Map<String, Object>> comentariosAnidados = new ArrayList<>();
        for (Comment respuesta : respuestas) {
            Map<String, Object> comentarioMap = new HashMap<>();
            comentarioMap.put("id", respuesta.getId());
            comentarioMap.put("contenido", respuesta.getContenido());
            comentarioMap.put("fecha", respuesta.getFecha());
            comentarioMap.put("res", respuesta.getComentariosAnidados());
            comentarioMap.put("id_padre",respuesta.getPadre().getId());
            comentariosAnidados.add(comentarioMap);
        }
        return comentariosAnidados;
    }


    public Comment() {
    }

    public Comment(Long id, String contenido, String fecha, Comment padre, List<Comment> respuestas) {
        this.id = id;
        this.contenido = contenido;
        this.fecha = fecha;
        this.padre = padre;
        this.respuestas = respuestas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Comment getPadre() {
        return padre;
    }

    public void setPadre(Comment padre) {
        this.padre = padre;
    }

    public List<Comment> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Comment> respuestas) {
        this.respuestas = respuestas;
    }
}
