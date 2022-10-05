package com.cruzpet.models;

import com.cruzpet.entitys.CantidadEntity;
import com.cruzpet.entitys.ClienteEntity;
import com.cruzpet.entitys.ComprasEntity;
import com.cruzpet.entitys.ProductosEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ComprasModel {

    public ComprasModel(ComprasEntity comprasEntity) {
        this.idCompra = comprasEntity.getIdCompra();
        this.comprasProductos = comprasEntity.getComprasProductos();
        this.cantidades = comprasEntity.getCantidades();
    }

    private Integer idCompra;

    private List<ProductosEntity> comprasProductos = new ArrayList<>();

    private ClienteEntity clienteEntity;

    private List<CantidadEntity> cantidades;

    public ClienteEntity getClienteEntity() {
        return clienteEntity;
    }

    public void setClienteEntity(ClienteEntity clienteEntity) {
        this.clienteEntity = clienteEntity;
    }

    public List<CantidadEntity> getCantidades() {
        return cantidades;
    }

    public void setCantidades(List<CantidadEntity> cantidades) {
        this.cantidades = cantidades;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public List<ProductosEntity> getComprasProductos() {
        return comprasProductos;
    }

    public void setComprasProductos(List<ProductosEntity> comprasProductos) {
        this.comprasProductos = comprasProductos;
    }
}
