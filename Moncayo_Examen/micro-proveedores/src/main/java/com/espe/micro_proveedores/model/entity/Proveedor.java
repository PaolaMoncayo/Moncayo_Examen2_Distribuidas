package com.espe.micro_proveedores.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "proveedores")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProveedorProducto> proveedorProductos = new ArrayList<>();

    @Column(name = "creado_en", updatable = false)
    @CreationTimestamp
    private LocalDateTime creadoEn;

    // Método para agregar una relación
    public void addProveedorProducto(ProveedorProducto proveedorProducto) {
        proveedorProductos.add(proveedorProducto);
        proveedorProducto.setProveedor(this);
    }

    // Método para eliminar una relación
    public void removeProveedorProducto(ProveedorProducto proveedorProducto) {
        proveedorProductos.remove(proveedorProducto);
        proveedorProducto.setProveedor(null);
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

    public List<ProveedorProducto> getProveedorProductos() {
        return proveedorProductos;
    }

    public void setProveedorProductos(List<ProveedorProducto> proveedorProductos) {
        this.proveedorProductos = proveedorProductos;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }
}
