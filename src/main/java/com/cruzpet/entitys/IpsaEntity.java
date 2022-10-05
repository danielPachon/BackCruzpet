package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ipsas")
public class IpsaEntity {

    public IpsaEntity() {
    }

    public IpsaEntity(int rut, String nombre, String estado, String logoIpsa,  String passwordIpsa, DireccionEntity direccion, AdministradorEntity administrador) {
        this.rut = rut;
        this.nombre = nombre;
        this.estado = estado;
        this.logoIpsa = logoIpsa;
        this.passwordIpsa = passwordIpsa;
        this.direccionIpsa = direccion;
        this.administradorCreador = administrador;
    }

    public IpsaEntity(int rut, String nombre, String estado, String logoIpsa, String correoIpsa) {
        this.rut = rut;
        this.nombre = nombre;
        this.estado = estado;
        this.logoIpsa = logoIpsa;
        this.correoIpsa = correoIpsa;
    }

    @Id
    @Column(name = "rut")
    private int rut;

    @Column(name = "nombre", nullable = false, length = 50)
    private  String nombre;

    @Column(name = "estado", nullable = false, length = 1)
    private String estado;

    @Column(name = "logotipo", nullable = false, length = 150)
    private String logoIpsa;

    @Column(name = "correoIpsa", nullable = false, length = 50)
    private String correoIpsa;

    @Column(name = "passwordIpsa", nullable = false)
    private String passwordIpsa;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "iddireccionesfk")
    private DireccionEntity direccionIpsa;

    @JsonIgnore
    @OneToMany(mappedBy = "ipsaPropetaria", fetch = FetchType.LAZY)
    private List<CitaEntity> citas;

    @OneToMany(mappedBy = "ipsaTrabajo", fetch = FetchType.LAZY)
    private  List<VeterinarioEntity> veterinario;

    @JsonIgnore
    @OneToMany(mappedBy = "ipsaHistoriaClin")
    private  List<HistorialClinicaEntity> historiaClinica;

    @JsonIgnore
    @OneToMany(mappedBy = "ipsafk")
    private  List<DisponibilidadEntity> disponibilidadEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "administradorCreador")
    private AdministradorEntity administradorCreador;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ipsa_rol", joinColumns = @JoinColumn(name = "ipsa_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<RolEntity> roles = new HashSet<>();

    @JsonIgnore
    @OneToOne(mappedBy = "ipsaEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProductosEntity productosEntity;

    public Set<RolEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolEntity> roles) {
        this.roles = roles;
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<CitaEntity> getCitas() {
        return citas;
    }

    public void setCitas(List<CitaEntity> citas) {
        this.citas = citas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLogoIpsa() {
        return logoIpsa;
    }

    public void setLogoIpsa(String logoIpsa) {
        this.logoIpsa = logoIpsa;
    }

    public DireccionEntity getDireccionIpsa() {
        return direccionIpsa;
    }

    public void setDireccionIpsa(DireccionEntity direccionIpsa) {
        this.direccionIpsa = direccionIpsa;
    }

    public String getCorreoIpsa() {
        return correoIpsa;
    }

    public void setCorreoIpsa(String correoIpsa) {
        this.correoIpsa = correoIpsa;
    }

    public String getPasswordIpsa() {
        return passwordIpsa;
    }

    public void setPasswordIpsa(String passwordIpsa) {
        this.passwordIpsa = passwordIpsa;
    }

    public AdministradorEntity getAdministradorCreador() {
        return administradorCreador;
    }

    public void setAdministradorCreador(AdministradorEntity administradorCreador) {
        this.administradorCreador = administradorCreador;
    }
}
