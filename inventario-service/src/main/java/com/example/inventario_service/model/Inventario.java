package com.example.inventario_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {
    @Id
    private Long productoId;
    private Integer cantidad;
}
