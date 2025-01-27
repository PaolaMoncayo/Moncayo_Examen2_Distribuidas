package com.espe.micro_proveedores.repositories;

import com.espe.micro_proveedores.model.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
}
