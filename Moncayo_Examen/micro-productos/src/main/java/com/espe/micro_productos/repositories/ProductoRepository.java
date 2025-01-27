package com.espe.micro_productos.repositories;

import com.espe.micro_productos.model.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
