package com.cruzpet.models;

import com.cruzpet.entitys.AdministradorEntity;
import com.cruzpet.entitys.RolEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AdministradorModel implements UserDetails {

    public AdministradorModel(AdministradorEntity administradorEntity) {
        this.idAdministrador = administradorEntity.getIdAdministrador();
        this.nombreAdministrador = administradorEntity.getNombreAdministrador();
        this.correAdministrador = administradorEntity.getCorreAdministrador();
        this.passwordAdministrador = administradorEntity.getPasswordAdministrador();
        this.estado = administradorEntity.getEstado();
    }

    public AdministradorModel(Integer idAdministrador, String nombreAdministrador, String correAdministrador, String passwordAdministrador, Set<RolEntity> roles, Collection<? extends GrantedAuthority> authorities, String estado) {
        this.idAdministrador = idAdministrador;
        this.nombreAdministrador = nombreAdministrador;
        this.correAdministrador = correAdministrador;
        this.passwordAdministrador = passwordAdministrador;
        this.roles = roles;
        this.authorities = authorities;
        this.estado = estado;
    }



    private Integer idAdministrador;

    private String nombreAdministrador;

    private String correAdministrador;

    private String passwordAdministrador;

    private Set<RolEntity> roles = new HashSet<>();

    private Collection<? extends GrantedAuthority> authorities;

    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public static AdministradorModel build(AdministradorEntity administradorEntity){

        List<GrantedAuthority> authorities = administradorEntity.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre())).collect(Collectors.toList());

        return new AdministradorModel(administradorEntity.getIdAdministrador(), administradorEntity.getNombreAdministrador(), administradorEntity.getCorreAdministrador(), administradorEntity.getPasswordAdministrador(),administradorEntity.getRoles(), authorities, administradorEntity.getEstado());

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
        return passwordAdministrador;
    }

    @Override
    public String getUsername() {
        return correAdministrador;
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

    public Integer getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Integer idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

    public void setNombreAdministrador(String nombreAdministrador) {
        this.nombreAdministrador = nombreAdministrador;
    }

    public String getCorreAdministrador() {
        return correAdministrador;
    }

    public void setCorreAdministrador(String correAdministrador) {
        this.correAdministrador = correAdministrador;
    }

    public String getPasswordAdministrador() {
        return passwordAdministrador;
    }

    public void setPasswordAdministrador(String passwordAdministrador) {
        this.passwordAdministrador = passwordAdministrador;
    }
}
