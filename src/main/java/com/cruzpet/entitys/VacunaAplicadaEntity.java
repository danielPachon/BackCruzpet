package com.cruzpet.entitys;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class VacunaAplicadaEntity {

    public VacunaAplicadaEntity() {
    }

    public VacunaAplicadaEntity(Date fecha, Date fechaRepeticion, VacunaEntity vacunasEntity, String estado) {
        this.fecha = fecha;
        this.fechaRepeticion = fechaRepeticion;
        this.vacunasEntity = vacunasEntity;
        this.estado = estado;
    }

    @Id
    @GeneratedValue(generator = "secuenciavacunaaplicada")
    @SequenceGenerator(name = "secuenciavacunaaplicada", sequenceName = "autovacunaaplicada", allocationSize = 1)
    @Column(name = "vacunaaplicada")
    private Integer idVacunaAplicada;

    @Column(name = "fechaaplicacion", nullable = false)
    private Date fecha;

    @Column(name = "fecharepeticion", nullable = false)
    private  Date fechaRepeticion;

    @Column(name="estado")
    private String estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idvacunafk")
    private VacunaEntity vacunasEntity;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdVacunaAplicada() {
        return idVacunaAplicada;
    }

    public void setIdVacunaAplicada(Integer idVacunaAplicada) {
        this.idVacunaAplicada = idVacunaAplicada;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaRepeticion() {
        return fechaRepeticion;
    }

    public void setFechaRepeticion(Date fechaRepeticion) {
        this.fechaRepeticion = fechaRepeticion;
    }

    public VacunaEntity getVacunasEntity() {
        return vacunasEntity;
    }

    public void setVacunasEntity(VacunaEntity vacunasEntity) {
        this.vacunasEntity = vacunasEntity;
    }
}
