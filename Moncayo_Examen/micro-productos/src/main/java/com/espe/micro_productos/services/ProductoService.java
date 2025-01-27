package com.espe.micro_productos.services;

import com.espe.micro_productos.model.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> findAll();
    Optional<Producto> findById(Long id);
    Producto save(Producto producto);
    Producto update(Long id, Producto producto);
    void deleteById(Long id);
}
