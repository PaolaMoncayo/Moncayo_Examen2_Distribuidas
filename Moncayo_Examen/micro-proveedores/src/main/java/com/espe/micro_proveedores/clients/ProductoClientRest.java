package com.espe.micro_proveedores.clients;

import com.espe.micro_proveedores.model.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "micro-productos", url = "http://localhost:8002/api/productos")
public interface ProductoClientRest {

    @GetMapping("/{id}")
    Producto obtenerProducto(@PathVariable Long id);
}
