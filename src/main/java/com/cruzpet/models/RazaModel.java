package com.cruzpet.models;

import com.cruzpet.entitys.AdministradorEntity;
import com.cruzpet.entitys.RazaEntity;
import com.cruzpet.entitys.TipoMascotaEntity;

import javax.persistence.Column;
import javax.persistence.Id;

public class RazaModel {

    public RazaModel() {
    }

    public RazaModel(RazaEntity razaEntity) {
        this.idRaza = razaEntity.getIdRaza();
        this.nombreRaza = razaEntity.getNombreRaza();
        this.administradorCreador = razaEntity.getAdministradorCreador();
        this.tipoMascotaEntity = razaEntity.getTipoMascotaEntity();
    }

    private Integer idRaza;

    private String nombreRaza;

    private AdministradorEntity administradorCreador;

    private TipoMascotaEntity tipoMascotaEntity;

    public TipoMascotaEntity getTipoMascotaEntity() {
        return tipoMascotaEntity;
    }

    public void setTipoMascotaEntity(TipoMascotaEntity tipoMascotaEntity) {
        this.tipoMascotaEntity = tipoMascotaEntity;
    }

    public Integer getIdRaza() {
        return idRaza;
    }

    public void setIdRaza(Integer idRaza) {
        this.idRaza = idRaza;
    }

    public String getNombreRaza() {
        return nombreRaza;
    }

    public void setNombreRaza(String nombreRaza) {
        this.nombreRaza = nombreRaza;
    }

    public AdministradorEntity getAdministradorCreador() {
        return administradorCreador;
    }

    public void setAdministradorCreador(AdministradorEntity administradorCreador) {
        this.administradorCreador = administradorCreador;
    }
}
