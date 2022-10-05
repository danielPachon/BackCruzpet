package com.cruzpet.models;

import com.cruzpet.entitys.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClienteModel implements UserDetails {

    public ClienteModel() {
    }

    public ClienteModel(ClienteEntity clienteEntity) {
        this.cedulaUsuario = clienteEntity.getCedulaCliente();
        this.username = clienteEntity.getUsername();
        this.email = clienteEntity.getEmail();
        this.telefono = clienteEntity.getTelefono();
        this.nombres = clienteEntity.getNombres();
        this.apellidos = clienteEntity.getApellidos();
        this.estado = clienteEntity.getEstado();
        this.imagenCliente = clienteEntity.getImagenCliente();
        this.password = clienteEntity.getPassword();
        this.administrador = clienteEntity.getAdministradorCreador();
        this.tipoDocumento = clienteEntity.getTipoDocumento();
        this.planes = clienteEntity.getPlanes();
        this.compras = clienteEntity.getCompras();
        this.direccionEntity = clienteEntity.getDireccion();
        this.cantidadMascota = clienteEntity.getMascota().size();
    }

    public ClienteModel(String cedulaUsuario, String username, String email, String telefono, String nombres, String apellidos, String estado, String imagenCliente, String password, String tipoDocumento, AdministradorEntity administrador,  PlanesEntity planes,Set<RolEntity> roles, Collection<? extends GrantedAuthority> authorities, ComprasEntity comprasEntity, DireccionEntity direccionEntity, Integer cantidadMascota) {
        this.cedulaUsuario = cedulaUsuario;
        this.username = username;
        this.email = email;
        this.telefono = telefono;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.estado = estado;
        this.imagenCliente = imagenCliente;
        this.password = password;
        this.tipoDocumento = tipoDocumento;
        this.administrador = administrador;
        this.roles = roles;
        this.authorities = authorities;
        this.planes = planes;
        this.idCarrito = comprasEntity.getIdCompra();
        this.direccionEntity = direccionEntity;
        this.cantidadMascota = cantidadMascota;
    }

    public static ClienteModel build(ClienteEntity clienteEntity){

        List<GrantedAuthority> authorities = clienteEntity.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre())).collect(Collectors.toList());

        return new ClienteModel(clienteEntity.getCedulaCliente(), clienteEntity.getUsername(), clienteEntity.getEmail(), clienteEntity.getTelefono(), clienteEntity.getNombres(), clienteEntity.getApellidos(), clienteEntity.getEstado(), clienteEntity.getImagenCliente(), clienteEntity.getPassword(), clienteEntity.getTipoDocumento(), clienteEntity.getAdministradorCreador(),  clienteEntity.getPlanes(),clienteEntity.getRoles(),authorities, clienteEntity.getCompras(), clienteEntity.getDireccion(), clienteEntity.getMascota().size());

    }

    private String cedulaUsuario;

    private PlanesEntity planes;

    private String username;

    private String email;

    private String telefono;

    private String nombres;

    private String apellidos;

    private String estado;

    private String imagenCliente;

    private String password;

    private DireccionEntity direccionEntity;

    private String tipoDocumento;

    private AdministradorEntity administrador;

    private ComprasEntity compras;

    private Integer idCarrito;

    private Integer cantidadMascota;

    public Integer getCantidadMascota() {
        return cantidadMascota;
    }

    public void setCantidadMascota(Integer cantidadMascota) {
        this.cantidadMascota = cantidadMascota;
    }

    public Integer getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Integer idCarrito) {
        this.idCarrito = idCarrito;
    }

    public DireccionEntity getDireccionEntity() {
        return direccionEntity;
    }

    public void setDireccionEntity(DireccionEntity direccionEntity) {
        this.direccionEntity = direccionEntity;
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

    private Set<RolEntity> roles = new HashSet<>();

    private Collection<? extends GrantedAuthority> authorities;

    public Set<RolEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolEntity> roles) {
        this.roles = roles;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public String getUsernames() {
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

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AdministradorEntity getAdministrador() {
        return administrador;
    }

    public void setAdministrador(AdministradorEntity administrador) {
        this.administrador = administrador;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


