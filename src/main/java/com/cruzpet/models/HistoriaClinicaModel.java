package com.cruzpet.models;

import com.cruzpet.entitys.*;

import java.util.Date;
import java.util.List;

public class HistoriaClinicaModel {

    public HistoriaClinicaModel() {
    }

    public HistoriaClinicaModel(HistorialClinicaEntity historialClinicaEntity) {
        this.idHistoriaClinica = historialClinicaEntity.getIdHistoriaClinica();
        this.edad = historialClinicaEntity.getEdad();
        this.fecha = historialClinicaEntity.getFecha();
        this.hora = historialClinicaEntity.getHora();
        this.peso = historialClinicaEntity.getPeso();
        this.pulso = historialClinicaEntity.getPulso();
        this.motivoConsulta = historialClinicaEntity.getMotivoConsulta();
        this.conclucion = historialClinicaEntity.getConclucion();
        this.ipsaHistoriaClin = historialClinicaEntity.getIpsaHistoriaClin();
        this.historiaClinVeterinario = historialClinicaEntity.getHistoriaClinVeterinario();
        this.historiaClinMascota = historialClinicaEntity.getHistoriaClinMascota();
        this.historiaClinicaUsuario = historialClinicaEntity.getHistoriaClinicaCliente();
        this.formula = historialClinicaEntity.getFormula();
        this.vacuna = historialClinicaEntity.getVacuna();
    }

    private Integer idHistoriaClinica;

    private Integer edad;

    private Date fecha;

    private String hora;

    private Double peso;

    private Double pulso;

    private String motivoConsulta;

    private String conclucion;

    private IpsaEntity ipsaHistoriaClin;

    private VeterinarioEntity historiaClinVeterinario;

    private MascotaEntity historiaClinMascota;

    private ClienteEntity historiaClinicaUsuario;

    private List<FormulaEntity> formula;

    private VacunaEntity vacuna;

    public Integer getIdHistoriaClinica() {
        return idHistoriaClinica;
    }

    public void setIdHistoriaClinica(Integer idHistoriaClinica) {
        this.idHistoriaClinica = idHistoriaClinica;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getPulso() {
        return pulso;
    }

    public void setPulso(Double pulso) {
        this.pulso = pulso;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getConclucion() {
        return conclucion;
    }

    public void setConclucion(String conclucion) {
        this.conclucion = conclucion;
    }

    public IpsaEntity getIpsaHistoriaClin() {
        return ipsaHistoriaClin;
    }

    public void setIpsaHistoriaClin(IpsaEntity ipsaHistoriaClin) {
        this.ipsaHistoriaClin = ipsaHistoriaClin;
    }

    public VeterinarioEntity getHistoriaClinVeterinario() {
        return historiaClinVeterinario;
    }

    public void setHistoriaClinVeterinario(VeterinarioEntity historiaClinVeterinario) {
        this.historiaClinVeterinario = historiaClinVeterinario;
    }

    public MascotaEntity getHistoriaClinMascota() {
        return historiaClinMascota;
    }

    public void setHistoriaClinMascota(MascotaEntity historiaClinMascota) {
        this.historiaClinMascota = historiaClinMascota;
    }

    public ClienteEntity getHistoriaClinicaUsuario() {
        return historiaClinicaUsuario;
    }

    public void setHistoriaClinicaUsuario(ClienteEntity historiaClinicaUsuario) {
        this.historiaClinicaUsuario = historiaClinicaUsuario;
    }

    public List<FormulaEntity> getFormula() {
        return formula;
    }

    public void setFormula(List<FormulaEntity> formula) {
        this.formula = formula;
    }

    public VacunaEntity getVacuna() {
        return vacuna;
    }

    public void setVacuna(VacunaEntity vacuna) {
        this.vacuna = vacuna;
    }
}
