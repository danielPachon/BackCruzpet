package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "compras")
public class ComprasEntity {

    public ComprasEntity() {
    }

    public ComprasEntity(List<ProductosEntity> comprasProductos) {
        this.comprasProductos = comprasProductos;
    }

    public ComprasEntity(List<ProductosEntity> comprasProductos, Integer cantidades) {
        this.comprasProductos = comprasProductos;
        this.cantidadCompras = cantidades;
    }

    @Id
    @GeneratedValue(generator = "secuenciacompras")
    @SequenceGenerator(name = "secuenciacompras", sequenceName = "autocompras", allocationSize = 1)
    @Column(name = "idcompra")
    private Integer idCompra;

    private Integer cantidadCompras;

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "compras_productos", joinColumns = @JoinColumn(name = "compras_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private List<ProductosEntity> comprasProductos = new ArrayList<>();


    @JsonIgnore
    @OneToOne(mappedBy = "compras", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ClienteEntity clienteEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "carritoCompras", fetch = FetchType.LAZY)
    private List<CantidadEntity> cantidades;

    public Integer getCantidadCompras() {
        return cantidadCompras;
    }

    public void setCantidadCompras(Integer cantidadCompras) {
        this.cantidadCompras = cantidadCompras;
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
