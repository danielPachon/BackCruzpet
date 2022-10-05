package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "historiasclinicas")
public class HistorialClinicaEntity {


    public HistorialClinicaEntity() {
    }

    public HistorialClinicaEntity(Integer idHistoriaClinica, Integer edad, Date fecha, String hora, Double peso, Double pulso, String motivoConsulta, String conclucion, IpsaEntity ipsaHistoriaClin, VeterinarioEntity historiaClinVeterinario, MascotaEntity historiaClinMascota, ClienteEntity historiaClinicaCliente, List<FormulaEntity> formula, VacunaEntity vacuna) {
        this.idHistoriaClinica = idHistoriaClinica;
        this.edad = edad;
        this.fecha = fecha;
        this.hora = hora;
        this.peso = peso;
        this.pulso = pulso;
        this.motivoConsulta = motivoConsulta;
        this.conclucion = conclucion;
        this.ipsaHistoriaClin = ipsaHistoriaClin;
        this.historiaClinVeterinario = historiaClinVeterinario;
        this.historiaClinMascota = historiaClinMascota;
        this.historiaClinicaCliente = historiaClinicaCliente;
        this.formula = formula;
        this.vacuna = vacuna;
    }

    @Id
    @GeneratedValue(generator = "secuenciahistoriaclinicas")
    @SequenceGenerator(name = "secuenciahistoriaclinicas", sequenceName = "autohistoriasclinicas", allocationSize = 1)
    @Column(name = "idhistoriaclinica")
    private Integer idHistoriaClinica;

    @Column(name = "edad", nullable = false, length = 2)
    private Integer edad;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "hora", nullable = false, length = 5)
    private String hora;

    @Column(name = "peso", nullable = false)
    private Double peso;

    @Column(name = "pulso", nullable = false)
    private Double pulso;

    @Column(name = "motivoconsulta", nullable = false, length = 1000)
    private String motivoConsulta;

    @Column(name = "conclusion", nullable = false, length = 1000)
    private String conclucion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rutfk")
    private IpsaEntity ipsaHistoriaClin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cedveterinariofk")
    private VeterinarioEntity historiaClinVeterinario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numeroidentidadfk")
    private  MascotaEntity historiaClinMascota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cedulaclientefk")
    private ClienteEntity historiaClinicaCliente;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "formulas_historialclinico", joinColumns = @JoinColumn(name = "formulas_id"), inverseJoinColumns = @JoinColumn(name = "historialclinico_id"))
    private List<FormulaEntity> formula;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idvacunasfk")
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

    public ClienteEntity getHistoriaClinicaCliente() {
        return historiaClinicaCliente;
    }

    public void setHistoriaClinicaCliente(ClienteEntity historiaClinicaCliente) {
        this.historiaClinicaCliente = historiaClinicaCliente;
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
