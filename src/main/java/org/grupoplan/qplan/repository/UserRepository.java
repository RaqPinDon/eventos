package org.grupoplan.qplan.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.grupoplan.qplan.entity.User;

import java.util.Optional;



//JpaRepository nos permite gestionar las operaciones CRUD fundamentales y los métodos propios de jpa. Entre ellas crear la paginación

//JpaRepository permite realizar búsquedas por campo según la entidad y tipo de la entidad(Long).


@Repository
public interface UserRepository extends JpaRepository <User,Long >{

    public Optional<User> findByEmail(String email);



}
