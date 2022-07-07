package org.grupoplan.qplan.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.grupoplan.qplan.entity.Categoria;



@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long>{

    Categoria findByNombre(String nombre);



}
