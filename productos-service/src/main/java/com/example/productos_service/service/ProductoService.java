package com.example.productos_service.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.productos_service.model.Producto;
import com.example.productos_service.repository.ProductoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository repository;

    public Producto crear(Producto p) { return repository.save(p); }
    public Producto obtener(Long id) { return repository.findById(id).orElseThrow(); }
    public List<Producto> listar(Pageable pageable) { return repository.findAll(pageable).getContent(); }
    public Producto actualizar(Long id, Producto p) { p.setId(id); return repository.save(p); }
    public void eliminar(Long id) { repository.deleteById(id); }
}
