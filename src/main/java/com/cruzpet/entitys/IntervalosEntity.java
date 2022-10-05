package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "intervalos")
public class IntervalosEntity {

    public IntervalosEntity() {
    }

    public IntervalosEntity(LocalTime tiempoIntervalo, String estadoIntervalo, DisponibilidadEntity disponibilidadEntity) {
        this.tiempoIntervalo = tiempoIntervalo;
        this.estadoIntervalo = estadoIntervalo;
        this.disponibilidadfk = disponibilidadEntity;
    }

    @Id
    @GeneratedValue(generator = "secuenciaintervalos")
    @SequenceGenerator(name = "secuenciaintervalos", sequenceName = "autointervalos", allocationSize = 1)
    private Integer idIntervalo;

    @Column(name = "tiempointervalo")
    private LocalTime tiempoIntervalo;

    @Column(name = "estadointervalo", length = 2)
    private String estadoIntervalo;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddisponibilidadfk")
    private DisponibilidadEntity disponibilidadfk;

    public DisponibilidadEntity getDisponibilidadfk() {
        return disponibilidadfk;
    }

    public void setDisponibilidadfk(DisponibilidadEntity disponibilidadfk) {
        this.disponibilidadfk = disponibilidadfk;
    }

    public Integer getIdIntervalo() {
        return idIntervalo;
    }

    public void setIdIntervalo(Integer idIntervalo) {
        this.idIntervalo = idIntervalo;
    }

    public LocalTime getTiempoIntervalo() {
        return tiempoIntervalo;
    }

    public void setTiempoIntervalo(LocalTime tiempoIntervalo) {
        this.tiempoIntervalo = tiempoIntervalo;
    }

    public String getEstadoIntervalo() {
        return estadoIntervalo;
    }

    public void setEstadoIntervalo(String estadoIntervalo) {
        this.estadoIntervalo = estadoIntervalo;
    }
}
