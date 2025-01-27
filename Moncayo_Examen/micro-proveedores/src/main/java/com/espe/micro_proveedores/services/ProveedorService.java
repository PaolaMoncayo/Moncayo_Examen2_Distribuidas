package com.espe.micro_proveedores.services;

import com.espe.micro_proveedores.model.Producto;
import com.espe.micro_proveedores.model.entity.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProveedorService {

    // Listar todos los proveedores
    List<Proveedor> findAll();

    // Buscar proveedor por ID
    Optional<Proveedor> findById(Long id);

    // Guardar un proveedor
    Proveedor save(Proveedor proveedor);

    // Actualizar un proveedor
    Proveedor update(Long id, Proveedor proveedor);

    // Eliminar un proveedor por ID
    void deleteById(Long id);

    // Listar productos asociados a un proveedor
    List<Producto> listarProductosDeProveedor(Long proveedorId);

    // Asociar un producto a un proveedor
    Optional<Producto> asociarProducto(Long proveedorId, Long productoId);

    // Listar proveedores asociados a un producto
    List<Proveedor> listarProveedoresDeProducto(Long productoId);

    // Eliminar la relación entre un proveedor y un producto
    void eliminarRelacionProveedorProducto(Long proveedorId, Long productoId);
}
