package org.grupoplan.qplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.grupoplan.qplan.entity.CodigoPostal;


@Repository
public interface CodigoPostalRepository extends JpaRepository<CodigoPostal, Long> {
}
