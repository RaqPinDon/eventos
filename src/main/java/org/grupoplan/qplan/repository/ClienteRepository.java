package org.grupoplan.qplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.grupoplan.qplan.entity.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

}
