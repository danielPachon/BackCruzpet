package com.cruzpet.models;

import com.cruzpet.entitys.GeneroEntity;
import com.cruzpet.entitys.MascotaEntity;
import com.cruzpet.entitys.TipoSangreEntity;

import javax.persistence.*;
import java.util.Date;

public class CarnetIdentificacionModel {

    public CarnetIdentificacionModel(Integer idCarnetIdentificacion, String lugarNacimiento, Double estatura, String fechaLugarEspedicion, MascotaEntity mascota, TipoSangreEntity tipoSangre, GeneroEntity genero) {
        this.idCarnetIdentificacion = idCarnetIdentificacion;
        this.lugarNacimiento = lugarNacimiento;
        this.estatura = estatura;
        this.fechaLugarEspedicion = fechaLugarEspedicion;
        this.mascota = mascota;
        this.tipoSangre = tipoSangre;
        this.genero = genero;
    }

    private Integer idCarnetIdentificacion;

    private String lugarNacimiento;

    private Double estatura;

    private String fechaLugarEspedicion;

    private MascotaEntity mascota;

    private TipoSangreEntity tipoSangre;

    private GeneroEntity genero;

    public Integer getIdCarnetIdentificacion() {
        return idCarnetIdentificacion;
    }

    public void setIdCarnetIdentificacion(Integer idCarnetIdentificacion) {
        this.idCarnetIdentificacion = idCarnetIdentificacion;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public Double getEstatura() {
        return estatura;
    }

    public void setEstatura(Double estatura) {
        this.estatura = estatura;
    }

    public String getFechaLugarEspedicion() {
        return fechaLugarEspedicion;
    }

    public void setFechaLugarEspedicion(String fechaLugarEspedicion) {
        this.fechaLugarEspedicion = fechaLugarEspedicion;
    }

    public MascotaEntity getMascota() {
        return mascota;
    }

    public void setMascota(MascotaEntity mascota) {
        this.mascota = mascota;
    }

    public TipoSangreEntity getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(TipoSangreEntity tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public GeneroEntity getGenero() {
        return genero;
    }

    public void setGenero(GeneroEntity genero) {
        this.genero = genero;
    }
}
