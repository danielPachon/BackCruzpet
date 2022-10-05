package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "productos")
public class ProductosEntity {

    public ProductosEntity() {
    }

    public ProductosEntity(Integer idProducto, String rutaImagen, String nombreProducto, String precioproducto, Double totalCalificacion, List<CalificacionEntity> calificacion, AdministradorEntity administradorCreador, IpsaEntity ipsaEntity, String estado) {
        this.idProducto = idProducto;
        this.rutaImagen = rutaImagen;
        this.nombreProducto = nombreProducto;
        this.precioproducto = precioproducto;
        this.totalCalificacion = totalCalificacion;
        this.calificacion = calificacion;
        this.administradorCreador = administradorCreador;
        this.ipsaEntity = ipsaEntity;
        this.estado = estado;
    }

    public ProductosEntity(Integer idProducto, String rutaImagen, String nombreProducto, String precioproducto,  List<CalificacionEntity> calificacion, AdministradorEntity administradorCreador, IpsaEntity ipsaEntity, String estado) {
        this.idProducto = idProducto;
        this.rutaImagen = rutaImagen;
        this.nombreProducto = nombreProducto;
        this.precioproducto = precioproducto;
        this.calificacion = calificacion;
        this.administradorCreador = administradorCreador;
        this.ipsaEntity = ipsaEntity;
        this.estado = estado;
    }

    public ProductosEntity(Integer idProducto, String rutaImagen, String nombreProducto, String precioproducto,  List<CalificacionEntity> calificacion, AdministradorEntity administradorCreador, String estado) {
        this.idProducto = idProducto;
        this.rutaImagen = rutaImagen;
        this.nombreProducto = nombreProducto;
        this.precioproducto = precioproducto;
        this.calificacion = calificacion;
        this.administradorCreador = administradorCreador;
        this.estado = estado;
    }

    @Id
    @GeneratedValue(generator = "secuenciaproductos")
    @SequenceGenerator(name = "secuenciaproductos", sequenceName = "autoproductos", allocationSize = 1)
    @Column(name = "idproducto")
    private Integer idProducto;

    @Column(name = "rutaimagen", nullable = false)
    private String rutaImagen;

    @Column(name = "nombreproducto", nullable = false, length = 100)
    private String nombreProducto;

    @Column(name = "precioproducto", nullable = false)
    private String precioproducto;

    @Column(name = "totalcalificacion", nullable = false)
    private Double totalCalificacion = 0.0;

    @Column(name="estado", nullable = false)
    private String estado;

    @ManyToMany
    @JoinTable(
            name = "productos_calificaciones",
            joinColumns = @JoinColumn(name = "idcalificacionfk"),
            inverseJoinColumns = @JoinColumn(name = "idproductofk")
    )
    private List<CalificacionEntity> calificacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idadministradorfk")
    private AdministradorEntity administradorCreador;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idipsafk")
    private IpsaEntity ipsaEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "productos", fetch = FetchType.LAZY)
    private List<CantidadEntity> cantidades;

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

    public Double getTotalCalificacion() {
        return totalCalificacion;
    }

    public void setTotalCalificacion(Double totalCalificacion) {
        this.totalCalificacion = totalCalificacion;
    }
}
