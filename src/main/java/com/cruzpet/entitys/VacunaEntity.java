package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vacunas")
public class VacunaEntity {

    public VacunaEntity() {
    }

    public VacunaEntity(Integer cantidadVacunas, List<VacunaDetalleEntity> vacunasDetalles, AdministradorEntity administradorCreador) {
        this.cantidadVacunas = cantidadVacunas;
        this.vacunasDetalles = vacunasDetalles;
        this.administradorCreador = administradorCreador;
    }

    public VacunaEntity(Integer idVacuna, Integer cantidadVacunas, List<VacunaDetalleEntity> vacunasDetalles, AdministradorEntity administradorCreador) {
        this.idVacuna = idVacuna;
        this.cantidadVacunas = cantidadVacunas;
        this.vacunasDetalles = vacunasDetalles;
        this.administradorCreador = administradorCreador;
    }

    @Id
    @GeneratedValue(generator = "secuenciavacunas")
    @SequenceGenerator(name = "secuenciavacunas",sequenceName = "autovacunas", allocationSize = 1)
    @Column(name = "idvacuna")
    private Integer idVacuna;

    @Column(name = "cantidadvacunas", nullable = false)
    private Integer cantidadVacunas;

    @ManyToMany
    @JoinTable(
            name = "vacunas_vacunasdetalles",
            joinColumns = @JoinColumn(name = "idvacunasfk"),
            inverseJoinColumns = @JoinColumn(name = "idvacunasdetallesfk")
    )
    private List<VacunaDetalleEntity> vacunasDetalles;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idadministradorfk")
    private AdministradorEntity administradorCreador;

    @JsonIgnore
    @OneToMany(mappedBy = "vacunasEntity", fetch = FetchType.LAZY)
    private List<VacunaAplicadaEntity> vacunaAplicadaEntity;

    public Integer getIdVacuna() {
        return idVacuna;
    }

    public void setIdVacuna(Integer idVacuna) {
        this.idVacuna = idVacuna;
    }

    public Integer getCantidadVacuanas() {
        return cantidadVacunas;
    }

    public void setCantidadVacuanas(Integer cantidadVacuanas) {
        this.cantidadVacunas = cantidadVacuanas;
    }

    public Integer getCantidadVacunas() {
        return cantidadVacunas;
    }

    public void setCantidadVacunas(Integer cantidadVacunas) {
        this.cantidadVacunas = cantidadVacunas;
    }

    public List<VacunaDetalleEntity> getVacunasDetalles() {
        return vacunasDetalles;
    }

    public void setVacunasDetalles(List<VacunaDetalleEntity> vacunasDetalles) {
        this.vacunasDetalles = vacunasDetalles;
    }

    public AdministradorEntity getAdministradorCreador() {
        return administradorCreador;
    }

    public void setAdministradorCreador(AdministradorEntity administradorCreador) {
        this.administradorCreador = administradorCreador;
    }
}
