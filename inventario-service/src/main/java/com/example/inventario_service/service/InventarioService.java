package com.example.inventario_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.example.inventario_service.model.Inventario;
import com.example.inventario_service.repository.InventarioRepository;
import com.example.inventario_service.exception.BadRequestException;
import com.example.inventario_service.exception.ExternalServiceException;
import com.example.inventario_service.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventarioService {

    private final InventarioRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();

    public Inventario obtener(Long productoId) {
        try {
            validarProducto(productoId);
            return repository.findById(productoId)
                    .orElseThrow(() -> new ResourceNotFoundException("Inventario con ID " + productoId + " no encontrado"));
        } catch (ResourceNotFoundException e) {
            log.warn("Inventario no encontrado: {}", e.getMessage());
            throw e;
        } catch (ExternalServiceException e) {
            log.error("Error consultando producto externo: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al obtener inventario: {}", e.getMessage(), e);
            throw new RuntimeException("Error interno al obtener inventario");
        }
    }

    public Inventario actualizar(Long productoId, int cantidad) {
        try {
            if (cantidad < 0) {
                throw new BadRequestException("La cantidad no puede ser negativa");
            }

            validarProducto(productoId);

            Inventario inv = repository.findById(productoId)
                    .orElse(new Inventario(productoId, 0));

            inv.setCantidad(cantidad);
            repository.save(inv);
            log.info("Inventario actualizado correctamente para producto {}", productoId);

            return inv;
        } catch (BadRequestException | ResourceNotFoundException e) {
            log.warn("Error de validación: {}", e.getMessage());
            throw e;
        } catch (ExternalServiceException e) {
            log.error("Error externo al consultar producto: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al actualizar inventario: {}", e.getMessage(), e);
            throw new RuntimeException("Error interno al actualizar inventario");
        }
    }

    private void validarProducto(Long id) {
        try {
            restTemplate.getForObject("http://localhost:8080/api/productos/" + id, Object.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException("Producto con ID " + id + " no encontrado");
        } catch (ResourceAccessException e) {
            throw new ExternalServiceException("No se pudo acceder al servicio de productos");
        } catch (Exception e) {
            throw new ExternalServiceException("Error desconocido al validar producto");
        }
    }
}
