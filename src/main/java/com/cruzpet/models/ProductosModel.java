package com.cruzpet.models;

import com.cruzpet.entitys.AdministradorEntity;
import com.cruzpet.entitys.CalificacionEntity;
import com.cruzpet.entitys.IpsaEntity;
import com.cruzpet.entitys.ProductosEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

public class ProductosModel {

    public ProductosModel() {
    }

    public ProductosModel(ProductosEntity productosEntity) {
        this.idProducto = productosEntity.getIdProducto();
        this.rutaImagen = productosEntity.getRutaImagen();
        this.nombreProducto = productosEntity.getNombreProducto();
        this.precioproducto = productosEntity.getPrecioproducto();
        this.totalCalificacion = productosEntity.getTotalCalificacion();
        this.calificacion = productosEntity.getCalificacion();
        this.administradorCreador = productosEntity.getAdministradorCreador();
        this.ipsaEntity = productosEntity.getIpsaEntity();
        this.estado = productosEntity.getEstado();
    }

    private Integer idProducto;

    private String rutaImagen;

    private String nombreProducto;

    private String precioproducto;

    private Double totalCalificacion;

    private List<CalificacionEntity> calificacion;

    private AdministradorEntity administradorCreador;

    private IpsaEntity ipsaEntity;

    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public IpsaEntity getIpsaEntity() {
        return ipsaEntity;
    }

    public void setIpsaEntity(IpsaEntity ipsaEntity) {
        this.ipsaEntity = ipsaEntity;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getPrecioproducto() {
        return precioproducto;
    }

    public void setPrecioproducto(String precioproducto) {
        this.precioproducto = precioproducto;
    }

    public Double getTotalCalificacion() {
        return totalCalificacion;
    }

    public void setTotalCalificacion(Double totalCalificacion) {
        this.totalCalificacion = totalCalificacion;
    }

    public List<CalificacionEntity> getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(List<CalificacionEntity> calificacion) {
        this.calificacion = calificacion;
    }

    public AdministradorEntity getAdministradorCreador() {
        return administradorCreador;
    }

    public void setAdministradorCreador(AdministradorEntity administradorCreador) {
        this.administradorCreador = administradorCreador;
    }
}
