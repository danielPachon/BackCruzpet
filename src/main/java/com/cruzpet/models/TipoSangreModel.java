package com.cruzpet.models;

import com.cruzpet.entitys.AdministradorEntity;
import com.cruzpet.entitys.TipoMascotaEntity;
import com.cruzpet.entitys.TipoSangreEntity;

import javax.persistence.Column;
import javax.persistence.Id;

public class TipoSangreModel {

    public TipoSangreModel() {
    }

    public TipoSangreModel(TipoSangreEntity tipoSangreEntity) {
        this.idTipoSangre = tipoSangreEntity.getIdTipoSangre();
        this.tipoSangre = tipoSangreEntity.getTipoSangre();
        this.administradorEntity = tipoSangreEntity.getAdministradorCreador();
        this.tipoMascotaEntity = tipoSangreEntity.getTipoMascotaEntity();
    }

    private Integer idTipoSangre;

    private String tipoSangre;

    private AdministradorEntity administradorEntity;

    private TipoMascotaEntity tipoMascotaEntity;

    public TipoMascotaEntity getTipoMascotaEntity() {
        return tipoMascotaEntity;
    }

    public void setTipoMascotaEntity(TipoMascotaEntity tipoMascotaEntity) {
        this.tipoMascotaEntity = tipoMascotaEntity;
    }

    public Integer getIdTipoSangre() {
        return idTipoSangre;
    }

    public void setIdTipoSangre(Integer idTipoSangre) {
        this.idTipoSangre = idTipoSangre;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public AdministradorEntity getAdministradorEntity() {
        return administradorEntity;
    }

    public void setAdministradorEntity(AdministradorEntity administradorEntity) {
        this.administradorEntity = administradorEntity;
    }
}
