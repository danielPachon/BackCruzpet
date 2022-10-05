package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Administradores")
public class AdministradorEntity {

    public AdministradorEntity() {
    }

    public AdministradorEntity(Integer idAdministrador, String nombreAdministrador, String correAdministrador, String passwordAdministrador, Set<RolEntity> roles, String estado) {
        this.idAdministrador = idAdministrador;
        this.nombreAdministrador = nombreAdministrador;
        this.correAdministrador = correAdministrador;
        this.passwordAdministrador = passwordAdministrador;
        this.roles = roles;
        this.estado = estado;
    }

    public AdministradorEntity(Integer idAdministrador, String nombreAdministrador, String correAdministrador,String estado) {
        this.idAdministrador = idAdministrador;
        this.nombreAdministrador = nombreAdministrador;
        this.correAdministrador = correAdministrador;
        this.estado = estado;
    }

    @Id
    @GeneratedValue(generator = "secuneciaAdministradores")
    @SequenceGenerator(name = "secuneciaAdministradores", sequenceName = "autoadministrador", allocationSize = 1)
    @Column(name = "idAdministrador")
    private Integer idAdministrador;

    @Column(name = "nombreadministrador", nullable = false, length = 50)
    private String nombreAdministrador;

    @Column(name = "correoAdministrador", nullable = false, length = 50)
    private String correAdministrador;

    @Column(name = "passwordAdministrador", nullable = false)
    private String passwordAdministrador;

    @Column(name = "estado", nullable = false)
    private String estado;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "administrador_rol", joinColumns = @JoinColumn(name = "administrador_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<RolEntity> roles = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "administradorCreador", fetch = FetchType.LAZY)
    private List<BarrioEntity> barrio;

    @JsonIgnore
    @OneToMany(mappedBy = "administradorCreador", fetch = FetchType.LAZY)
    private List<CiudadEntity> ciudad;

    @JsonIgnore
    @OneToMany(mappedBy = "administradorCreador", fetch = FetchType.LAZY)
    private List<ClienteEntity> cliente;

    @JsonIgnore
    @OneToMany(mappedBy = "administradorCreador", fetch = FetchType.LAZY)
    private List<DepartamentoEntity> departamento;

    @JsonIgnore
    @OneToMany(mappedBy = "administradorCreador", fetch = FetchType.LAZY)
    private List<PlanesEntity> planes;

    @JsonIgnore
    @OneToMany(mappedBy = "administradorCreador", fetch = FetchType.LAZY)
    private List<DireccionEntity> direcciones;

    @JsonIgnore
    @OneToMany(mappedBy = "administradorCreador", fetch = FetchType.LAZY)
    private List<EspecialidadEntity> especialidades;

    @JsonIgnore
    @OneToMany(mappedBy = "administradorCreador", fetch = FetchType.LAZY)
    private List<GeneroEntity> genero;

    @JsonIgnore
    @OneToMany(mappedBy = "administradorCreador", fetch = FetchType.LAZY)
    private List<IpsaEntity> ipsa;

    @JsonIgnore
    @OneToMany(mappedBy = "administradorCreador", fetch = FetchType.LAZY)
    private List<MascotaEntity> mascota;

    @JsonIgnore
    @OneToMany(mappedBy = "administradorCreador", fetch = FetchType.LAZY)
    private List<BeneficiosEntity> beneficio;

    @JsonIgnore
    @OneToMany(mappedBy = "administradorCreador", fetch = FetchType.LAZY)
    private List<MedicamentoEntity> medicamento;

    @JsonIgnore
    @OneToMany(mappedBy = "administradorCreador", fetch = FetchType.LAZY)
    private List<PostEntity> post;

    @JsonIgnore
    @OneToMany(mappedBy = "administradorCreador", fetch = FetchType.LAZY)
    private List<RazaEntity> raza;

    @JsonIgnore
    @OneToMany(mappedBy = "administradorCreador", fetch = FetchType.LAZY)
    private List<TipoMascotaEntity> tipoMascota;

    @JsonIgnore
    @OneToMany(mappedBy = "administradorCreador", fetch = FetchType.LAZY)
    private List<TipoSangreEntity> tipoSangre;

    @JsonIgnore
    @OneToMany(mappedBy = "administradorCreador", fetch = FetchType.LAZY)
    private List<VacunaDetalleEntity> vacunaDetalle;

    @JsonIgnore
    @OneToMany(mappedBy = "administradorCreador", fetch = FetchType.LAZY)
    private List<VacunaEntity> vacuna;

    @JsonIgnore
    @OneToMany(mappedBy = "administradorCreador", fetch = FetchType.LAZY)
    private List<VeterinarioEntity> veterinario;

    @JsonIgnore
    @OneToMany(mappedBy = "administrador", fetch = FetchType.LAZY)
    private List<ServiciosEntity> servicios;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Set<RolEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolEntity> roles) {
        this.roles = roles;
    }

    public Integer getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Integer idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

    public void setNombreAdministrador(String nombreAdministrador) {
        this.nombreAdministrador = nombreAdministrador;
    }

    public String getCorreAdministrador() {
        return correAdministrador;
    }

    public void setCorreAdministrador(String correAdministrador) {
        this.correAdministrador = correAdministrador;
    }

    public String getPasswordAdministrador() {
        return passwordAdministrador;
    }

    public void setPasswordAdministrador(String passwordAdministrador) {
        this.passwordAdministrador = passwordAdministrador;
    }
}
