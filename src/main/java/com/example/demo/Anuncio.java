package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Anuncio {
    private String nombre;
    private String titulo;
    private String comentario;
    private long id;
    public Anuncio(String nombre, String titulo, String comentario) {
        this.nombre = nombre;
        this.titulo = titulo;
        this.comentario = comentario;
    }

}
