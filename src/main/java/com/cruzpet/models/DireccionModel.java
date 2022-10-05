package com.cruzpet.models;

import com.cruzpet.entitys.AdministradorEntity;
import com.cruzpet.entitys.BarrioEntity;
import com.cruzpet.entitys.DepartamentoEntity;
import com.cruzpet.entitys.DireccionEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

public class DireccionModel {

    public DireccionModel() {
    }

    public DireccionModel(DireccionEntity direccionEntity){

        this.idDireccion = direccionEntity.getIdDireccion();
        this.direccionCasa = direccionEntity.getDireccionCasa();
        this.barrio = direccionEntity.getBarrios();
        this.administrador = direccionEntity.getAdministradorCreador();
    }

    private Integer idDireccion;

    private String direccionCasa;

    private BarrioEntity barrio;

    private AdministradorEntity administrador;

    public Integer getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getDireccionCasa() {
        return direccionCasa;
    }

    public void setDireccionCasa(String direccionCasa) {
        this.direccionCasa = direccionCasa;
    }

    public BarrioEntity getBarrio() {
        return barrio;
    }

    public void setBarrio(BarrioEntity barrio) {
        this.barrio = barrio;
    }

    public AdministradorEntity getAdministrador() {
        return administrador;
    }

    public void setAdministrador(AdministradorEntity administrador) {
        this.administrador = administrador;
    }
}
