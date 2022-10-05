package com.cruzpet.models;

import com.cruzpet.entitys.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class VeterinarioModel implements UserDetails {

    public VeterinarioModel() {
    }

    public VeterinarioModel(VeterinarioEntity veterinarioEntity) {
        this.cedVeterinario = veterinarioEntity.getCedVeterinario();
        this.nombres = veterinarioEntity.getNombres();
        this.apellidos = veterinarioEntity.getApellidos();
        this.fechaNacimiento = veterinarioEntity.getFechaNacimiento();
        this.direccion = veterinarioEntity.getDireccion();
        this.celular = veterinarioEntity.getCelular();
        this.foto = veterinarioEntity.getFoto();
        this.ipsaTrabajo = veterinarioEntity.getIpsaTrabajo();
        this.especialidadVeterinario = veterinarioEntity.getEspecialidadVeterinario();
        this.correoVeterinario = veterinarioEntity.getCorreoVeterinario();
        this.passwordVeterinario = veterinarioEntity.getPasswordVeterinario();
        this.administradorCreador = veterinarioEntity.getAdministradorCreador();
        this.veterinarioDisponibilidadeEntities = veterinarioEntity.getVeterinarioDisponibilidadeEntities();
        this.estado = veterinarioEntity.getEstado();
    }


    public VeterinarioModel(String cedVeterinario, String nombres, String apellidos, Date fechaNacimiento, DireccionEntity direccion, String celular, String foto, IpsaEntity ipsaTrabajo, EspecialidadEntity especialidadVeterinario, String correoVeterinario, String passwordVeterinario, AdministradorEntity administradorCreador, Set<RolEntity> roles, Collection<? extends GrantedAuthority> authorities, String estado) {
        this.cedVeterinario = cedVeterinario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.celular = celular;
        this.foto = foto;
        this.ipsaTrabajo = ipsaTrabajo;
        this.especialidadVeterinario = especialidadVeterinario;
        this.correoVeterinario = correoVeterinario;
        this.passwordVeterinario = passwordVeterinario;
        this.administradorCreador = administradorCreador;
        this.roles = roles;
        this.authorities = authorities;
        this.estado = estado;
    }



    public static VeterinarioModel build(VeterinarioEntity veterinarioEntity){

        List<GrantedAuthority> authorities = veterinarioEntity.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre())).collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority(veterinarioEntity.getDireccion().getIdDireccion().toString()));

        return new VeterinarioModel(veterinarioEntity.getCedVeterinario(), veterinarioEntity.getNombres(), veterinarioEntity.getApellidos(), veterinarioEntity.getFechaNacimiento(), veterinarioEntity.getDireccion(), veterinarioEntity.getCelular(), veterinarioEntity.getFoto(), veterinarioEntity.getIpsaTrabajo(), veterinarioEntity.getEspecialidadVeterinario(), veterinarioEntity.getCorreoVeterinario(), veterinarioEntity.getPasswordVeterinario(), veterinarioEntity.getAdministradorCreador(),  veterinarioEntity.getRoles(),authorities, veterinarioEntity.getEstado());

    }

    private String cedVeterinario;

    private  String  nombres;

    private String apellidos;

    private Date fechaNacimiento;

    private DireccionEntity direccion;

    private String celular;

    private String foto;

    private IpsaEntity ipsaTrabajo;

    private EspecialidadEntity especialidadVeterinario;

    private String correoVeterinario;

    private String passwordVeterinario;

    private AdministradorEntity administradorCreador;

    private Set<RolEntity> roles = new HashSet<>();

    private Collection<? extends GrantedAuthority> authorities;

    private List<VeterinarioDisponibilidadesEntity> veterinarioDisponibilidadeEntities;

    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<VeterinarioDisponibilidadesEntity> getVeterinarioDisponibilidadeEntities() {
        return veterinarioDisponibilidadeEntities;
    }

    public void setVeterinarioDisponibilidadeEntities(List<VeterinarioDisponibilidadesEntity> veterinarioDisponibilidadeEntities) {
        this.veterinarioDisponibilidadeEntities = veterinarioDisponibilidadeEntities;
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

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getCedVeterinario() {
        return cedVeterinario;
    }

    public void setCedVeterinario(String cedVeterinario) {
        this.cedVeterinario = cedVeterinario;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public DireccionEntity getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionEntity direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public IpsaEntity getIpsaTrabajo() {
        return ipsaTrabajo;
    }

    public void setIpsaTrabajo(IpsaEntity ipsaTrabajo) {
        this.ipsaTrabajo = ipsaTrabajo;
    }

    public EspecialidadEntity getEspecialidadVeterinario() {
        return especialidadVeterinario;
    }

    public void setEspecialidadVeterinario(EspecialidadEntity especialidadVeterinario) {
        this.especialidadVeterinario = especialidadVeterinario;
    }

    public String getCorreoVeterinario() {
        return correoVeterinario;
    }

    public void setCorreoVeterinario(String correoVeterinario) {
        this.correoVeterinario = correoVeterinario;
    }

    public String getPasswordVeterinario() {
        return passwordVeterinario;
    }

    public void setPasswordVeterinario(String passwordVeterinario) {
        this.passwordVeterinario = passwordVeterinario;
    }

    public AdministradorEntity getAdministradorCreador() {
        return administradorCreador;
    }

    public void setAdministradorCreador(AdministradorEntity administradorCreador) {
        this.administradorCreador = administradorCreador;
    }

    @Override
    public String getPassword() {
        return passwordVeterinario;
    }

    @Override
    public String getUsername() {
        return nombres;
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
