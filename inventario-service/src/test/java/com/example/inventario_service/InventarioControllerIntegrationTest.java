package com.example.inventario_service;

import com.example.inventario_service.model.Inventario;
import com.example.inventario_service.repository.InventarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class InventarioControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InventarioRepository inventarioRepository;

    @BeforeEach
    void limpiar() {
        inventarioRepository.deleteAll();
    }

    @Test
    void deberiaActualizarCantidadInventario() throws Exception {
        Inventario inv = inventarioRepository.save(new Inventario( null, 5));

        String json = "{\"cantidad\": 3}";

        mockMvc.perform(put("/api/inventarios/" + inv.getProductoId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cantidad").value(3));
    }
}
