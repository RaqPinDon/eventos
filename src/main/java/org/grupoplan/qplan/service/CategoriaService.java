package org.grupoplan.qplan.service;

import org.grupoplan.qplan.entity.Categoria;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    public List<Categoria> findAll();

    public Optional<Categoria> findById(Long id);

    public Categoria save(Categoria categoria);

    public void deleteById(Long id);

    public List<Categoria> getAllCategorias(Pageable pageable);

}
