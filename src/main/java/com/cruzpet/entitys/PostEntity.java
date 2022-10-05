package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
public class PostEntity {

    public PostEntity() {
    }

    public PostEntity(String tituloPost, Date fechaPost, String cuerpoPost, char tipoPost, ClienteEntity cedulaCliente, AdministradorEntity administradorCreador, String imagen, String estado) {
        this.tituloPost = tituloPost;
        this.fechaPost = fechaPost;
        this.cuerpoPost = cuerpoPost;
        this.tipoPost = tipoPost;
        this.cedulaCliente = cedulaCliente;
        this.administradorCreador = administradorCreador;
        this.imagen = imagen;
        this.estado = estado;
    }

    public PostEntity(Integer idPost, String tituloPost, Date fechaPost, String cuerpoPost, char tipoPost, ClienteEntity cedulaCliente, AdministradorEntity administradorCreador, String imagen, String estado) {
        this.idPost = idPost;
        this.tituloPost = tituloPost;
        this.fechaPost = fechaPost;
        this.cuerpoPost = cuerpoPost;
        this.tipoPost = tipoPost;
        this.cedulaCliente = cedulaCliente;
        this.administradorCreador = administradorCreador;
        this.imagen = imagen;
        this.estado = estado;
    }

    @Id
    @GeneratedValue(generator = "secuenciaposts")
    @SequenceGenerator(name = "secuenciaposts", sequenceName = "autoposts", allocationSize = 1)
    @Column(name = "idPost")
    private Integer idPost;

    @Column(name = "tituloPost", nullable = false, length = 1000)
    private String tituloPost;

    @Column(name = "fechaPost", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaPost;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "cuerpoPost", nullable = false, length = 10000)
    private String cuerpoPost;

    @Column(name = "tipoPost", nullable = false, length = 1)
    private char tipoPost;

    @Column(name = "estado", length = 1)
    private String estado;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idcedulaclientefk")
    private ClienteEntity cedulaCliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idadministradorfk")
    private AdministradorEntity administradorCreador;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public String getTituloPost() {
        return tituloPost;
    }

    public void setTituloPost(String tituloPost) {
        this.tituloPost = tituloPost;
    }

    public Date getFechaPost() {
        return fechaPost;
    }

    public void setFechaPost(Date fechaPost) {
        this.fechaPost = fechaPost;
    }

    public String getCuerpoPost() {
        return cuerpoPost;
    }

    public void setCuerpoPost(String cuerpoPost) {
        this.cuerpoPost = cuerpoPost;
    }

    public char getTipoPost() {
        return tipoPost;
    }

    public void setTipoPost(char tipoPost) {
        this.tipoPost = tipoPost;
    }

    public ClienteEntity getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(ClienteEntity cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public AdministradorEntity getAdministradorCreador() {
        return administradorCreador;
    }

    public void setAdministradorCreador(AdministradorEntity administradorCreador) {
        this.administradorCreador = administradorCreador;
    }
}
