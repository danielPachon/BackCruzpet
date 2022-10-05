package com.cruzpet.models;

import com.cruzpet.entitys.AdministradorEntity;
import com.cruzpet.entitys.VacunaDetalleEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class VacunaDetalleModel {

    public VacunaDetalleModel() {
    }

    public VacunaDetalleModel(VacunaDetalleEntity vacunaDetalleEntity) {
        this.idVacunasDetalles = vacunaDetalleEntity.getIdVacunasDetalles();
        this.nombreVacuna = vacunaDetalleEntity.getNombreVacuna();
        this.tipoVacuna = vacunaDetalleEntity.getTipoVacuna();
        this.periodoAplicacion = vacunaDetalleEntity.getPeriodoAplicacion();
        this.administradorEntity = vacunaDetalleEntity.getAdministradorCreador();
    }

    private Integer idVacunasDetalles;

    private String nombreVacuna;

    private  String tipoVacuna;

    private String periodoAplicacion;

    private AdministradorEntity administradorEntity;

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

    public AdministradorEntity getAdministradorEntity() {
        return administradorEntity;
    }

    public void setAdministradorEntity(AdministradorEntity administradorEntity) {
        this.administradorEntity = administradorEntity;
    }
}
