package com.cruzpet.models;


import com.cruzpet.entitys.AdministradorEntity;
import com.cruzpet.entitys.DireccionEntity;
import com.cruzpet.entitys.IpsaEntity;
import com.cruzpet.entitys.RolEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class IpsasModel implements UserDetails {

    public IpsasModel() {
    }

    public IpsasModel(IpsaEntity ipsaEntity) {
        this.rut = ipsaEntity.getRut();
        this.nombre = ipsaEntity.getNombre();
        this.ubicacion = ipsaEntity.getDireccionIpsa();
        this.estado = ipsaEntity.getEstado();
        this.logoIpsa = ipsaEntity.getLogoIpsa();
        this.correoIpsa = ipsaEntity.getCorreoIpsa();
        this.passwordIpsa = ipsaEntity.getPasswordIpsa();
        this.administrador = ipsaEntity.getAdministradorCreador();
    }


    public IpsasModel(int rut, String nombre, DireccionEntity ubicacion, String estado, String logoIpsa, String correoIpsa, String passwordIpsa, AdministradorEntity administrador, Set<RolEntity> roles, Collection<? extends GrantedAuthority> authorities) {
        this.rut = rut;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.estado = estado;
        this.logoIpsa = logoIpsa;
        this.correoIpsa = correoIpsa;
        this.passwordIpsa = passwordIpsa;
        this.administrador = administrador;
        this.roles = roles;
        this.authorities = authorities;
    }

    public static IpsasModel build(IpsaEntity ipsaEntity){

        List<GrantedAuthority> authorities = ipsaEntity.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre())).collect(Collectors.toList());

        return new IpsasModel(ipsaEntity.getRut(), ipsaEntity.getNombre(), ipsaEntity.getDireccionIpsa(), ipsaEntity.getEstado(), ipsaEntity.getLogoIpsa(), ipsaEntity.getCorreoIpsa(), ipsaEntity.getPasswordIpsa(), ipsaEntity.getAdministradorCreador(),  ipsaEntity.getRoles(),authorities);

    }

    private int rut;

    private  String nombre;

    private DireccionEntity ubicacion;

    private  String estado;

    private String logoIpsa;

    private String correoIpsa;

    private String passwordIpsa;

    private AdministradorEntity administrador;

    private Set<RolEntity> roles = new HashSet<>();

    private Collection<? extends GrantedAuthority> authorities;

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DireccionEntity getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(DireccionEntity ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLogoIpsa() {
        return logoIpsa;
    }

    public void setLogoIpsa(String logoIpsa) {
        this.logoIpsa = logoIpsa;
    }

    public String getCorreoIpsa() {
        return correoIpsa;
    }

    public void setCorreoIpsa(String correoIpsa) {
        this.correoIpsa = correoIpsa;
    }

    public String getPasswordIpsa() {
        return passwordIpsa;
    }

    public void setPasswordIpsa(String passwordIpsa) {
        this.passwordIpsa = passwordIpsa;
    }

    public AdministradorEntity getAdministrador() {
        return administrador;
    }

    public void setAdministrador(AdministradorEntity administrador) {
        this.administrador = administrador;
    }

    public Set<RolEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolEntity> roles) {
        this.roles = roles;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return passwordIpsa;
    }

    @Override
    public String getUsername() {
        return correoIpsa;
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

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}

