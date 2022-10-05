package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "planes")
public class PlanesEntity {

    public PlanesEntity() {
    }

    public PlanesEntity(Integer idPlan, double precio, String contenidoplan, String tituloPlan, List<BeneficiosEntity> beneficiosEntity, AdministradorEntity administradorCreador) {
        this.idPlan = idPlan;
        this.precio = precio;
        this.contenidoplan = contenidoplan;
        this.tituloPlan = tituloPlan;
        this.beneficiosEntity = beneficiosEntity;
        this.administradorCreador = administradorCreador;
    }

    @Id
    @GeneratedValue(generator = "secuenciaplanes")
    @SequenceGenerator(name = "secuenciaplanes", sequenceName = "autoplanes", allocationSize = 1)
    @Column(name = "idplan")
    private Integer idPlan;

    @Column(name = "precio", nullable = false)
    private double precio;

    @Column(name = "contenidoplan", nullable = false, length = 100)
    private String contenidoplan;

    @Column(name = "nombreplan", nullable = false, length = 50)
    private String tituloPlan;

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "planes_beneficios", joinColumns = @JoinColumn(name = "plan_id"), inverseJoinColumns = @JoinColumn(name = "beneficio_id"))
    private List<BeneficiosEntity> beneficiosEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idadministradorfk")
    private AdministradorEntity administradorCreador;

    @JsonIgnore
    @OneToOne(mappedBy = "planes", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ClienteEntity clienteEntity;

    public Integer getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getContenidoplan() {
        return contenidoplan;
    }

    public void setContenidoplan(String contenidoplan) {
        this.contenidoplan = contenidoplan;
    }

    public String getTituloPlan() {
        return tituloPlan;
    }

    public void setTituloPlan(String tituloPlan) {
        this.tituloPlan = tituloPlan;
    }

    public List<BeneficiosEntity> getBeneficiosEntity() {
        return beneficiosEntity;
    }

    public void setBeneficiosEntity(List<BeneficiosEntity> beneficiosEntity) {
        this.beneficiosEntity = beneficiosEntity;
    }

    public AdministradorEntity getAdministradorCreador() {
        return administradorCreador;
    }

    public void setAdministradorCreador(AdministradorEntity administradorCreador) {
        this.administradorCreador = administradorCreador;
    }
}
