package com.cruzpet.models;

import com.cruzpet.entitys.DisponibilidadEntity;
import com.cruzpet.entitys.VeterinarioDisponibilidadesEntity;
import com.cruzpet.entitys.VeterinarioEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class VeterinarioDisponibilidadesModel {

    public VeterinarioDisponibilidadesModel(VeterinarioDisponibilidadesEntity veterinarioDisponibilidadesEntity) {
        this.id = veterinarioDisponibilidadesEntity.getId();
        this.disponibilidadEntity = veterinarioDisponibilidadesEntity.getDisponibilidadEntity();
        this.veterinarioEntity = veterinarioDisponibilidadesEntity.getVeterinarioEntity();
    }

    public VeterinarioDisponibilidadesModel(Integer id,DisponibilidadEntity disponibilidadEntity) {
        this.id = id;
        this.disponibilidadEntity = disponibilidadEntity;
    }

    public VeterinarioDisponibilidadesModel(Integer id, DisponibilidadEntity disponibilidadEntity, VeterinarioEntity veterinarioEntity) {
        this.id = id;
        this.disponibilidadEntity = disponibilidadEntity;
        this.veterinarioEntity = veterinarioEntity;
    }

    private Integer id;

    private DisponibilidadEntity disponibilidadEntity;

    private VeterinarioEntity veterinarioEntity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DisponibilidadEntity getDisponibilidadEntity() {
        return disponibilidadEntity;
    }

    public void setDisponibilidadEntity(DisponibilidadEntity disponibilidadEntity) {
        this.disponibilidadEntity = disponibilidadEntity;
    }

    public VeterinarioEntity getVeterinarioEntity() {
        return veterinarioEntity;
    }

    public void setVeterinarioEntity(VeterinarioEntity veterinarioEntity) {
        this.veterinarioEntity = veterinarioEntity;
    }
}
