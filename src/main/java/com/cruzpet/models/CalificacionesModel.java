package com.cruzpet.models;

import com.cruzpet.entitys.CalificacionEntity;

import javax.persistence.Column;
import javax.persistence.Id;

public class CalificacionesModel {

    public CalificacionesModel() {
    }

    public CalificacionesModel(CalificacionEntity calificacionEntity) {
        this.idCalificacion = calificacionEntity.getIdCalificacion();
        this.numeroCalificacion = calificacionEntity.getNumeroCalificacion();
    }

    private Integer idCalificacion;

    private Integer numeroCalificacion;

    public Integer getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(Integer idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public Integer getNumeroCalificacion() {
        return numeroCalificacion;
    }

    public void setNumeroCalificacion(Integer numeroCalificacion) {
        this.numeroCalificacion = numeroCalificacion;
    }
}
