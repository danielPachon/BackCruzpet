package com.cruzpet.models;

import com.cruzpet.entitys.CarnetVacunacionEntity;
import com.cruzpet.entitys.MascotaEntity;
import com.cruzpet.entitys.VacunaAplicadaEntity;
import com.cruzpet.entitys.VacunaEntity;

import java.util.Date;
import java.util.List;

public class CarnetVacunacionModel {

    public CarnetVacunacionModel(CarnetVacunacionEntity carnetVacunacionEntity) {
        this.id = carnetVacunacionEntity.getIdCarnetVacunacion();
        this.peso = carnetVacunacionEntity.getPeso();
        this.mascota = carnetVacunacionEntity.getMascota();
        this.vacunaAplicadaEntities = carnetVacunacionEntity.getVacunaAplicadaEntity();
    }

    private Integer id;


    private Double peso;

    private VacunaEntity vacuna;

    private MascotaEntity mascota;

    private List<VacunaAplicadaEntity> vacunaAplicadaEntities;

    public List<VacunaAplicadaEntity> getVacunaAplicadaEntities() {
        return vacunaAplicadaEntities;
    }

    public void setVacunaAplicadaEntities(List<VacunaAplicadaEntity> vacunaAplicadaEntities) {
        this.vacunaAplicadaEntities = vacunaAplicadaEntities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public VacunaEntity getVacuna() {
        return vacuna;
    }

    public void setVacuna(VacunaEntity vacuna) {
        this.vacuna = vacuna;
    }

    public MascotaEntity getMascota() {
        return mascota;
    }

    public void setMascota(MascotaEntity mascota) {
        this.mascota = mascota;
    }
}
