package com.cruzpet.entitys;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "citas")
public class CitaEntity {

    public CitaEntity() {
    }

    public CitaEntity(Integer idCita, String estadoCita, LocalDate fecha, String telefono,String email,LocalTime hora, CiudadEntity citaCiudad,DepartamentoEntity departamentoEntity,IpsaEntity ipsaPropetaria, VeterinarioEntity citaVeterinario, ClienteEntity clienteCita, MascotaEntity citaMascota, String tipoDocumentoCliente, String tipoTramite) {
        this.idCita = idCita;
        this.estadoCita = estadoCita;
        this.fecha = fecha;
        this.hora = hora;
        this.email = email;
        this.telefono = telefono;
        this.ipsaPropetaria = ipsaPropetaria;
        this.citaVeterinario = citaVeterinario;
        this.clienteCita = clienteCita;
        this.citaMascota = citaMascota;
        this.citaDepartamento = departamentoEntity;
        this.citaCiudad = citaCiudad;
        this.tipoDocumentoClientes = tipoDocumentoCliente;
        this.tipoTramite = tipoTramite;
    }

    @Id
    @GeneratedValue(generator = "secuenciacitas")
    @SequenceGenerator(name = "secuenciacitas", sequenceName = "autocitas", allocationSize = 1)
    @Column(name = "idcita")
    private Integer idCita;

    @Column(name = "estadocita", nullable = false, length = 1)
    private String estadoCita;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "hora", nullable = false)
    private LocalTime hora;

    @Column(name = "tipodocumentoclientes", nullable = false, length = 4)
    private String tipoDocumentoClientes;

    @Column(name = "correo", nullable = false)
    private String email;

    @Column(name = "telefono", nullable = false, length = 10)
    private String telefono;

    @Column(name = "tipotramite", nullable = false)
    private String tipoTramite;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rutfk")
    private IpsaEntity ipsaPropetaria;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "cedveterinariofk")
    private VeterinarioEntity citaVeterinario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cedulaclientefk")
    private ClienteEntity clienteCita;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "numeroidentidadfk")
    private  MascotaEntity citaMascota;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departamentofk")
    private  DepartamentoEntity citaDepartamento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ciudadfk")
    private  CiudadEntity citaCiudad;

    public String getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public String getTipoDocumentoClientes() {
        return tipoDocumentoClientes;
    }

    public void setTipoDocumentoClientes(String tipoDocumentoCliente) {
        this.tipoDocumentoClientes = tipoDocumentoCliente;
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

    public ClienteEntity getClienteCita() {
        return clienteCita;
    }

    public void setClienteCita(ClienteEntity clienteCita) {
        this.clienteCita = clienteCita;
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
        return clienteCita;
    }

    public void setUsuarioCita(ClienteEntity usuarioCita) {
        this.clienteCita = usuarioCita;
    }

    public MascotaEntity getCitaMascota() {
        return citaMascota;
    }

    public void setCitaMascota(MascotaEntity citaMascota) {
        this.citaMascota = citaMascota;
    }

}
