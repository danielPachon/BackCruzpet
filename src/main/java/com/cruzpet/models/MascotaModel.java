package com.cruzpet.models;

import com.cruzpet.entitys.*;

import java.util.Date;

public class MascotaModel {

    public MascotaModel() {
    }

    public MascotaModel(MascotaEntity mascotaEntity) {
        this.numeroIdentidad = mascotaEntity.getNumeroIdentidad();
        this.edad = mascotaEntity.getEdad();
        this.nombreMascota = mascotaEntity.getNombreMascota();
        this.fechaNacimiento = mascotaEntity.getFechaNacimiento();
        this.razaMascota = mascotaEntity.getRazaMascota();
        this.usuarioMascota = mascotaEntity.getClienteMascota();
        this.tipoMascota = mascotaEntity.getTipoMascota();
        this.generoMascota = mascotaEntity.getGeneroMascota();
        this.tipoSangreMascota = mascotaEntity.getTipoSangreMascota();
        this.estado = mascotaEntity.getEstado();
        this.imagenMascota = mascotaEntity.getImagenMascota();
        this.administradorEntity = mascotaEntity.getAdministradorCreador();
    }

    private String numeroIdentidad;

    private Integer edad;

    private  String nombreMascota;

    private Date fechaNacimiento;

    private String estado;

    private RazaEntity razaMascota;

    private ClienteEntity usuarioMascota;

    private TipoMascotaEntity tipoMascota;

    private GeneroEntity generoMascota;

    private TipoSangreEntity tipoSangreMascota;

    private String imagenMascota;

    private AdministradorEntity administradorEntity;

    public String getNumeroIdentidad() {
        return numeroIdentidad;
    }

    public void setNumeroIdentidad(String numeroIdentidad) {
        this.numeroIdentidad = numeroIdentidad;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public RazaEntity getRazaMascota() {
        return razaMascota;
    }

    public void setRazaMascota(RazaEntity razaMascota) {
        this.razaMascota = razaMascota;
    }

    public ClienteEntity getUsuarioMascota() {
        return usuarioMascota;
    }

    public void setUsuarioMascota(ClienteEntity usuarioMascota) {
        this.usuarioMascota = usuarioMascota;
    }

    public TipoMascotaEntity getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(TipoMascotaEntity tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public GeneroEntity getGeneroMascota() {
        return generoMascota;
    }

    public void setGeneroMascota(GeneroEntity generoMascota) {
        this.generoMascota = generoMascota;
    }

    public TipoSangreEntity getTipoSangreMascota() {
        return tipoSangreMascota;
    }

    public void setTipoSangreMascota(TipoSangreEntity tipoSangreMascota) {
        this.tipoSangreMascota = tipoSangreMascota;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getImagenMascota() {
        return imagenMascota;
    }

    public void setImagenMascota(String imagenMascota) {
        this.imagenMascota = imagenMascota;
    }

    public AdministradorEntity getAdministradorEntity() {
        return administradorEntity;
    }

    public void setAdministradorEntity(AdministradorEntity administradorEntity) {
        this.administradorEntity = administradorEntity;
    }
}



