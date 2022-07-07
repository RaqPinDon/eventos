package org.grupoplan.qplan.service;

import org.grupoplan.qplan.entity.Evento;
import org.grupoplan.qplan.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class EventoServiceImpl implements EventoService{

    @Autowired
    private EventoRepository eventoRepository;



    @Override
    @Transactional(readOnly = true)
    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Evento> findById(Long id) {
        return eventoRepository.findById(id);
    }

    @Override
    @Transactional
    public Evento save(Evento evento) {
        return eventoRepository.save(evento);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        eventoRepository.deleteById(id);
    }


    @Override
    @Transactional
    public void updateEvento(Evento evento){eventoRepository.save(evento);}

    @Override
    @Transactional
    public Page<Evento> getAllEventos(Pageable pageable) {
        return eventoRepository.findAll(pageable);
    }

    @Override
    public List<Evento> findByNombreEventoContaining(String nombreEvento) {
        return eventoRepository.findByNombreEventoContaining(nombreEvento);
    }

    @Override
    public List<Evento> findByDescripcionContaining(String descripcion) {
        return eventoRepository.findByDescripcionContaining(descripcion);
    }

    @Override
    public List<Evento> findByCategoriaNombreContaining(String categorianombre) {
        return eventoRepository.findByCategoriaNombreContaining(categorianombre);
    }

    
}
