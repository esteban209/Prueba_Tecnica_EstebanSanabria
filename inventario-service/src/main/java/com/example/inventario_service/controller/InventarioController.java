package com.example.inventario_service.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.inventario_service.service.InventarioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/inventarios")
@RequiredArgsConstructor
@Slf4j
public class InventarioController {

    private final InventarioService service;

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtener(@PathVariable Long id) {
        try {
            var inventario = service.obtener(id);

            if (inventario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Inventario con ID " + id + " no encontrado"));
            }

            return ResponseEntity.ok(Map.of("data", inventario));

        } catch (IllegalArgumentException e) {
            log.warn("Error de solicitud: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            log.error("Error interno al obtener inventario con ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Ocurrió un error interno en el servidor"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizar(@PathVariable Long id, @RequestParam int cantidad) {
        try {
            if (cantidad < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "La cantidad no puede ser negativa"));
            }

            var inventarioActualizado = service.actualizar(id, cantidad);

            if (inventarioActualizado == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Inventario con ID " + id + " no encontrado"));
            }

            return ResponseEntity.ok(Map.of("data", inventarioActualizado));

        } catch (IllegalArgumentException e) {
            log.warn("Error de parámetros: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            log.error("Error interno al actualizar inventario con ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Ocurrió un error interno en el servidor"));
        }
    }
}
