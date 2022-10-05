package com.cruzpet.entitys;

import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "beneficios")
public class BeneficiosEntity {

    public BeneficiosEntity() {
    }

    public BeneficiosEntity(String nombreBeneficio, String estado, AdministradorEntity administradorEntity) {
        this.nombreBeneficio = nombreBeneficio;
        this.estado = estado;
        this.administradorCreador = administradorEntity;
    }

    public BeneficiosEntity(Integer idBeneficio, AdministradorEntity administradorCreador) {
        this.idBeneficio = idBeneficio;
        this.administradorCreador = administradorCreador;
    }

    public BeneficiosEntity(Integer idBeneficio, String nombreBeneficio, String estado, AdministradorEntity administradorCreador) {
        this.idBeneficio = idBeneficio;
        this.nombreBeneficio = nombreBeneficio;
        this.estado = estado;
        this.administradorCreador = administradorCreador;
    }

    @Id
    @GeneratedValue(generator = "secuenciabeneficios")
    @SequenceGenerator(name = "secuenciabeneficios", sequenceName = "autobeneficios", allocationSize = 1)
    @Column(name = "idBeneficio")
    private Integer idBeneficio;

    @Column(name = "nombrebeneficio")
    private String nombreBeneficio;

    @Column(name = "estado", length = 2)
    private String estado = "i";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idadministradorfk")
    private AdministradorEntity administradorCreador;

    public AdministradorEntity getAdministradorCreador() {
        return administradorCreador;
    }

    public void setAdministradorCreador(AdministradorEntity administradorCreador) {
        this.administradorCreador = administradorCreador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdBeneficio() {
        return idBeneficio;
    }

    public void setIdBeneficio(Integer idBeneficio) {
        this.idBeneficio = idBeneficio;
    }

    public String getNombreBeneficio() {
        return nombreBeneficio;
    }

    public void setNombreBeneficio(String nombreBeneficio) {
        this.nombreBeneficio = nombreBeneficio;
    }
}
