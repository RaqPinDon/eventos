package org.grupoplan.qplan.dto;

//este dto se utiliza para listar los clientes dentro de la administración

public class ClienteDto{
    
    private Long id;

    private String email;

    private String nombre;

    private String apellidos;

    private String fechaNacimiento;

    private String sexo;

    private String telefono;

    private String direccion;

    private String role;

    private String fechaAlta;

    private Integer numEventos;



   


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
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



    public String getFechaNacimiento() {
        return fechaNacimiento;
    }


    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }



    public String getSexo() {
        return sexo;
    }


    public void setSexo(String sexo) {
        this.sexo = sexo;
    }



    public String getTelefono() {
        return telefono;
    }



    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }



    public String getDireccion() {
        return direccion;
    }


    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }



    public String getRole() {
        return role;
    }



    public void setRole(String role) {
        this.role = role;
    }



    public String getFechaAlta() {
        return fechaAlta;
    }



    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }


    public Integer getNumEventos() {
        return numEventos;
    }



    public void setNumEventos(Integer numEventos) {
        this.numEventos = numEventos;
    }



    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

}


