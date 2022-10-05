package com.cruzpet.models;

import com.cruzpet.entitys.AdministradorEntity;
import com.cruzpet.entitys.BarrioEntity;
import com.cruzpet.entitys.CiudadEntity;

import javax.persistence.*;

public class BarrioModel {

    public BarrioModel() {
    }

    public BarrioModel(BarrioEntity barrioEntity) {
        this.idBarrio = barrioEntity.getIdBarrio();
        this.nombreBarrio = barrioEntity.getNombreBarrio();
        this.codigoPostal = barrioEntity.getCodigoPostal();
        this.direccion = barrioEntity.getNombreBarrio();
        this.ciudadOrigen = barrioEntity.getCiudadOrigen();
        this.administradorOrigen = barrioEntity.getAdministradorCreador();
    }

    private Integer idBarrio;

    private String nombreBarrio;

    private Integer codigoPostal;

    private String direccion;

    private CiudadEntity ciudadOrigen;

    private AdministradorEntity administradorOrigen;

    public Integer getIdBarrio() {
        return idBarrio;
    }

    public void setIdBarrio(Integer idBarrio) {
        this.idBarrio = idBarrio;
    }

    public String getNombreBarrio() {
        return nombreBarrio;
    }

    public void setNombreBarrio(String nombreBarrio) {
        this.nombreBarrio = nombreBarrio;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public CiudadEntity getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(CiudadEntity ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public AdministradorEntity getAdministradorOrigen() {
        return administradorOrigen;
    }

    public void setAdministradorOrigen(AdministradorEntity administradorOrigen) {
        this.administradorOrigen = administradorOrigen;
    }
}
