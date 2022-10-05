package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "disponibilidades")
public class DisponibilidadEntity {

    public DisponibilidadEntity() {
    }

    public DisponibilidadEntity(Integer idDisponibilidad, String diaDisponibilidad, String mesDisponibilidad, String yearDisponibilidad, LocalTime horaEntrada, LocalTime horaSalida, String estado,IpsaEntity ipsafk, List<IntervalosEntity> intervalos) {
        this.idDisponibilidad = idDisponibilidad;
        this.diaDisponibilidad = diaDisponibilidad;
        this.mesDisponibilidad = mesDisponibilidad;
        this.yearDisponibilidad = yearDisponibilidad;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.estado = estado;
        this.ipsafk = ipsafk;
        this.intervalos = intervalos;
    }

    public DisponibilidadEntity(Integer idDisponibilidad, String diaDisponibilidad, String mesDisponibilidad, String yearDisponibilidad, LocalTime horaEntrada, LocalTime horaSalida, IpsaEntity ipsaNueva) {
        this.idDisponibilidad = idDisponibilidad;
        this.diaDisponibilidad = diaDisponibilidad;
        this.mesDisponibilidad = mesDisponibilidad;
        this.yearDisponibilidad = yearDisponibilidad;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.ipsafk = ipsaNueva;
    }

    public DisponibilidadEntity(Integer idDisponibilidad, String diaDisponibilidad, String mesDisponibilidad, String yearDisponibilidad, LocalTime horaEntrada, LocalTime horaSalida) {
        this.idDisponibilidad = idDisponibilidad;
        this.diaDisponibilidad = diaDisponibilidad;
        this.mesDisponibilidad = mesDisponibilidad;
        this.yearDisponibilidad = yearDisponibilidad;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }

    public DisponibilidadEntity(Integer idDisponibilidad, String diaDisponibilidad, String mesDisponibilidad, String yearDisponibilidad, LocalTime horaEntrada,  List<IntervalosEntity> intervalosEntity, LocalTime horaSalida) {
        this.idDisponibilidad = idDisponibilidad;
        this.diaDisponibilidad = diaDisponibilidad;
        this.mesDisponibilidad = mesDisponibilidad;
        this.yearDisponibilidad = yearDisponibilidad;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.intervalos = intervalosEntity;
    }

    @Id
    @GeneratedValue(generator = "secuenciadisponibilidades")
    @SequenceGenerator(name = "secuenciadisponibilidades", sequenceName = "autodisponibilidades", allocationSize = 1)
    @Column(name = "iddisponibilidad")
    private Integer idDisponibilidad;

    @Column(name = "diadisponobilidad", length = 2)
    private String diaDisponibilidad;

    @Column(name = "mesdisponibilidad", length = 2)
    private String mesDisponibilidad;

    @Column(name = "yeardisponibilidad", length = 4)
    private String yearDisponibilidad;

    @Column(name = "horaEntrada")
    private LocalTime horaEntrada;

    @Column(name = "horaSalida")
    private LocalTime horaSalida;

    @Column(name = "estado")
    private String estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idipsafk")
    private IpsaEntity ipsafk;

    @OneToMany(mappedBy = "disponibilidadfk")
    private List<IntervalosEntity> intervalos;

    @OneToMany(mappedBy = "disponibilidadEntity", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonManagedReference(value="user-person")
    private List<VeterinarioDisponibilidadesEntity> veterinarioDisponibilidadeEntities;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<VeterinarioDisponibilidadesEntity> getVeterinarioDisponibilidades() {
        return veterinarioDisponibilidadeEntities;
    }

    public void setVeterinarioDisponibilidades(List<VeterinarioDisponibilidadesEntity> veterinarioDisponibilidadeEntities) {
        this.veterinarioDisponibilidadeEntities = veterinarioDisponibilidadeEntities;
    }

    public String getDiaDisponibilidad() {
        return diaDisponibilidad;
    }

    public void setDiaDisponibilidad(String diaDisponibilidad) {
        this.diaDisponibilidad = diaDisponibilidad;
    }

    public String getMesDisponibilidad() {
        return mesDisponibilidad;
    }

    public void setMesDisponibilidad(String mesDisponibilidad) {
        this.mesDisponibilidad = mesDisponibilidad;
    }

    public String getYearDisponibilidad() {
        return yearDisponibilidad;
    }

    public void setYearDisponibilidad(String yearDisponibilidad) {
        this.yearDisponibilidad = yearDisponibilidad;
    }

    public Integer getIdDisponibilidad() {
        return idDisponibilidad;
    }

    public void setIdDisponibilidad(Integer idDisponibilidad) {
        this.idDisponibilidad = idDisponibilidad;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public IpsaEntity getIpsafk() {
        return ipsafk;
    }

    public void setIpsafk(IpsaEntity ipsafk) {
        this.ipsafk = ipsafk;
    }

    public List<IntervalosEntity> getIntervalos() {
        return intervalos;
    }

    public void setIntervalos(List<IntervalosEntity> intervalos) {
        this.intervalos = intervalos;
    }
}
