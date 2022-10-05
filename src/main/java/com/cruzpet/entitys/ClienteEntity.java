package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "clientes")
public class ClienteEntity {

    public ClienteEntity() {
    }

    public ClienteEntity(String cedulaCliente, String username, String email, String telefono, String nombres, String apellidos, String estado, String imagenCliente, String password, AdministradorEntity administrador, String tipoDocumento, PlanesEntity planesEntity, ComprasEntity compras, DireccionEntity direccionEntity) {
        this.cedulaCliente = cedulaCliente;
        this.username = username;
        this.email = email;
        this.telefono = telefono;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.estado = estado;
        this.imagenCliente = imagenCliente;
        this.password = password;
        this.administradorCreador = administrador;
        this.tipoDocumento = tipoDocumento;
        this.planes = planesEntity;
        this.compras = compras;
        this.direccion = direccionEntity;
    }


    @Id
    @Column(name = "cedulacliente", nullable = false, length = 10)
    private String cedulaCliente;

    @Column(name="username", nullable = false, length = 50)
    private String username;

    @Column(name="email", nullable = false, length = 100)
    private String email;

    @Column(name="telefono", nullable = false, length = 15)
    private String telefono;

    @Column(name = "nombres", nullable = false, length = 59)
    private String nombres;

    @Column(name="apellidos", nullable = false, length = 50)
    private String apellidos;

    @Column(name = "estado", nullable = false, length = 1)
    private String estado = "a";

    @Column(name = "imagencliente", nullable = false, length = 500)
    private String imagenCliente;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "tipodocumento", nullable = false, length = 2)
    private String tipoDocumento;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "clientes_rol", joinColumns = @JoinColumn(name = "clientes_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<RolEntity> roles = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "clienteCita", fetch = FetchType.EAGER)
    private List<CitaEntity> cita;

    @JsonIgnore
    @OneToMany(mappedBy = "clienteMascota", fetch = FetchType.LAZY)
    private List<MascotaEntity> mascota;

    @JsonIgnore
    @OneToMany(mappedBy = "codigoReferenciaCliente", fetch = FetchType.LAZY)
    private List<CodigoReferenciaEntity> codigoReferencia;

    @OneToMany(mappedBy = "historiaClinicaCliente", fetch = FetchType.LAZY)
    private List<HistorialClinicaEntity> historiaClinica;

    @OneToMany(mappedBy = "cedulaCliente", fetch = FetchType.LAZY)
    private List<PostEntity> post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idadministradorfk")
    private AdministradorEntity administradorCreador;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idplanesfk")
    private PlanesEntity planes;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idComprasFk")
    private ComprasEntity compras;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "iddireccionesfk", nullable = true)
    private DireccionEntity direccion;

    public DireccionEntity getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionEntity direccion) {
        this.direccion = direccion;
    }

    public ComprasEntity getCompras() {
        return compras;
    }

    public void setCompras(ComprasEntity compras) {
        this.compras = compras;
    }

    public PlanesEntity getPlanes() {
        return planes;
    }

    public void setPlanes(PlanesEntity planes) {
        this.planes = planes;
    }

    public List<MascotaEntity> getMascota() {
        return mascota;
    }

    public void setMascota(List<MascotaEntity> mascota) {
        this.mascota = mascota;
    }

    public Set<RolEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolEntity> roles) {
        this.roles = roles;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public List<CitaEntity> getCita() {
        return cita;
    }

    public void setCita(List<CitaEntity> cita) {
        this.cita = cita;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getImagenCliente() {
        return imagenCliente;
    }

    public void setImagenCliente(String imagenCliente) {
        this.imagenCliente = imagenCliente;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AdministradorEntity getAdministradorCreador() {
        return administradorCreador;
    }

    public void setAdministradorCreador(AdministradorEntity administradorCreador) {
        this.administradorCreador = administradorCreador;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }


}
