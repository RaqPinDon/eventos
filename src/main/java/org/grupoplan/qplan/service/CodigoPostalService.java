package org.grupoplan.qplan.service;

import org.grupoplan.qplan.entity.CodigoPostal;

import java.util.List;
import java.util.Optional;

public interface CodigoPostalService {
    
    public List<CodigoPostal> findAll();

    public Optional<CodigoPostal> findById(Long id);

    public CodigoPostal save(CodigoPostal codigoPostal);

    public void deleteById(Long id);
}
