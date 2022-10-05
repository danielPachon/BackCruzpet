package com.cruzpet.entitys;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "cantidades")
public class CantidadEntity {

    public CantidadEntity() {
    }

    public CantidadEntity( ComprasEntity carritoCompras, ProductosEntity productos) {
        this.carritoCompras = carritoCompras;
        this.productos = productos;
    }

    @Id
    @GeneratedValue(generator = "secuenciacantidades")
    @SequenceGenerator(name = "secuenciacantidades", sequenceName = "autocantidades", allocationSize = 1)
    @Column(name = "idcantidad")
    private Integer idCantidad;

    @Column(name = "cantidad")
    private Integer cantidad = 1;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idcarritocomprasfk")
    private ComprasEntity carritoCompras;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idproductofk")
    private ProductosEntity productos;

    public ProductosEntity getProductos() {
        return productos;
    }

    public void setProductos(ProductosEntity productos) {
        this.productos = productos;
    }

    public ComprasEntity getCarritoCompras() {
        return carritoCompras;
    }

    public void setCarritoCompras(ComprasEntity carritoCompras) {
        this.carritoCompras = carritoCompras;
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
