package org.grupoplan.qplan.entity;

import jakarta.persistence.*;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Entity
@Table(name="clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Evento> listaEventos;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="fecha_nacimiento")
    private Date fechaNacimiento;

    private String sexo;
    //Sexo: M = Masculino F=Femenino X=indeterminado

    //-------CONSTRUCTORES----


    public Cliente() {
    }

    public Cliente(Long id, User user, List<Evento> listaEventos, Date fechaNacimiento, String sexo) {
        this.id = id;
        this.user = user;
        this.listaEventos = listaEventos;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }

    public Date getfechaNacimiento() {
        return fechaNacimiento;
    }

    public void setfechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", user=" + user +
                ", listaEventos=" + listaEventos +
                ", fechaNacimiento=" + fechaNacimiento +
                ", sexo='" + sexo + '\'' +
                '}';
    }


    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }



    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}
