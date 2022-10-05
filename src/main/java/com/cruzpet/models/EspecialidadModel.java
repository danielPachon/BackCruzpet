package com.cruzpet.models;

import com.cruzpet.entitys.AdministradorEntity;
import com.cruzpet.entitys.EspecialidadEntity;

public class EspecialidadModel {

    public EspecialidadModel() {
    }

    public EspecialidadModel(EspecialidadEntity especialidades) {
        this.idEspecialidad = especialidades.getIdEspecialidad();
        this.nombreEspecialidad = especialidades.getNombreEspecialidad();
        this.administradorEntity = especialidades.getAdministradorCreador();
    }

    private Integer idEspecialidad;

    private String nombreEspecialidad;

    private AdministradorEntity administradorEntity;

    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public AdministradorEntity getAdministradorEntity() {
        return administradorEntity;
    }

    public void setAdministradorEntity(AdministradorEntity administradorEntity) {
        this.administradorEntity = administradorEntity;
    }
}
