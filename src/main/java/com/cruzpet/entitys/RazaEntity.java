package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "razas")
public class RazaEntity {

    public RazaEntity() {
    }

    public RazaEntity(Integer idRaza, String nombreRaza, AdministradorEntity administradorCreador, TipoMascotaEntity tipoMascotaEntity) {
        this.idRaza = idRaza;
        this.nombreRaza = nombreRaza;
        this.administradorCreador = administradorCreador;
        this.tipoMascotaEntity = tipoMascotaEntity;
    }

    @Id
    @GeneratedValue(generator = "secuenciarazas")
    @SequenceGenerator(name = "secuenciarazas",sequenceName = "autorazas", allocationSize = 1)
    @Column(name = "idraza")
    private Integer idRaza;

    @Column(name = "nombreraza", nullable = false, length = 100)
    private String nombreRaza;

    @JsonIgnore
    @OneToMany(mappedBy = "razaMascota", fetch = FetchType.LAZY)
    private List<MascotaEntity> mascota;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idtipomascotafk")
    private TipoMascotaEntity tipoMascotaEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idadministradorfk")
    private AdministradorEntity administradorCreador;

    public TipoMascotaEntity getTipoMascotaEntity() {
        return tipoMascotaEntity;
    }

    public void setTipoMascotaEntity(TipoMascotaEntity tipoMascotaEntity) {
        this.tipoMascotaEntity = tipoMascotaEntity;
    }

    public Integer getIdRaza() {
        return idRaza;
    }

    public void setIdRaza(Integer idRaza) {
        this.idRaza = idRaza;
    }

    public String getNombreRaza() {
        return nombreRaza;
    }

    public void setNombreRaza(String nombreRaza) {
        this.nombreRaza = nombreRaza;
    }

    public AdministradorEntity getAdministradorCreador() {
        return administradorCreador;
    }

    public void setAdministradorCreador(AdministradorEntity administradorCreador) {
        this.administradorCreador = administradorCreador;
    }
}
