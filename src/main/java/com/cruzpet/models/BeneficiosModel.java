package com.cruzpet.models;

import com.cruzpet.entitys.AdministradorEntity;
import com.cruzpet.entitys.BeneficiosEntity;
import com.cruzpet.entitys.PlanesEntity;

import javax.persistence.*;
import java.util.List;

public class BeneficiosModel {

    public BeneficiosModel(BeneficiosEntity beneficiosEntity) {
        this.idBeneficio = beneficiosEntity.getIdBeneficio();
        this.nombreBeneficio = beneficiosEntity.getNombreBeneficio();
        this.estado = beneficiosEntity.getEstado();
        this.administradorEntity = beneficiosEntity.getAdministradorCreador();
    }

    private Integer idBeneficio;

    private String nombreBeneficio;

    private String estado;

    private AdministradorEntity administradorEntity;

    public AdministradorEntity getAdministradorEntity() {
        return administradorEntity;
    }

    public void setAdministradorEntity(AdministradorEntity administradorEntity) {
        this.administradorEntity = administradorEntity;
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
