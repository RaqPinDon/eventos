package org.grupoplan.qplan.dto;

//este dto se utiliza para listar los eventos dentro de la administración
public class EventoDto{
    private String nombreEvento;
    private String descripcion;
    private String imagen;
    private String estado;
    //Estado: 0: Caducado, 1: Aforo Completo, 2: Abierto, 3: Cerrado, 4: Cancelado
    private String estadobg;
    // Estadobg = bg-warning=1, bg-success = 2, bg-danger = 0,4,3
    private String direccion;
    private String codigoPostal;
    
    private Long idCodigoPostal;
    private Long idAnunciante;

    private String fechaInicio;
    private String fechaFin;
    private String anunciante;
    private Integer asistentesEventos;
    private String aforo;
    private String urlEvento;
    private String categoriaNombre;

    private Long categoria;

    public Long getCategoria() {
        return categoria;
    }

    public void setCategoria(Long categoria) {
        this.categoria = categoria;
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

    
    public String getEstado() {
        return estado;
    }

    
    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    public String getEstadobg() {
        return estadobg;
    }

    
    public void setEstadobg(String estadobg) {
        this.estadobg = estadobg;
    }

    
    public String getDireccion() {
        return direccion;
    }

    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
    public String getCodigoPostal() {
        return codigoPostal;
    }

    
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    
    public String getFechaInicio() {
        return fechaInicio;
    }

    
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    
    public String getFechaFin() {
        return fechaFin;
    }

    
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    
    public String getAnunciante() {
        return anunciante;
    }

    
    public void setAnunciante(String anunciante) {
        this.anunciante = anunciante;
    }

    
    public Integer getAsistentesEventos() {
        return asistentesEventos;
    }

    
    public void setAsistentesEventos(Integer asistentesEventos) {
        this.asistentesEventos = asistentesEventos;
    }

    
    public String getAforo() {
        return aforo;
    }

    
    public void setAforo(String aforo) {
        this.aforo = aforo;
    }

    
    public String getUrlEvento() {
        return urlEvento;
    }

    
    public void setUrlEvento(String urlEvento) {
        this.urlEvento = urlEvento;
    }

    
    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    
    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }


    
    public Long getIdCodigoPostal() {
        return idCodigoPostal;
    }
    
    public void setIdCodigoPostal(Long idCodigoPostal) {
        this.idCodigoPostal = idCodigoPostal;
    }

    
    public Long getIdAnunciante() {
        return idAnunciante;
    }

    
    public void setIdAnunciante(Long idAnunciante) {
        this.idAnunciante = idAnunciante;
    }

}

