package com.cruzpet.entitys;

import javax.persistence.*;

@Entity
@Table(name = "servicios")
public class ServiciosEntity {

    public ServiciosEntity() {
    }

    public ServiciosEntity(String nombreServicio, String rutaImagenServicio, AdministradorEntity administrador) {
        this.nombreServicio = nombreServicio;
        this.rutaImagenServicio = rutaImagenServicio;
        this.administrador = administrador;
    }

    @Id
    @GeneratedValue(generator = "secuenciaServicios")
    @SequenceGenerator(name = "secuenciaservicios", sequenceName = "autoservicios", allocationSize = 1)
    @Column(name = "idServicio")
    private Integer idServicio;

    @Column(name = "nombreservicico", nullable = false, length = 50)
    private String nombreServicio;

    @Column(name = "rutaimagenservicio", nullable = false, length = 200)
    private String rutaImagenServicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAdministradorFk")
    private AdministradorEntity administrador;

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getRutaImagenServicio() {
        return rutaImagenServicio;
    }

    public void setRutaImagenServicio(String rutaImagenServicio) {
        this.rutaImagenServicio = rutaImagenServicio;
    }

    public AdministradorEntity getAdministrador() {
        return administrador;
    }

    public void setAdministrador(AdministradorEntity administrador) {
        this.administrador = administrador;
    }
}
