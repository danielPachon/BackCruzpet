package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "barrios")
public class BarrioEntity {

    public BarrioEntity() {
    }

    public BarrioEntity(String nombreBarrio, Integer codigoPostal, CiudadEntity ciudadOrigen, AdministradorEntity administradorCreador) {
        this.nombreBarrio = nombreBarrio;
        this.codigoPostal = codigoPostal;
        this.ciudadOrigen = ciudadOrigen;
        this.administradorCreador = administradorCreador;
    }

    public BarrioEntity(Integer idBarrio, String nombreBarrio, Integer codigoPostal, CiudadEntity ciudadOrigen, AdministradorEntity administradorCreador) {
        this.idBarrio = idBarrio;
        this.nombreBarrio = nombreBarrio;
        this.codigoPostal = codigoPostal;
        this.ciudadOrigen = ciudadOrigen;
        this.administradorCreador = administradorCreador;
    }

    @Id
    @GeneratedValue(generator = "secuenciabarrios")
    @SequenceGenerator(name = "secuenciabarrios", sequenceName = "autobarrios", allocationSize = 1)
    @Column(name = "idbarrio")
    private Integer idBarrio;

    @NotNull
    @Column(name = "nombrebarrio", nullable = false, length = 50)
    private String nombreBarrio;

    @Column(name = "codigopostal", nullable = false, length = 6)
    private Integer codigoPostal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idciudadfk")
    private CiudadEntity ciudadOrigen;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idadministradorfk")
    private AdministradorEntity administradorCreador;

    @JsonIgnore
    @OneToOne(mappedBy = "barrios", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private DireccionEntity direccionEntity;

    public Integer getIdBarrio() {
        return idBarrio;
    }

    public void setIdBarrio(Integer idBarrio) {
        this.idBarrio = idBarrio;
    }

    public String getNombreBarrio() {
        return nombreBarrio;
    }

    public void setNombreBarrio(String nombreBarrio) {
        this.nombreBarrio = nombreBarrio;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public CiudadEntity getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(CiudadEntity ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public AdministradorEntity getAdministradorCreador() {
        return administradorCreador;
    }

    public void setAdministradorCreador(AdministradorEntity administradorCreador) {
        this.administradorCreador = administradorCreador;
    }

    public DireccionEntity getDireccionEntity() {
        return direccionEntity;
    }

    public void setDireccionEntity(DireccionEntity direccionEntity) {
        this.direccionEntity = direccionEntity;
    }
}
