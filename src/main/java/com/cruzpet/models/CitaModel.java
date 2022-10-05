package com.cruzpet.models;

import com.cruzpet.entitys.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class CitaModel {

    public CitaModel() {
    }

    public CitaModel(CitaEntity citaEntity) {
        this.idCita = citaEntity.getIdCita();
        this.estadoCita = citaEntity.getEstadoCita();
        this.fecha = citaEntity.getFecha();
        this.hora = citaEntity.getHora();
        this.email = citaEntity.getEmail();
        this.telefono = citaEntity.getTelefono();
        this.ipsaPropetaria = citaEntity.getIpsaPropetaria();
        this.citaVeterinario = citaEntity.getCitaVeterinario();
        this.usuarioCita = citaEntity.getUsuarioCita();
        this.citaMascota = citaEntity.getCitaMascota();
        this.citaCiudad = citaEntity.getCitaCiudad();
        this.citaDepartamento = citaEntity.getCitaDepartamento();
        this.tipoDocumento = citaEntity.getTipoDocumentoClientes();
        this.tipoTramite = citaEntity.getTipoTramite();
    }

    private Integer idCita;

    private String estadoCita;

    private LocalDate fecha;

    private LocalTime hora;

    private String email;

    private String telefono;

    private IpsaEntity ipsaPropetaria;

    private VeterinarioEntity citaVeterinario;

    private ClienteEntity usuarioCita;

    private MascotaEntity citaMascota;

    private  DepartamentoEntity citaDepartamento;

    private  CiudadEntity citaCiudad;

    private String tipoDocumento;

    private String tipoTramite;

    public String getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public DepartamentoEntity getCitaDepartamento() {
        return citaDepartamento;
    }

    public void setCitaDepartamento(DepartamentoEntity citaDepartamento) {
        this.citaDepartamento = citaDepartamento;
    }

    public CiudadEntity getCitaCiudad() {
        return citaCiudad;
    }

    public void setCitaCiudad(CiudadEntity citaCiudad) {
        this.citaCiudad = citaCiudad;
    }

    public Integer getIdCita() {
        return idCita;
    }

    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }

    public String getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(String estadoCita) {
        this.estadoCita = estadoCita;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public IpsaEntity getIpsaPropetaria() {
        return ipsaPropetaria;
    }

    public void setIpsaPropetaria(IpsaEntity ipsaPropetaria) {
        this.ipsaPropetaria = ipsaPropetaria;
    }

    public VeterinarioEntity getCitaVeterinario() {
        return citaVeterinario;
    }

    public void setCitaVeterinario(VeterinarioEntity citaVeterinario) {
        this.citaVeterinario = citaVeterinario;
    }

    public ClienteEntity getUsuarioCita() {
        return usuarioCita;
    }

    public void setUsuarioCita(ClienteEntity usuarioCita) {
        this.usuarioCita = usuarioCita;
    }

    public MascotaEntity getCitaMascota() {
        return citaMascota;
    }

    public void setCitaMascota(MascotaEntity citaMascota) {
        this.citaMascota = citaMascota;
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
}
