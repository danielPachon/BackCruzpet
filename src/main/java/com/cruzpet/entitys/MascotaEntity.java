package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "mascotas")
public class MascotaEntity {

    public MascotaEntity( Integer edad, String nombreMascota, Date fechaNacimiento, RazaEntity razaMascota, ClienteEntity clienteMascota, TipoMascotaEntity tipoMascota, GeneroEntity generoMascota, TipoSangreEntity tipoSangreMascota, String estado, String imagenMascota, AdministradorEntity administradorCreador) {
        this.edad = edad;
        this.nombreMascota = nombreMascota;
        this.fechaNacimiento = fechaNacimiento;
        this.razaMascota = razaMascota;
        this.clienteMascota = clienteMascota;
        this.tipoMascota = tipoMascota;
        this.generoMascota = generoMascota;
        this.tipoSangreMascota = tipoSangreMascota;
        this.estado = estado;
        this.imagenMascota = imagenMascota;
        this.administradorCreador = administradorCreador;
    }

    public MascotaEntity() {
    }

    @Id
    @Column(name = "numeroidentidad", length = 10)
    private String numeroIdentidad;

    @Column(name = "edad", nullable = false, length = 2)
    private Integer edad;

    @Column(name = "nombremascota", length = 30, nullable = false)
    private  String nombreMascota;

    @Column(name = "fechanacimiento", nullable = false)
    private Date fechaNacimiento;

    @Column(name = "estado", nullable = false, length = 1)
    private String estado;

    @Column(name = "imagenmascota", nullable = false, length = 150)
    private String imagenMascota;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idrazafk")
    private RazaEntity razaMascota;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idclientefk")
    private ClienteEntity clienteMascota;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idtipomascotafk")
    private TipoMascotaEntity tipoMascota;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idgenerofk")
    private GeneroEntity generoMascota;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idtiposangrefk")
    private TipoSangreEntity tipoSangreMascota;

    @JsonIgnore
    @OneToMany(mappedBy = "citaMascota", fetch = FetchType.EAGER)
    private List<CitaEntity> cita;

    @JsonIgnore
    @OneToMany(mappedBy = "mascotaEntity", fetch = FetchType.LAZY)
    private List<FormulaEntity> formulaEntities;

    @OneToMany(mappedBy = "historiaClinMascota")
    private List<HistorialClinicaEntity> historiaClinica;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idadministradorfk")
    private AdministradorEntity administradorCreador;

    @JsonIgnore
    @OneToOne(mappedBy = "mascota", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CarnetVacunacionEntity carnetVacunacionEntity;


    public String getNumeroIdentidad() {
        return numeroIdentidad;
    }

    public void setNumeroIdentidad(String numeroIdentidad) {
        this.numeroIdentidad = numeroIdentidad;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public RazaEntity getRazaMascota() {
        return razaMascota;
    }

    public void setRazaMascota(RazaEntity razaMascota) {
        this.razaMascota = razaMascota;
    }

    public ClienteEntity getClienteMascota() {
        return clienteMascota;
    }

    public void setClienteMascota(ClienteEntity clienteMascota) {
        this.clienteMascota = clienteMascota;
    }

    public TipoMascotaEntity getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(TipoMascotaEntity tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public GeneroEntity getGeneroMascota() {
        return generoMascota;
    }

    public void setGeneroMascota(GeneroEntity generoMascota) {
        this.generoMascota = generoMascota;
    }

    public TipoSangreEntity getTipoSangreMascota() {
        return tipoSangreMascota;
    }

    public void setTipoSangreMascota(TipoSangreEntity tipoSangreMascota) {
        this.tipoSangreMascota = tipoSangreMascota;
    }

    public List<CitaEntity> getCita() {
        return cita;
    }

    public void setCita(List<CitaEntity> cita) {
        this.cita = cita;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getImagenMascota() {
        return imagenMascota;
    }

    public void setImagenMascota(String imagenMascota) {
        this.imagenMascota = imagenMascota;
    }

    public AdministradorEntity getAdministradorCreador() {
        return administradorCreador;
    }

    public void setAdministradorCreador(AdministradorEntity administradorCreador) {
        this.administradorCreador = administradorCreador;
    }
}
