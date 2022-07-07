package org.grupoplan.qplan.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.grupoplan.qplan.entity.Anunciante;


@Repository
public interface AnuncianteRepository extends JpaRepository<Anunciante,Long> {



}
