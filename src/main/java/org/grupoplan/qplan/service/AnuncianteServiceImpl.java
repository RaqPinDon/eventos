package org.grupoplan.qplan.service;

import org.grupoplan.qplan.entity.Anunciante;
import org.grupoplan.qplan.repository.AnuncianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AnuncianteServiceImpl implements AnuncianteService{

    @Autowired
    private AnuncianteRepository anuncianteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Anunciante> findAll() {
        return anuncianteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Anunciante> findById(Long id) {
        return anuncianteRepository.findById(id);
    }

    @Override
    @Transactional
    public Anunciante save(Anunciante anunciante) {
        return anuncianteRepository.save(anunciante);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        anuncianteRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Page<Anunciante> getAllAnunciantes(Pageable pageable) {
        return anuncianteRepository.findAll(pageable);
    }

}
