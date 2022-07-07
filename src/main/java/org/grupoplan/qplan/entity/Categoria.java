package org.grupoplan.qplan.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String icono;

    //relación de uno a muchos. En una categoría puede haber más de un evento
    @OneToMany(mappedBy = "categoria" , cascade = CascadeType.ALL)
    private List<Evento> listaEventosenCategoria;



    //-----CONSTRUCTORES----


    public Categoria() {
    }

    public Categoria(Long id, String nombre, String icono, List<Evento> listaEventosenCategoria) {
        this.id = id;
        this.nombre = nombre;
        this.icono = icono;
        this.listaEventosenCategoria = listaEventosenCategoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public List<Evento> getListaEventosenCategoria() {
        return listaEventosenCategoria;
    }

    public void setListaEventosenCategoria(List<Evento> listaEventosenCategoria) {
        this.listaEventosenCategoria = listaEventosenCategoria;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", icono='" + icono + '\'' +
                ", listaEventosenCategoria=" + listaEventosenCategoria +
                '}';
    }
}
