package com.cruzpet.models;

import com.cruzpet.entitys.AdministradorEntity;
import com.cruzpet.entitys.GeneroEntity;

public class GenerosModel {

    public GenerosModel() {
    }

    public GenerosModel(GeneroEntity generoEntity) {
        this.idGenero = generoEntity.getIdGenero();
        this.genero = generoEntity.getGenero();
        this.administrador = generoEntity.getAdministradorCreador();
    }

    private Integer idGenero;

    private String genero;

    private AdministradorEntity administrador;

    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public AdministradorEntity getAdministrador() {
        return administrador;
    }

    public void setAdministrador(AdministradorEntity administrador) {
        this.administrador = administrador;
    }
}
