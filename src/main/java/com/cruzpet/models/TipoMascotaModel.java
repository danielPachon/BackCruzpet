package com.cruzpet.models;

import com.cruzpet.entitys.AdministradorEntity;
import com.cruzpet.entitys.TipoMascotaEntity;

public class TipoMascotaModel {

    public TipoMascotaModel() {
    }

    public TipoMascotaModel(TipoMascotaEntity tipoMascotaEntity) {
        this.idTipoMascota = tipoMascotaEntity.getIdTipoMascota();
        this.tipoMascota = tipoMascotaEntity.getTipoMascota();
        this.administradorEntity = tipoMascotaEntity.getAdministradorCreador();
        this.tipoMascota = tipoMascotaEntity.getTipoMascota();
    }

    private Integer idTipoMascota;

    private String tipoMascota;

    private AdministradorEntity administradorEntity;

    private TipoMascotaEntity tipoMascotaEntity;

    public TipoMascotaEntity getTipoMascotaEntity() {
        return tipoMascotaEntity;
    }

    public void setTipoMascotaEntity(TipoMascotaEntity tipoMascotaEntity) {
        this.tipoMascotaEntity = tipoMascotaEntity;
    }

    public Integer getIdTipoMascota() {
        return idTipoMascota;
    }

    public void setIdTipoMascota(Integer idTipoMascota) {
        this.idTipoMascota = idTipoMascota;
    }

    public String getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(String tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public AdministradorEntity getAdministradorEntity() {
        return administradorEntity;
    }

    public void setAdministradorEntity(AdministradorEntity administradorEntity) {
        this.administradorEntity = administradorEntity;
    }
}
