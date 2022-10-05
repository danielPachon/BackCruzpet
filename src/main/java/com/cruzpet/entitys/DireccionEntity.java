package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "direcciones")
public class DireccionEntity {

    public DireccionEntity() {
    }

    public DireccionEntity(Integer idDireccion, String direccionCasa, BarrioEntity barrios, AdministradorEntity administradorEntity) {
        this.idDireccion = idDireccion;
        this.direccionCasa = direccionCasa;
        this.barrios = barrios;
        this.administradorCreador = administradorEntity;
    }

    @Id
    @GeneratedValue(generator = "secuenciadirecciones")
    @SequenceGenerator(name = "secuenciadirecciones", sequenceName = "autodirecciones", allocationSize = 1)
    @Column(name = "iddireccion")
    private Integer idDireccion;

    @Column(name = "direccioncasa", nullable = false, length = 100)
    private String direccionCasa;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idbarriofk")
    private BarrioEntity barrios;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idadministradorfk")
    private AdministradorEntity administradorCreador;

    @JsonIgnore
    @OneToOne(mappedBy = "direccionIpsa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private IpsaEntity ipsaEntity;

    @JsonIgnore
    @OneToOne(mappedBy = "direccion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ClienteEntity clienteEntity;

    public Integer getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getDireccionCasa() {
        return direccionCasa;
    }

    public void setDireccionCasa(String direccionCasa) {
        this.direccionCasa = direccionCasa;
    }

    public BarrioEntity getBarrios() {
        return barrios;
    }

    public void setBarrios(BarrioEntity barrios) {
        this.barrios = barrios;
    }

    public AdministradorEntity getAdministradorCreador() {
        return administradorCreador;
    }

    public void setAdministradorCreador(AdministradorEntity administradorCreador) {
        this.administradorCreador = administradorCreador;
    }
}
