package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tiposmascotas")
public class TipoMascotaEntity {

    public TipoMascotaEntity() {
    }

    public TipoMascotaEntity(Integer idTipoMascota, String tipoMascota, AdministradorEntity administradorCreador) {
        this.idTipoMascota = idTipoMascota;
        this.tipoMascota = tipoMascota;
        this.administradorCreador = administradorCreador;
    }

    @Id
    @GeneratedValue(generator = "secuenciatipomascota")
    @SequenceGenerator(name = "secuenciatipomascota", sequenceName = "autotipomascota", allocationSize = 1)
    @Column(name = "idtipomascota")
    private Integer idTipoMascota;

    @Column(name = "tipomascota", nullable = true, length = 50)
    private String tipoMascota;

    @JsonIgnore
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @OneToMany(mappedBy = "tipoMascota", fetch = FetchType.LAZY)
    private List<MascotaEntity> mascota;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idadministradorfk")
    private AdministradorEntity administradorCreador;



    public Integer getIdTipoMascota() {
        return idTipoMascota;
    }

    public void setIdTipoMascota(Integer idTipoMascota) {
        this.idTipoMascota = idTipoMascota;
    }

    public String getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(String tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public AdministradorEntity getAdministradorCreador() {
        return administradorCreador;
    }

    public void setAdministradorCreador(AdministradorEntity administradorCreador) {
        this.administradorCreador = administradorCreador;
    }
}
