package com.cruzpet.models;

import com.cruzpet.entitys.AdministradorEntity;
import com.cruzpet.entitys.PostEntity;
import com.cruzpet.entitys.ClienteEntity;

import java.util.Date;

public class PostModel {

    public PostModel() {
    }

    public PostModel(PostEntity post) {
        this.idPost = post.getIdPost();
        this.tituloPost = post.getTituloPost();
        this.fechaPost = post.getFechaPost();
        this.cuerpoPost = post.getCuerpoPost();
        this.tipoPost = post.getTipoPost();
        this.cedulaUsuario = post.getCedulaCliente();
        this.administaradorCreador = post.getAdministradorCreador();
        this.imagen = post.getImagen();
        this.estado = post.getEstado();
    }

    public PostModel(Integer post, String tituloPost, Date fechaPost, String cuerpoPost, char tipoPost, ClienteEntity cedulaUsuario, AdministradorEntity administradorEntity,String imagen, String estado) {
        this.idPost = post;
        this.tituloPost = tituloPost;
        this.fechaPost = fechaPost;
        this.cuerpoPost = cuerpoPost;
        this.tipoPost = tipoPost;
        this.cedulaUsuario = cedulaUsuario;
        this.administaradorCreador = administradorEntity;
        this.imagen = imagen;
        this.estado = estado;
    }

    private int idPost;

    private String tituloPost;

    private Date fechaPost;

    private String imagen;

    private String cuerpoPost;

    private char tipoPost;

    private ClienteEntity cedulaUsuario;

    private AdministradorEntity administaradorCreador;

    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public ClienteEntity getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(ClienteEntity cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public AdministradorEntity getAdministaradorCreador() {
        return administaradorCreador;
    }

    public void setAdministaradorCreador(AdministradorEntity administaradorCreador) {
        this.administaradorCreador = administaradorCreador;
    }
}
