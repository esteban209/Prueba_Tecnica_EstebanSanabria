package com.example.productos_service;

import com.example.productos_service.model.Producto;
import com.example.productos_service.repository.ProductoRepository;
import com.example.productos_service.service.ProductoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearProducto_deberiaGuardarCorrectamente() {
        Producto producto = new Producto(null, "Lapiz", 1200.0);
        when(productoRepository.save(any(Producto.class))).thenReturn(new Producto(1L, "Lapiz", 1200.0));

        Producto resultado = productoService.crear(producto);

        assertNotNull(resultado);
        assertEquals("Lapiz", resultado.getNombre());
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void actualizarProducto_existente_deberiaActualizarCorrectamente() {
        Producto existente = new Producto(1L, "Lapiz", 1200.0);
        Producto actualizado = new Producto(1L, "Cuaderno", 3500.0);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(productoRepository.save(any(Producto.class))).thenReturn(actualizado);

        Producto resultado = productoService.actualizar(1L, actualizado);

        assertEquals("Cuaderno", resultado.getNombre());
        assertEquals(3500.0, resultado.getPrecio());
    }

    @Test
    void actualizarProducto_noExistente_deberiaLanzarExcepcion() {
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            productoService.actualizar(99L, new Producto());
        });
    }
}

