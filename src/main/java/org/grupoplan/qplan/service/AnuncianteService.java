package org.grupoplan.qplan.service;

import org.grupoplan.qplan.entity.Anunciante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AnuncianteService {

    public List<Anunciante> findAll();

    public Optional<Anunciante> findById(Long id);

    public Anunciante save(Anunciante anunciante);

    public void deleteById(Long id);

    Page<Anunciante> getAllAnunciantes(Pageable pageable);
}
