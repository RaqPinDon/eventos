package org.grupoplan.qplan.service;

import org.grupoplan.qplan.entity.Evento;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

//borrar, buscar por id, buscar todos (listar),guardar, modificar

public interface EventoService {

    public List<Evento> findAll();

    public Optional<Evento> findById(Long id);

    public Evento save(Evento evento);

    public void deleteById(Long id);

    public void updateEvento(Evento evento);

    Page<Evento> getAllEventos(Pageable pageable);

    //Containing busca dentro del vento una string. Es decir busca una string dentro de una string.
    // El otro string es evento, descripción y nombre categoría

    public List<Evento> findByNombreEventoContaining(String nombreEvento);

    public List<Evento> findByDescripcionContaining(String descripcion);

    public List<Evento> findByCategoriaNombreContaining(String categorianombre);
    
}
