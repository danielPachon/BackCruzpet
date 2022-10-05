package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "especialidades")
public class EspecialidadEntity {

    public EspecialidadEntity() {
    }

    public EspecialidadEntity(Integer idEspecialidad, String nombreEspecialidad, AdministradorEntity administrador) {
        this.idEspecialidad = idEspecialidad;
        this.nombreEspecialidad = nombreEspecialidad;
        this.administradorCreador = administrador;
    }

    @Id
    @GeneratedValue(generator = "secuenciaespecialidades")
    @SequenceGenerator(name = "secuenciaespecialidades",sequenceName = "autoespecialidades", allocationSize = 1)
    @Column(name = "idespecialidad")
    private Integer idEspecialidad;

    @Column(name = "nombreespecialidad", nullable = false, length = 50)
    private String nombreEspecialidad;

    @JsonIgnore
    @OneToMany(mappedBy = "especialidadVeterinario", fetch = FetchType.EAGER)
    private List<VeterinarioEntity> veterinario;

    @JsonIgnore
    @OneToMany(mappedBy = "especialidadVeterinario")
    private Set<VeterinarioEntity> veterinarios = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idadministradorfk")
    private AdministradorEntity administradorCreador;

    public Set<VeterinarioEntity> getVeterinarios() {
        return veterinarios;
    }

    public void setVeterinarios(Set<VeterinarioEntity> veterinarios) {
        this.veterinarios = veterinarios;
    }

    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public List<VeterinarioEntity> getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(List<VeterinarioEntity> veterinario) {
        this.veterinario = veterinario;
    }

    public AdministradorEntity getAdministradorCreador() {
        return administradorCreador;
    }

    public void setAdministradorCreador(AdministradorEntity administradorCreador) {
        this.administradorCreador = administradorCreador;
    }
}
