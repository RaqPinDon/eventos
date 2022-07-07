package org.grupoplan.qplan.service;

import org.grupoplan.qplan.entity.CodigoPostal;

import org.grupoplan.qplan.repository.CodigoPostalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CodigoPostalServiceImpl implements CodigoPostalService {
    @Autowired
    private CodigoPostalRepository codigoPostalRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CodigoPostal> findAll() {
        return codigoPostalRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CodigoPostal> findById(Long id) {
        return codigoPostalRepository.findById(id);
    }

    @Override
    @Transactional
    public CodigoPostal save(CodigoPostal codigoPostal) {
        return codigoPostalRepository.save(codigoPostal);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        codigoPostalRepository.deleteById(id);
    }

}
