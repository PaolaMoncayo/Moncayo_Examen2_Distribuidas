package com.espe.micro_proveedores.controllers;

import com.espe.micro_proveedores.model.Producto;
import com.espe.micro_proveedores.model.entity.Proveedor;
import com.espe.micro_proveedores.services.ProveedorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping
    public List<Proveedor> findAll() {
        return proveedorService.findAll();
    }

    @GetMapping("/{id}")
    public Proveedor findById(@PathVariable Long id) {
        return proveedorService.findById(id).orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
    }

    @PostMapping
    public Proveedor save(@RequestBody Proveedor proveedor) {
        return proveedorService.save(proveedor);
    }

    @PutMapping("/{id}")
    public Proveedor update(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        return proveedorService.update(id, proveedor);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        proveedorService.deleteById(id);
    }

    @GetMapping("/{proveedorId}/productos")
    public List<Producto> listarProductosDeProveedor(@PathVariable Long proveedorId) {
        return proveedorService.listarProductosDeProveedor(proveedorId);
    }

    @PostMapping("/{proveedorId}/productos/{productoId}")
    public Producto asociarProducto(@PathVariable Long proveedorId, @PathVariable Long productoId) {
        return proveedorService.asociarProducto(proveedorId, productoId).orElseThrow(
                () -> new RuntimeException("No se pudo asociar el producto.")
        );
    }

    @GetMapping("/producto/{productoId}")
    public List<Proveedor> listarProveedoresDeProducto(@PathVariable Long productoId) {
        return proveedorService.listarProveedoresDeProducto(productoId);
    }

    @DeleteMapping("/{proveedorId}/productos/{productoId}")
    public void eliminarRelacionProveedorProducto(@PathVariable Long proveedorId, @PathVariable Long productoId) {
        proveedorService.eliminarRelacionProveedorProducto(proveedorId, productoId);
    }
}

