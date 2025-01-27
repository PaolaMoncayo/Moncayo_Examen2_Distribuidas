package com.espe.micro_proveedores.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "proveedor_producto")
public class ProveedorProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "proveedor_id", nullable = false)
    @JsonBackReference // Marca esta propiedad como la referencia inversa
    private Proveedor proveedor;

    @Column(name = "producto_id", nullable = false)
    private Long productoId;

    @Column(name = "producto_nombre", nullable = false, length = 255) // Nuevo campo para el nombre del producto
    private String productoNombre;

    // Constructor vacío
    public ProveedorProducto() {}

    // Constructor con parámetros
    public ProveedorProducto(Proveedor proveedor, Long productoId, String productoNombre) {
        this.proveedor = proveedor;
        this.productoId = productoId;
        this.productoNombre = productoNombre;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    // Método equals y hashCode
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProveedorProducto)) {
            return false;
        }
        ProveedorProducto that = (ProveedorProducto) obj;
        return Objects.equals(this.productoId, that.productoId) &&
                Objects.equals(this.proveedor, that.proveedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proveedor, productoId);
    }
}
