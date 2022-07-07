package org.grupoplan.qplan.repository;

import org.grupoplan.qplan.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;


//para buscar si es administrador, cliente, o anunciante

public interface AuthorityRepository extends JpaRepository <Authority,Long> {

    Authority findByAuthority(String authority);
    //aquí especificamos lo que realmente queremos buscar

}
