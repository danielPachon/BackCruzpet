package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "veterinarios")
public class VeterinarioEntity {

    public VeterinarioEntity() {
    }

    public VeterinarioEntity(String cedVeterinario, String nombres, String apellidos, Date fechaNacimiento, String celular, String foto, String passwordVeterinario, IpsaEntity ipsaTrabajo, EspecialidadEntity especialidadVeterinario, DireccionEntity direccion, AdministradorEntity administradorCreador, List<DisponibilidadEntity> disponibilidades, String estado) {
        this.cedVeterinario = cedVeterinario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.celular = celular;
        this.foto = foto;
        this.passwordVeterinario = passwordVeterinario;
        this.ipsaTrabajo = ipsaTrabajo;
        this.especialidadVeterinario = especialidadVeterinario;
        this.direccion = direccion;
        this.administradorCreador = administradorCreador;
        this.estado = estado;
    }

    public VeterinarioEntity(String cedVeterinario, String nombres, String apellidos, String celular, String foto,  EspecialidadEntity especialidadVeterinario, String estado) {
        this.cedVeterinario = cedVeterinario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.foto = foto;
        this.especialidadVeterinario = especialidadVeterinario;
        this.estado = estado;
    }


    @Id
    @Column(name = "cedveterinario")
    private String cedVeterinario;

    @Column(name = "nombres", nullable = false, length = 50)
    private  String  nombres;

    @Column(name = "apellidos", nullable = false, length = 50)
    private String apellidos;

    @Column(name = "fechanacimiento", nullable = false)
    private Date fechaNacimiento;

    @Column(name = "celular", nullable = false, length = 15)
    private String celular;

    @Column(name = "fotoVeterinario", nullable = false, length = 150)
    private String foto;

    @Column(name = "correoVeterinario", nullable = false, length = 50)
    private String correoVeterinario;

    @Column(name = "passwordVeterinario", nullable = false)
    private String passwordVeterinario;


    @Column(name = "estado")
    private String estado;
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "veterinario_rol", joinColumns = @JoinColumn(name = "veterinario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<RolEntity> roles = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rutfk")
    private IpsaEntity ipsaTrabajo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idespecialidadfk")
    private EspecialidadEntity especialidadVeterinario;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "iddireccionfk")
    private DireccionEntity direccion;

    @JsonIgnore
    @OneToMany(mappedBy = "citaVeterinario", fetch = FetchType.LAZY)
    private List<CitaEntity> citaEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "historiaClinVeterinario", fetch = FetchType.LAZY)
    private List<HistorialClinicaEntity> historiaClinica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idadministradorfk")
    private AdministradorEntity administradorCreador;

    @OneToMany(mappedBy = "veterinarioEntity", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonManagedReference
    private List<VeterinarioDisponibilidadesEntity> veterinarioDisponibilidadeEntities;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<VeterinarioDisponibilidadesEntity> getVeterinarioDisponibilidadeEntities() {
        return veterinarioDisponibilidadeEntities;
    }

    public void setVeterinarioDisponibilidadeEntities(List<VeterinarioDisponibilidadesEntity> veterinarioDisponibilidadeEntities) {
        this.veterinarioDisponibilidadeEntities = veterinarioDisponibilidadeEntities;
    }

    public Set<RolEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolEntity> roles) {
        this.roles = roles;
    }

    public String getCedVeterinario() {
        return cedVeterinario;
    }

    public void setCedVeterinario(String cedVeterinario) {
        this.cedVeterinario = cedVeterinario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public DireccionEntity getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionEntity direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public IpsaEntity getIpsaTrabajo() {
        return ipsaTrabajo;
    }

    public void setIpsaTrabajo(IpsaEntity ipsaTrabajo) {
        this.ipsaTrabajo = ipsaTrabajo;
    }

    public EspecialidadEntity getEspecialidadVeterinario() {
        return especialidadVeterinario;
    }

    public void setEspecialidadVeterinario(EspecialidadEntity especialidadVeterinario) {
        this.especialidadVeterinario = especialidadVeterinario;
    }

    public List<CitaEntity> getCitaEntity() {
        return citaEntity;
    }

    public void setCitaEntity(List<CitaEntity> citaEntity) {
        this.citaEntity = citaEntity;
    }

    public List<HistorialClinicaEntity> getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(List<HistorialClinicaEntity> historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    public String getCorreoVeterinario() {
        return correoVeterinario;
    }

    public void setCorreoVeterinario(String correoVeterinario) {
        this.correoVeterinario = correoVeterinario;
    }

    public String getPasswordVeterinario() {
        return passwordVeterinario;
    }

    public void setPasswordVeterinario(String passwordVeterinario) {
        this.passwordVeterinario = passwordVeterinario;
    }

    public AdministradorEntity getAdministradorCreador() {
        return administradorCreador;
    }

    public void setAdministradorCreador(AdministradorEntity administradorCreador) {
        this.administradorCreador = administradorCreador;
    }
}

