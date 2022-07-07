package org.grupoplan.qplan.entity;

import jakarta.persistence.*;

@Entity
@Table(name="codigos_postales")
public class CodigoPostal {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="codigo_postal")
    private String cPostal;

    private String localidad;

    private String provincia;


    //-----CONSTRUCTORES----


    public CodigoPostal() {
    }

    public CodigoPostal(Long id, String cPostal, String localidad, String provincia) {
        this.id = id;
        this.cPostal = cPostal;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getcPostal() {
        return cPostal;
    }

    public void setcPostal(String cPostal) {
        this.cPostal = cPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "CodigoPostal{" +
                "id=" + id +
                ", cPostal='" + cPostal + '\'' +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}
