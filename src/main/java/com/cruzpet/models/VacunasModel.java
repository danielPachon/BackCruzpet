package com.cruzpet.models;


import com.cruzpet.entitys.AdministradorEntity;
import com.cruzpet.entitys.VacunaDetalleEntity;
import com.cruzpet.entitys.VacunaEntity;

import java.util.List;

public class VacunasModel {

    public VacunasModel() {
    }

    public VacunasModel(VacunaEntity vacunaEntity) {
        this.idVacuna = vacunaEntity.getIdVacuna();
        this.cantidadVacunas = vacunaEntity.getCantidadVacunas();
        this.vacunasDetalles = vacunaEntity.getVacunasDetalles();
        this.administradorEntity = vacunaEntity.getAdministradorCreador();
    }

    private Integer idVacuna;

    private Integer cantidadVacunas;

    private List<VacunaDetalleEntity> vacunasDetalles;

    private AdministradorEntity administradorEntity;

    public Integer getIdVacuna() {
        return idVacuna;
    }

    public void setIdVacuna(Integer idVacuna) {
        this.idVacuna = idVacuna;
    }

    public Integer getCantidadVacunas() {
        return cantidadVacunas;
    }

    public void setCantidadVacunas(Integer cantidadVacunas) {
        this.cantidadVacunas = cantidadVacunas;
    }

    public AdministradorEntity getAdministradorEntity() {
        return administradorEntity;
    }

    public void setAdministradorEntity(AdministradorEntity administradorEntity) {
        this.administradorEntity = administradorEntity;
    }

    public List<VacunaDetalleEntity> getVacunasDetalles() {
        return vacunasDetalles;
    }

    public void setVacunasDetalles(List<VacunaDetalleEntity> vacunasDetalles) {
        this.vacunasDetalles = vacunasDetalles;
    }
}
