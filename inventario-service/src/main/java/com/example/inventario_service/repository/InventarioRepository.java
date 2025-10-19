package com.example.inventario_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventario_service.model.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {}
