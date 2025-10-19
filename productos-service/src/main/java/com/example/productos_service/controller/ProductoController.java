package com.example.productos_service.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.productos_service.model.Producto;
import com.example.productos_service.service.ProductoService;

import java.util.Map;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService service;

    @PostMapping
    public ResponseEntity<Map<String, Object>> crear(@RequestBody Producto p) {
        return ResponseEntity.ok(Map.of("data", service.crear(p)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(Map.of("data", service.obtener(id)));
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> listar(Pageable pageable) {
        return ResponseEntity.ok(Map.of("data", service.listar(pageable)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizar(@PathVariable Long id, @RequestBody Producto p) {
        return ResponseEntity.ok(Map.of("data", service.actualizar(id, p)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
