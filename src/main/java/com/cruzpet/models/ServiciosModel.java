package com.cruzpet.models;

import com.cruzpet.entitys.ServiciosEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class ServiciosModel {

    public ServiciosModel(ServiciosEntity serviciosEntity) {
        this.idServicio = serviciosEntity.getIdServicio();
        this.nombreServicio = serviciosEntity.getNombreServicio();
        this.rutaImagenServicio = serviciosEntity.getRutaImagenServicio();
    }

    private Integer idServicio;

    private String nombreServicio;

    private String rutaImagenServicio;

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getRutaImagenServicio() {
        return rutaImagenServicio;
    }

    public void setRutaImagenServicio(String rutaImaenServicio) {
        this.rutaImagenServicio = rutaImaenServicio;
    }
}
