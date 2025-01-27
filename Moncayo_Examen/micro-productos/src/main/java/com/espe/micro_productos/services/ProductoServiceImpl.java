package com.espe.micro_productos.services;

import com.espe.micro_productos.model.entity.Producto;
import com.espe.micro_productos.repositories.ProductoRepository;
import com.espe.micro_productos.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto update(Long id, Producto producto) {
        Optional<Producto> optionalProducto = productoRepository.findById(id);
        if (optionalProducto.isPresent()) {
            Producto productoToUpdate = optionalProducto.get();
            productoToUpdate.setNombre(producto.getNombre());
            productoToUpdate.setDescripcion(producto.getDescripcion());
            return productoRepository.save(productoToUpdate);
        }
        throw new RuntimeException("Producto no encontrado");
    }

    @Override
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }
}
