package com.cruzpet.models;

import com.cruzpet.entitys.AdministradorEntity;
import com.cruzpet.entitys.BeneficiosEntity;
import com.cruzpet.entitys.PlanesEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class PlanesModel {

    public PlanesModel() {
    }

    public PlanesModel(PlanesEntity planesEntity) {
        this.idPlan = planesEntity.getIdPlan();
        this.precio = planesEntity.getPrecio();
        this.contenidoplan = planesEntity.getContenidoplan();
        this.administradorOrigen = planesEntity.getAdministradorCreador();
        this.tituloPlan = planesEntity.getTituloPlan();
        this.beneficiosEntity = planesEntity.getBeneficiosEntity();
    }

    private Integer idPlan;

    private double precio;

    private String contenidoplan;

    private AdministradorEntity administradorOrigen;

    private String tituloPlan;

    private List<BeneficiosEntity> beneficiosEntity;

    public AdministradorEntity getAdministradorOrigen() {
        return administradorOrigen;
    }

    public void setAdministradorOrigen(AdministradorEntity administradorOrigen) {
        this.administradorOrigen = administradorOrigen;
    }

    public Integer getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getContenidoplan() {
        return contenidoplan;
    }

    public void setContenidoplan(String contenidoplan) {
        this.contenidoplan = contenidoplan;
    }

    public String getTituloPlan() {
        return tituloPlan;
    }

    public void setTituloPlan(String tituloPlan) {
        this.tituloPlan = tituloPlan;
    }

    public List<BeneficiosEntity> getBeneficiosEntity() {
        return beneficiosEntity;
    }

    public void setBeneficiosEntity(List<BeneficiosEntity> beneficiosEntity) {
        this.beneficiosEntity = beneficiosEntity;
    }
}
