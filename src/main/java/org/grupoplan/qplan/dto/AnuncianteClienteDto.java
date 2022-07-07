package org.grupoplan.qplan.dto;

//Datos para el formulario de registro

public class AnuncianteClienteDto {
    
    private String email;

    private String password;

    private String nombre;

    private String apellidos;

    private String nombreLocal;

    private String descripcion;

    private String fechaNacimiento;

    private String sexo;

    private String telefono;

    private String codigoPostal;

    private String role;




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


    public String getNombreLocal() {
        return nombreLocal;
    }


    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }



    public String getDescripcion() {
        return descripcion;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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


    public String getCodigoPostal() {
        return codigoPostal;
    }


    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }



    public String getRole() {
        return role;
    }



    public void setRole(String role) {
        this.role = role;
    }

}


