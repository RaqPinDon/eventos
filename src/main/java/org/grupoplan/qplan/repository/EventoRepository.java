package org.grupoplan.qplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.grupoplan.qplan.entity.Evento;



//Con @jpa Repository le indico los métodos principales select, create, update, delete
@Repository
public interface EventoRepository extends JpaRepository<Evento,Long>{

    List<Evento> findByNombreEventoContaining(String nombreEvento);

    List<Evento> findByDescripcionContaining(String descripcion);

    List<Evento> findByCategoriaNombreContaining(String categorianombre);

}
