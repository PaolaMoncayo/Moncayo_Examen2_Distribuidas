package com.espe.micro_proveedores.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity // Indica que esta clase es una entidad gestionada por JPA
@Table(name = "productos") // Define el nombre de la tabla en la base de datos
public class Producto {

    @Id // Define la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autogeneración del ID
    private Long id;

    @Column(nullable = false, length = 100) // Columna no nula con un límite de caracteres
    private String nombre;

    @Column(length = 255) // Límite de caracteres opcional para descripción
    private String descripcion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fecha_creacion", nullable = false) // Columna no nula
    private LocalDateTime fechaCreacion;

    // Constructor vacío
    public Producto() {}

    // Constructor con parámetros
    public Producto(Long id, String nombre, String descripcion, LocalDateTime fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
