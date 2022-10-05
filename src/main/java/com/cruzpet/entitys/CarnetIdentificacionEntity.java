package com.cruzpet.entitys;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "carnetidentificacion")
public class CarnetIdentificacionEntity {

    public CarnetIdentificacionEntity() {
    }

    public CarnetIdentificacionEntity(String lugarNacimiento, Double estatura, String fechaLugarEspedicion, MascotaEntity mascota, TipoSangreEntity tipoSangre, GeneroEntity genero) {
        this.lugarNacimiento = lugarNacimiento;
        this.estatura = estatura;
        this.fechaLugarEspedicion = fechaLugarEspedicion;
        this.mascota = mascota;
        this.tipoSangre = tipoSangre;
        this.genero = genero;
    }

    @Id
    @GeneratedValue(generator = "secuenciacarnetidentificacion")
    @SequenceGenerator(name = "secuenciacarnetidentificacion", sequenceName = "autocarnetidentificacion", allocationSize = 1)
    @Column(name = "idcarnetidentificacion")
    private Integer idCarnetIdentificacion;

    @Column(name = "lugarNacimiento")
    private String lugarNacimiento;

    @Column(name = "estatura")
    private Double estatura;

    @Column(name = "fechalugarespedicion")
    private String fechaLugarEspedicion;

    @OneToOne
    @JoinColumn(name = "idmascotafk")
    private MascotaEntity mascota;

    @OneToOne
    @JoinColumn(name = "idtiposangrefk")
    private TipoSangreEntity tipoSangre;

    @OneToOne
    @JoinColumn(name = "idgenerofk")
    private GeneroEntity genero;

    public Integer getIdCarnetIdentificacion() {
        return idCarnetIdentificacion;
    }

    public void setIdCarnetIdentificacion(Integer idCarnetIdentificacion) {
        this.idCarnetIdentificacion = idCarnetIdentificacion;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public Double getEstatura() {
        return estatura;
    }

    public void setEstatura(Double estatura) {
        this.estatura = estatura;
    }

    public String getFechaLugarEspedicion() {
        return fechaLugarEspedicion;
    }

    public void setFechaLugarEspedicion(String fechaLugarEspedicion) {
        this.fechaLugarEspedicion = fechaLugarEspedicion;
    }

    public MascotaEntity getMascota() {
        return mascota;
    }

    public void setMascota(MascotaEntity mascota) {
        this.mascota = mascota;
    }

    public TipoSangreEntity getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(TipoSangreEntity tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public GeneroEntity getGenero() {
        return genero;
    }

    public void setGenero(GeneroEntity genero) {
        this.genero = genero;
    }
}
