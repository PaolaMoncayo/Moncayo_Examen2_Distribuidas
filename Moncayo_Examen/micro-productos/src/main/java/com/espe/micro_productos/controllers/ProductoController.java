package com.espe.micro_productos.controllers;

import com.espe.micro_productos.model.entity.Producto;
import com.espe.micro_productos.services.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> findAll() {
        return productoService.findAll();
    }

    @GetMapping("/{id}")
    public Producto findById(@PathVariable Long id) {
        return productoService.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @PostMapping
    public Producto save(@RequestBody Producto producto) {
        return productoService.save(producto);
    }

    @PutMapping("/{id}")
    public Producto update(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.update(id, producto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productoService.deleteById(id);
    }
}
