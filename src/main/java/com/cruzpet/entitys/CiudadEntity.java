package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ciudades")
public class CiudadEntity {

    public CiudadEntity() {
    }

    public CiudadEntity(String nombreCiudad, DepartamentoEntity departamentoOrigen, AdministradorEntity administradorCreador) {
        this.nombreCiudad = nombreCiudad;
        this.departamentoOrigen = departamentoOrigen;
        this.administradorCreador = administradorCreador;
    }

    public CiudadEntity(Integer idCiudad, String nombreCiudad, DepartamentoEntity departamentoOrigen, AdministradorEntity administradorCreador) {
        this.idCiudad = idCiudad;
        this.nombreCiudad = nombreCiudad;
        this.departamentoOrigen = departamentoOrigen;
        this.administradorCreador = administradorCreador;
    }

    public CiudadEntity(String nombreCiudad, DepartamentoEntity departamentoOrigen) {
        this.nombreCiudad = nombreCiudad;
        this.departamentoOrigen = departamentoOrigen;
    }

    @Id
    @GeneratedValue(generator = "secuenciaciudades")
    @SequenceGenerator(name = "secuenciaciudades", sequenceName = "autociudades", allocationSize = 1)
    @Column(name = "idciudad")
    private Integer idCiudad;

    @Column(name = "nombreciudad", nullable = false, length = 100)
    private String nombreCiudad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "iddepartamentofk")
    private  DepartamentoEntity departamentoOrigen;

    @JsonIgnore
    @OneToMany(mappedBy = "ciudadOrigen", fetch = FetchType.LAZY)
    private List<BarrioEntity> barrio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idadministradorfk")
    private AdministradorEntity administradorCreador;

    public Integer getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public DepartamentoEntity getDepartamentoOrigen() {
        return departamentoOrigen;
    }

    public void setDepartamentoOrigen(DepartamentoEntity departamentoOrigen) {
        this.departamentoOrigen = departamentoOrigen;
    }

    public AdministradorEntity getAdministradorCreador() {
        return administradorCreador;
    }

    public void setAdministradorCreador(AdministradorEntity administradorCreador) {
        this.administradorCreador = administradorCreador;
    }
}
