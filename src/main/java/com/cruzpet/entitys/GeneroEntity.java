package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "generos")
public class GeneroEntity {

    public GeneroEntity() {
    }

    public GeneroEntity(Integer idGenero, String genero, AdministradorEntity administradorCreador) {
        this.idGenero = idGenero;
        this.genero = genero;
        this.administradorCreador = administradorCreador;
    }

    public GeneroEntity(Integer idGenero, String genero) {
        this.idGenero = idGenero;
        this.genero = genero;
    }

    @Id
    @GeneratedValue(generator = "secuenciageneros")
    @SequenceGenerator(name = "secuenciageneros", sequenceName = "autogeneros", allocationSize = 1)
    @Column(name = "idgenero")
    private Integer idGenero;

    @Column(name = "genero", nullable = false, length = 2)
    private String genero;

    @JsonIgnore
    @OneToMany(mappedBy = "generoMascota", fetch = FetchType.LAZY)
    private List<MascotaEntity> mascota;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idadministradorfk")
    private AdministradorEntity administradorCreador;

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

    public AdministradorEntity getAdministradorCreador() {
        return administradorCreador;
    }

    public void setAdministradorCreador(AdministradorEntity administradorCreador) {
        this.administradorCreador = administradorCreador;
    }
}
