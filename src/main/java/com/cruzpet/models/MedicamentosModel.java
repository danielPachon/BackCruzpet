package com.cruzpet.models;


import com.cruzpet.entitys.AdministradorEntity;
import com.cruzpet.entitys.MedicamentoEntity;

public class MedicamentosModel {

    public MedicamentosModel() {
    }

    public MedicamentosModel(MedicamentoEntity medicamentoEntity) {
        this.idMedicamento = medicamentoEntity.getIdMedicamento();
        this.nombreMedicamento = medicamentoEntity.getNombreMedicamento();
        this.descripcionMedicamento = medicamentoEntity.getDescripcionMedicamento();
        this.administradorCreador = medicamentoEntity.getAdministradorCreador();
        this.estado = medicamentoEntity.getEstado();
    }

    private Integer idMedicamento;

    private String nombreMedicamento;

    private String descripcionMedicamento;

    private AdministradorEntity administradorCreador;

    private String estado;

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

    public AdministradorEntity getAdministradorCreador() {
        return administradorCreador;
    }

    public void setAdministradorCreador(AdministradorEntity administradorCreador) {
        this.administradorCreador = administradorCreador;
    }
}
