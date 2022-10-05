package com.cruzpet.models;

import com.cruzpet.entitys.*;

import java.time.LocalTime;
import java.util.List;

public class DisponibilidadModel {

    public DisponibilidadModel(DisponibilidadEntity disponibilidadEntity) {
        this.idDisponibilidad = disponibilidadEntity.getIdDisponibilidad();
        this.diaDisponibilidad = disponibilidadEntity.getDiaDisponibilidad();
        this.mesDisponibilidad = disponibilidadEntity.getMesDisponibilidad();
        this.yearDisponibilidad = disponibilidadEntity.getYearDisponibilidad();
        this.horaEntrada = disponibilidadEntity.getHoraEntrada();
        this.horaSalida = disponibilidadEntity.getHoraSalida();
        this.ipsafk = disponibilidadEntity.getIpsafk();
        this.intervalos = disponibilidadEntity.getIntervalos();
        this.estado = disponibilidadEntity.getEstado();
        this.veterinarioDisponibilidadeEntities = disponibilidadEntity.getVeterinarioDisponibilidades();
    }
    public DisponibilidadModel(Integer idDisponibilidad, String diaDisponibilidad, String mesDisponibilidad, String yearDisponibilidad, LocalTime horaEntrada, LocalTime horaSalida, IpsaEntity ipsaEntity, String estado) {
        this.idDisponibilidad = idDisponibilidad;
        this.diaDisponibilidad = diaDisponibilidad;
        this.mesDisponibilidad = mesDisponibilidad;
        this.yearDisponibilidad = yearDisponibilidad;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.ipsafk = ipsaEntity;
        this.estado = estado;
    }

    public DisponibilidadModel(Integer idDisponibilidad, String diaDisponibilidad, String mesDisponibilidad, String yearDisponibilidad, LocalTime horaEntrada,  List<IntervalosEntity> intervalosEntity, LocalTime horaSalida, String estado) {
        this.idDisponibilidad = idDisponibilidad;
        this.diaDisponibilidad = diaDisponibilidad;
        this.mesDisponibilidad = mesDisponibilidad;
        this.yearDisponibilidad = yearDisponibilidad;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.intervalos = intervalosEntity;
        this.estado = estado;
    }

    public DisponibilidadModel(Integer idDisponibilidad, String diaDisponibilidad, String mesDisponibilidad, String yearDisponibilidad, LocalTime horaEntrada, LocalTime horaSalida, List<VeterinarioDisponibilidadesEntity> veterinarioDisponibilidadeEntities, String estado) {
        this.idDisponibilidad = idDisponibilidad;
        this.diaDisponibilidad = diaDisponibilidad;
        this.mesDisponibilidad = mesDisponibilidad;
        this.yearDisponibilidad = yearDisponibilidad;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.veterinarioDisponibilidadeEntities = veterinarioDisponibilidadeEntities;
        this.estado = estado;
    }

    private Integer idDisponibilidad;

    private String diaDisponibilidad;

    private String mesDisponibilidad;

    private String yearDisponibilidad;

    private LocalTime horaEntrada;

    private LocalTime horaSalida;

    private List<IntervalosEntity> intervalos;

    private IpsaEntity ipsafk;

    private List<VeterinarioDisponibilidadesEntity> veterinarioDisponibilidadeEntities;

    private String estado;

    public List<VeterinarioDisponibilidadesEntity> getVeterinarioDisponibilidadeEntities() {
        return veterinarioDisponibilidadeEntities;
    }

    public void setVeterinarioDisponibilidadeEntities(List<VeterinarioDisponibilidadesEntity> veterinarioDisponibilidadeEntities) {
        this.veterinarioDisponibilidadeEntities = veterinarioDisponibilidadeEntities;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<VeterinarioDisponibilidadesEntity> getVeterinarioDisponibilidades() {
        return veterinarioDisponibilidadeEntities;
    }

    public void setVeterinarioDisponibilidades(List<VeterinarioDisponibilidadesEntity> veterinarioDisponibilidadeEntities) {
        this.veterinarioDisponibilidadeEntities = veterinarioDisponibilidadeEntities;
    }

    public Integer getIdDisponibilidad() {
        return idDisponibilidad;
    }

    public void setIdDisponibilidad(Integer idDisponibilidad) {
        this.idDisponibilidad = idDisponibilidad;
    }

    public String getDiaDisponibilidad() {
        return diaDisponibilidad;
    }

    public void setDiaDisponibilidad(String diaDisponibilidad) {
        this.diaDisponibilidad = diaDisponibilidad;
    }

    public String getMesDisponibilidad() {
        return mesDisponibilidad;
    }

    public void setMesDisponibilidad(String mesDisponibilidad) {
        this.mesDisponibilidad = mesDisponibilidad;
    }

    public String getYearDisponibilidad() {
        return yearDisponibilidad;
    }

    public void setYearDisponibilidad(String yearDisponibilidad) {
        this.yearDisponibilidad = yearDisponibilidad;
    }

    public IpsaEntity getIpsafk() {
        return ipsafk;
    }

    public void setIpsafk(IpsaEntity ipsafk) {
        this.ipsafk = ipsafk;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public List<IntervalosEntity> getIntervalos() {
        return intervalos;
    }

    public void setIntervalos(List<IntervalosEntity> intervalos) {
        this.intervalos = intervalos;
    }
}
