package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "carnetVacunacion")
public class CarnetVacunacionEntity {

    public CarnetVacunacionEntity() {
    }

    public CarnetVacunacionEntity(Integer idCarnetVacunacion, Double peso, List<VacunaAplicadaEntity> vacunaAplicadaEntity,MascotaEntity mascota) {
        this.idCarnetVacunacion = idCarnetVacunacion;
        this.peso = peso;
        this.mascota = mascota;
        this.vacunaAplicadaEntity = vacunaAplicadaEntity;
    }

    public CarnetVacunacionEntity( Double peso,  MascotaEntity mascota, List<VacunaAplicadaEntity> vacunaAplicadaEntity) {
        this.peso = peso;
        this.mascota = mascota;
        this.vacunaAplicadaEntity = vacunaAplicadaEntity;
    }

    @Id
    @GeneratedValue(generator = "secuenciaCarnetsVacunacion")
    @SequenceGenerator(name = "secuenciaCarnetsVacunacion", sequenceName = "autoCarnetVacunacion", allocationSize = 1)
    @Column(name = "idCarnetVacunacion")
    private Integer idCarnetVacunacion;

    @Column(name = "peso", nullable = false, length = 5)
    private Double peso;


    @OneToOne
    @JoinColumn(name = "idmascotafk")
    private MascotaEntity mascota;

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "vacuna_aplicada_carnet", joinColumns = @JoinColumn(name = "carnet_id"), inverseJoinColumns = @JoinColumn(name = "vacuna_aplicadas_id"))
    private List<VacunaAplicadaEntity> vacunaAplicadaEntity;

    public List<VacunaAplicadaEntity> getVacunaAplicadaEntity() {
        return vacunaAplicadaEntity;
    }

    public void setVacunaAplicadaEntity(List<VacunaAplicadaEntity> vacunaAplicadaEntity) {
        this.vacunaAplicadaEntity = vacunaAplicadaEntity;
    }

    public Integer getIdCarnetVacunacion() {
        return idCarnetVacunacion;
    }

    public void setIdCarnetVacunacion(Integer idCarnetVacunacion) {
        this.idCarnetVacunacion = idCarnetVacunacion;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public MascotaEntity getMascota() {
        return mascota;
    }

    public void setMascota(MascotaEntity mascota) {
        this.mascota = mascota;
    }
}

