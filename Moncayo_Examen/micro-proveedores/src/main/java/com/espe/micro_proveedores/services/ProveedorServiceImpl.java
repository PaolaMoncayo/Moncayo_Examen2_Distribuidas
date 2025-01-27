package com.espe.micro_proveedores.services;

import com.espe.micro_proveedores.clients.ProductoClientRest;
import com.espe.micro_proveedores.model.Producto;
import com.espe.micro_proveedores.model.entity.Proveedor;
import com.espe.micro_proveedores.model.entity.ProveedorProducto;
import com.espe.micro_proveedores.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProductoClientRest productoClientRest;

    @Override
    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

    @Override
    public Optional<Proveedor> findById(Long id) {
        return proveedorRepository.findById(id);
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public Proveedor update(Long id, Proveedor proveedor) {
        Optional<Proveedor> optionalProveedor = proveedorRepository.findById(id);
        if (optionalProveedor.isPresent()) {
            Proveedor proveedorToUpdate = optionalProveedor.get();
            proveedorToUpdate.setNombre(proveedor.getNombre());
            return proveedorRepository.save(proveedorToUpdate);
        }
        throw new RuntimeException("Proveedor no encontrado");
    }

    @Override
    public void deleteById(Long id) {
        proveedorRepository.deleteById(id);
    }

    @Override
    public List<Producto> listarProductosDeProveedor(Long proveedorId) {
        Optional<Proveedor> optionalProveedor = proveedorRepository.findById(proveedorId);
        if (optionalProveedor.isPresent()) {
            Proveedor proveedor = optionalProveedor.get();

            // Mapear las relaciones ProveedorProducto a objetos Producto
            return proveedor.getProveedorProductos().stream()
                    .map(relacion -> {
                        // Obtener el producto del microservicio utilizando ProductoClientRest
                        Producto producto = productoClientRest.obtenerProducto(relacion.getProductoId());
                        if (producto != null) {
                            producto.setNombre(relacion.getProductoNombre()); // Actualizar el nombre del producto
                        }
                        return producto;
                    })
                    .filter(producto -> producto != null) // Filtrar los nulos en caso de errores en la obtención
                    .toList();
        }
        throw new RuntimeException("Proveedor no encontrado");
    }


    @Override
    public Optional<Producto> asociarProducto(Long proveedorId, Long productoId) {
        Optional<Proveedor> optionalProveedor = proveedorRepository.findById(proveedorId);
        if (optionalProveedor.isPresent()) {
            Proveedor proveedor = optionalProveedor.get();

            Producto producto = productoClientRest.obtenerProducto(productoId);
            if (producto != null) {
                // Crear una nueva relación entre el proveedor y el producto
                ProveedorProducto nuevaRelacion = new ProveedorProducto();
                nuevaRelacion.setProveedor(proveedor);
                nuevaRelacion.setProductoId(productoId);
                nuevaRelacion.setProductoNombre(producto.getNombre());

                // Añadir la nueva relación al proveedor
                proveedor.getProveedorProductos().add(nuevaRelacion);

                // Guardar la relación en la base de datos
                proveedorRepository.save(proveedor);

                // Retornar solo el ID y nombre del producto
                Producto respuesta = new Producto();
                respuesta.setId(producto.getId());
                respuesta.setNombre(producto.getNombre());
                return Optional.of(respuesta);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Proveedor> listarProveedoresDeProducto(Long productoId) {
        return proveedorRepository.findAll().stream()
                .filter(proveedor -> proveedor.getProveedorProductos().stream()
                        .anyMatch(relacion -> relacion.getProductoId().equals(productoId)))
                .map(proveedor -> {
                    // Clonamos el proveedor para evitar referencias circulares en JSON
                    Proveedor simpleProveedor = new Proveedor();
                    simpleProveedor.setId(proveedor.getId());
                    simpleProveedor.setNombre(proveedor.getNombre());
                    simpleProveedor.setCreadoEn(proveedor.getCreadoEn());

                    // Incluimos solo las relaciones del producto solicitado
                    List<ProveedorProducto> productosRelacionados = proveedor.getProveedorProductos().stream()
                            .filter(relacion -> relacion.getProductoId().equals(productoId))
                            .toList();

                    simpleProveedor.setProveedorProductos(productosRelacionados);
                    return simpleProveedor;
                })
                .toList();
    }

    @Override
    public void eliminarRelacionProveedorProducto(Long proveedorId, Long productoId) {
        Optional<Proveedor> optionalProveedor = proveedorRepository.findById(proveedorId);
        if (optionalProveedor.isPresent()) {
            Proveedor proveedor = optionalProveedor.get();

            // Buscar la relación a eliminar
            ProveedorProducto relacionAEliminar = proveedor.getProveedorProductos().stream()
                    .filter(relacion -> relacion.getProductoId().equals(productoId))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Relación no encontrada"));

            // Eliminar la relación utilizando el método removeProveedorProducto
            proveedor.removeProveedorProducto(relacionAEliminar);

            // Guardar el proveedor actualizado
            proveedorRepository.save(proveedor);
        } else {
            throw new RuntimeException("Proveedor no encontrado");
        }
    }

}
