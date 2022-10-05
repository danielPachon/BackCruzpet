package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipossangre")
public class TipoSangreEntity {

    public TipoSangreEntity() {
    }

    public TipoSangreEntity(String tipoSangre, AdministradorEntity administradorCreador, TipoMascotaEntity tipoMascota) {
        this.tipoSangre = tipoSangre;
        this.administradorCreador = administradorCreador;
        this.tipoMascotaEntity = tipoMascota;
    }

    @Id
    @GeneratedValue(generator = "secuenciatiposangre")
    @SequenceGenerator(name = "secuenciatiposangre", sequenceName = "autotiposangre", allocationSize = 1)
    @Column(name = "idtiposangre")
    private Integer idTipoSangre;

    @Column(name = "tiposangre", nullable = false, length = 10)
    private String tipoSangre;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoSangreMascota", fetch = FetchType.LAZY)
    private List<MascotaEntity> mascota;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idadministradorfk")
    private AdministradorEntity administradorCreador;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtipomascotafk")
    private TipoMascotaEntity tipoMascotaEntity;

    public TipoMascotaEntity getTipoMascotaEntity() {
        return tipoMascotaEntity;
    }

    public void setTipoMascotaEntity(TipoMascotaEntity tipoMascotaEntity) {
        this.tipoMascotaEntity = tipoMascotaEntity;
    }

    public Integer getIdTipoSangre() {
        return idTipoSangre;
    }

    public void setIdTipoSangre(Integer idTipoSangre) {
        this.idTipoSangre = idTipoSangre;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public AdministradorEntity getAdministradorCreador() {
        return administradorCreador;
    }

    public void setAdministradorCreador(AdministradorEntity administradorCreador) {
        this.administradorCreador = administradorCreador;
    }
}
