package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "medicamentos")
public class MedicamentoEntity {

    public MedicamentoEntity() {
    }

    public MedicamentoEntity(String nombreMedicamento, String descripcionMedicamento, AdministradorEntity administradorCreador, String estado) {
        this.nombreMedicamento = nombreMedicamento;
        this.descripcionMedicamento = descripcionMedicamento;
        this.administradorCreador = administradorCreador;
        this.estado = estado;
    }

    public MedicamentoEntity(Integer idMedicamento, String nombreMedicamento, String descripcionMedicamento, AdministradorEntity administradorCreador, String estado) {
        this.idMedicamento = idMedicamento;
        this.nombreMedicamento = nombreMedicamento;
        this.descripcionMedicamento = descripcionMedicamento;
        this.administradorCreador = administradorCreador;
        this.estado = estado;
    }

    @Id
    @GeneratedValue(generator = "secuenciamedicamentos")
    @SequenceGenerator(name = "secuenciamedicamentos",sequenceName = "automedicamentos", allocationSize = 1)
    @Column(name = "idmedicamento")
    private Integer idMedicamento;

    @Column(name = "nombremedicamento", nullable = false, length = 50)
    private String nombreMedicamento;

    @Column(name = "descripcionmedicamento", nullable = false, length = 500)
    private String descripcionMedicamento;

    @Column(name = "estado", length = 1)
    private String estado;

    @JsonIgnore
    @ManyToMany(mappedBy = "medicamento")
    private List<FormulaEntity> formulaMedicamento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idadministradorfk")
    private AdministradorEntity administradorCreador;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(Integer idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }

    public String getDescripcionMedicamento() {
        return descripcionMedicamento;
    }

    public void setDescripcionMedicamento(String descripcionMedicamento) {
        this.descripcionMedicamento = descripcionMedicamento;
    }

    public List<FormulaEntity> getFormulaMedicamento() {
        return formulaMedicamento;
    }

    public void setFormulaMedicamento(List<FormulaEntity> formulaMedicamento) {
        this.formulaMedicamento = formulaMedicamento;
    }

    public void incluirFormula(FormulaEntity formula){
        this.formulaMedicamento.add(formula);
    }

    public AdministradorEntity getAdministradorCreador() {
        return administradorCreador;
    }

    public void setAdministradorCreador(AdministradorEntity administradorCreador) {
        this.administradorCreador = administradorCreador;
    }
}
