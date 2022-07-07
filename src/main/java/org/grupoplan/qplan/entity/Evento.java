package org.grupoplan.qplan.entity;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;
import java.util.Date;


@Entity
@Table(name="eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name="nombre_evento")
    private String nombreEvento;

    private String descripcion;

    private String imagen;

    private Integer estado;
    //Estado: 0: Caducado, 1: Aforo Completo, 2: Abierto, 3: Cerrado, 4: Cancelado

    private String direccion;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private CodigoPostal codigoPostal;

    @Column(name="fecha_inicio")
    private Date fechaInicio;

    @Column(name="fecha_fin")
    private Date fechaFin;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Anunciante anunciante;

    @ManyToMany(mappedBy = "listaEventos", cascade = CascadeType.ALL)
    private List<Cliente> asistentesEventos;

    private Integer aforo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    public Categoria categoria;


    //----CONSTRUCTORES-----

    public Evento(){};

    public Evento(Long id,String nombreEvento,String descripcion,String imagen, Integer estado,
                  String direccion,CodigoPostal codigoPostal, Date fechaInicio, Date fechaFin,
                  Anunciante anunciante,List<Cliente> asistentesEventos, Integer aforo,
                  Categoria categoria){

        this.id=id;
        this.nombreEvento=nombreEvento;
        this.descripcion=descripcion;
        this.imagen=imagen;
        this.estado=estado;
        this.direccion=direccion;
        this.codigoPostal=codigoPostal;
        this.fechaInicio=fechaInicio;
        this.fechaFin=fechaFin;
        this.anunciante=anunciante;
        this.asistentesEventos=asistentesEventos;
        this.aforo=aforo;
        this.categoria=categoria;

    }


    //-------GETER AND SETER


    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public String getNombreEvento() {
        return nombreEvento;
    }



    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }



    public String getDescripcion() {
        return descripcion;
    }



    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    public String getImagen() {
        return imagen;
    }



    public void setImagen(String imagen) {
        this.imagen = imagen;
    }



    public Integer getEstado() {
        return estado;
    }


    public void setEstado(Integer estado) {
        this.estado = estado;
    }


    public String getDireccion() {
        return direccion;
    }


    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public CodigoPostal getCodigoPostal() {
        return codigoPostal;
    }

    
    public void setCodigoPostal(CodigoPostal codigoPostal) {
        this.codigoPostal = codigoPostal;
    }



    public Date getFechaInicio() {
        return fechaInicio;
    }



    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    public Date getFechaFin() {
        return fechaFin;
    }


    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }


    public Anunciante getAnunciante() {
        return anunciante;
    }


    public void setAnunciante(Anunciante anunciante) {
        this.anunciante = anunciante;
    }


    public List<Cliente> getAsistentesEventos() {
        return asistentesEventos;
    }


    public void setAsistentesEventos(List<Cliente> asistentesEventos) {
        this.asistentesEventos = asistentesEventos;
    }


    public Integer getAforo() {
        return aforo;
    }


    public void setAforo(Integer aforo) {
        this.aforo = aforo;
    }


    public Categoria getCategoria() {
        return categoria;
    }


    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }



    //--------------TOSTRING------------

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", nombreEvento='" + nombreEvento + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", estado=" + estado +
                ", direccion='" + direccion + '\'' +
                ", codigoPostal=" + codigoPostal +
                ", fechaInicio=" + fechaInicio +
                ", fechaFIn=" + fechaFin +
                ", anunciante=" + anunciante +
                ", asistentesEventos=" + asistentesEventos +
                ", aforo=" + aforo +
                ", categoria=" + categoria +
                '}';
    }

    

}
