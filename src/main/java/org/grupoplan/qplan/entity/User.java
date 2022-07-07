package org.grupoplan.qplan.entity;
import jakarta.persistence.*;

import java.util.Collection;
//las entidades son la manera que tenemos de declarar los campos
// que tiene la tabla que queremos que mapeé JPA (no tienen porque ser todos).
//--1º-- primeramente necesitamos añadir la entidad que mapeará los datos de la tabla de SQL
// que le especifiquemos de SQL para posteriormente ir transformándolos en objetos.
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;






@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)  ////Se indica que el campo ID es Autonumerico
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String telefono;

    @Column(nullable = false, name = "estado_activo")
    private Boolean estadoActivo;


    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Anunciante anunciante;


    
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Cliente cliente;

    private String nombre;

    private String apellidos;


    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="authorities_users", joinColumns=@JoinColumn(name="usuario_id"),
    inverseJoinColumns=@JoinColumn(name="authority_id"))
    private Collection<Authority> authority;
    //para darle un rol al usuario

    @CreatedDate
    @Column(name="fecha_alta")
    private Date fechaAlta;

    //------    CONSTRUCTORES----


    public User() {
    }

    public User(Long id, String email, String password, String telefono, Boolean estadoActivo,
                Anunciante anunciante, Cliente cliente, String nombre, String apellidos) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.estadoActivo = estadoActivo;
        this.anunciante = anunciante;
        this.cliente = cliente;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }


    //-----------GETER AND SETER---------


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Boolean getEstadoActivo() {
        return estadoActivo;
    }

    public void setEstadoActivo(Boolean estadoActivo) {
        this.estadoActivo = estadoActivo;
    }



    public Anunciante getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(Anunciante anunciante) {
        this.anunciante = anunciante;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Collection<Authority> getAuthority() {
        return authority;
    }

    public void setAuthority(Collection<Authority> authority) {
        this.authority = authority;
    }

    public Boolean isEstadoActivo() {
        return estadoActivo;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }
   
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", telefono='" + telefono + '\'' +
                ", estadoActivo=" + estadoActivo +
                ", anunciante=" + anunciante +
                ", cliente=" + cliente +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                '}';
    }

   

}
