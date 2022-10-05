package com.cruzpet.models;

import com.cruzpet.entitys.CantidadEntity;
import com.cruzpet.entitys.ProductosEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class CanitdadModel {

    public CanitdadModel(CantidadEntity cantidad) {
        this.idCantidad = cantidad.getIdCantidad();
        this.cantidad = cantidad.getCantidad();
        this.productos = cantidad.getProductos();
    }


    private Integer idCantidad;

    private Integer cantidad;

    private ProductosEntity productos;

    public ProductosEntity getProductos() {
        return productos;
    }

    public void setProductos(ProductosEntity productos) {
        this.productos = productos;
    }

    public Integer getIdCantidad() {
        return idCantidad;
    }

    public void setIdCantidad(Integer idCantidad) {
        this.idCantidad = idCantidad;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
