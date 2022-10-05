package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "VACUNASDETALLES")
public class VacunaDetalleEntity {

    public VacunaDetalleEntity() {
    }

    public VacunaDetalleEntity(String nombreVacuna, String tipoVacuna, String periodoAplicacion, AdministradorEntity administradorCreador) {
        this.nombreVacuna = nombreVacuna;
        this.tipoVacuna = tipoVacuna;
        this.periodoAplicacion = periodoAplicacion;
        this.administradorCreador = administradorCreador;
    }

    public VacunaDetalleEntity(Integer idVacunasDetalles, String nombreVacuna, String tipoVacuna, String periodoAplicacion, AdministradorEntity administradorCreador) {
        this.idVacunasDetalles = idVacunasDetalles;
        this.nombreVacuna = nombreVacuna;
        this.tipoVacuna = tipoVacuna;
        this.periodoAplicacion = periodoAplicacion;
        this.administradorCreador = administradorCreador;
    }

    @Id
    @GeneratedValue(generator = "secuenciavacunasdetalles")
    @SequenceGenerator(name = "secuenciavacunasdetalles",sequenceName = "autovacunasdetalles", allocationSize = 1)
    @Column(name = "IDVACUNASDETALLES")
    private Integer idVacunasDetalles;

    @Column(name = "NOMBREVACUNA", nullable = false, length = 50)
    private String nombreVacuna;

    @Column(name = "TIPOVACUNA", nullable = false, length = 50)
    private  String tipoVacuna;

    @Column(name = "PERIODOAPLICACION", nullable = false, length = 150)
    private String periodoAplicacion;

    @JsonIgnore
    @ManyToMany(mappedBy = "vacunasDetalles")
    private List<VacunaEntity> vacuna;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idadministradorfk")
    private AdministradorEntity administradorCreador;

    public Integer getIdVacunasDetalles() {
        return idVacunasDetalles;
    }

    public void setIdVacunasDetalles(Integer idVacunasDetalles) {
        this.idVacunasDetalles = idVacunasDetalles;
    }

    public String getNombreVacuna() {
        return nombreVacuna;
    }

    public void setNombreVacuna(String nombreVacuna) {
        this.nombreVacuna = nombreVacuna;
    }

    public String getTipoVacuna() {
        return tipoVacuna;
    }

    public void setTipoVacuna(String tipoVacuna) {
        this.tipoVacuna = tipoVacuna;
    }

    public String getPeriodoAplicacion() {
        return periodoAplicacion;
    }

    public void setPeriodoAplicacion(String periodoAplicacion) {
        this.periodoAplicacion = periodoAplicacion;
    }

    public List<VacunaEntity> getVacuna() {
        return vacuna;
    }

    public void setVacuna(List<VacunaEntity> vacuna) {
        this.vacuna = vacuna;
    }

    public void incluirVacuna(VacunaEntity vacuna){
        this.vacuna.add(vacuna);
    }

    public AdministradorEntity getAdministradorCreador() {
        return administradorCreador;
    }

    public void setAdministradorCreador(AdministradorEntity administradorCreador) {
        this.administradorCreador = administradorCreador;
    }
}
