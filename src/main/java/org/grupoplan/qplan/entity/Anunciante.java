package org.grupoplan.qplan.entity;

import java.util.List;

import jakarta.persistence.*;

//la entidad anunciante es un usuario que va a ser capaz de crear eventos y listarlos. Añadir la información de nombre local
// y describir el evento que crea. Además de todas las propiedades comunes que vienen recogidos en la clase user de la que hereda.

//mediante la anotación entity decimos que se trata de una entidad y que el nombre que recibe como tabla en la base de datos
//es anunciantes.
@Entity
@Table(name="anunciantes")
public class Anunciante {


    //mediante ambas anotaciones le decimos que es una entidad y que autoincremente automáticamente
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String nombreLocal;

    @OneToOne
    private User user;

    private String descripcion;


    @OneToMany(mappedBy = "anunciante", cascade = CascadeType.ALL)
    private List<Evento> listaEventos;


    //------------------CONSTRUCTORES--------------


    public Anunciante() {
    }

    public Anunciante(Long id, String nombreLocal, User user, String descripcion, List<Evento> listaEventos) {
        this.id = id;
        this.nombreLocal = nombreLocal;
        this.user = user;
        this.descripcion = descripcion;
        this.listaEventos = listaEventos;
    }

    //------------GETER AND SETER--------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }

    @Override
    public String toString() {
        return "Anunciante{" +
                "id=" + id +
                ", nombreLocal='" + nombreLocal + '\'' +
                ", user=" + user +
                ", descripcion='" + descripcion + '\'' +
                ", listaEventos=" + listaEventos +
                '}';
    }
}
