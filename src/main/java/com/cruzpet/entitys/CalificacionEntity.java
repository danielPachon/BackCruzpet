package com.cruzpet.entitys;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "calificaciones")
public class CalificacionEntity {

    public CalificacionEntity() {
    }

    public CalificacionEntity(Integer idCalificacion, Integer numeroCalificacion) {
        this.idCalificacion = idCalificacion;
        this.numeroCalificacion = numeroCalificacion;
    }

    @Id
    @Column(name = "idcalificacion")
    private Integer idCalificacion;

    @Column(name = "numeroCalificacion")
    private Integer numeroCalificacion;

    @ManyToMany(mappedBy = "calificacion")
    private List<ProductosEntity> productosEntity;

    public Integer getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(Integer idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public Integer getNumeroCalificacion() {
        return numeroCalificacion;
    }

    public void setNumeroCalificacion(Integer numeroCalificacion) {
        this.numeroCalificacion = numeroCalificacion;
    }
}
