package com.cruzpet.models;

import com.cruzpet.entitys.AdministradorEntity;
import com.cruzpet.entitys.CiudadEntity;
import com.cruzpet.entitys.DepartamentoEntity;

import javax.persistence.*;

public class CiudadModel {

    public CiudadModel() {
    }

    public CiudadModel(CiudadEntity ciudadEntity) {
        this.idCiudad = ciudadEntity.getIdCiudad();
        this.nombreCiudad = ciudadEntity.getNombreCiudad();
        this.departamentoOrigen = ciudadEntity.getDepartamentoOrigen();
        this.administrador = ciudadEntity.getAdministradorCreador();
    }

    public CiudadModel(Integer idCiudad, String nombreCiudad, DepartamentoEntity departamentoOrigen) {
        this.idCiudad = idCiudad;
        this.nombreCiudad = nombreCiudad;
        this.departamentoOrigen = departamentoOrigen;
    }

    private Integer idCiudad;

    private String nombreCiudad;

    private DepartamentoEntity departamentoOrigen;

    private AdministradorEntity administrador;

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

    public AdministradorEntity getAdministrador() {
        return administrador;
    }

    public void setAdministrador(AdministradorEntity administrador) {
        this.administrador = administrador;
    }
}
