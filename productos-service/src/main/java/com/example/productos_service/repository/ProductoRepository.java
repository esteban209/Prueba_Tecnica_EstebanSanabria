package com.example.productos_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productos_service.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {}
