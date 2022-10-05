package com.cruzpet.models;

import com.cruzpet.entitys.FormulaEntity;
import com.cruzpet.entitys.MascotaEntity;
import com.cruzpet.entitys.MedicamentoEntity;

import java.util.Date;
import java.util.List;

public class FormulasModel {

    public FormulasModel() {
    }

    public FormulasModel(FormulaEntity formulaEntity) {
        this.idFormula = formulaEntity.getIdFormula();
        this.fecha = formulaEntity.getFecha();
        this.detallesFormulas = formulaEntity.getDetallesFormulas();
        this.medicamento = formulaEntity.getMedicamento();
        this.estado = formulaEntity.getEstado();
        this.mascotaEntity = formulaEntity.getMascotaEntity();
    }

    private Integer idFormula;

    private Date fecha;

    private MascotaEntity mascotaEntity;

    public MascotaEntity getMascotaEntity() {
        return mascotaEntity;
    }

    public void setMascotaEntity(MascotaEntity mascotaEntity) {
        this.mascotaEntity = mascotaEntity;
    }

    private String detallesFormulas;

    private List<MedicamentoEntity> medicamento;

    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdFormula() {
        return idFormula;
    }

    public void setIdFormula(Integer idFormula) {
        this.idFormula = idFormula;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDetallesFormulas() {
        return detallesFormulas;
    }

    public void setDetallesFormulas(String detallesFormulas) {
        this.detallesFormulas = detallesFormulas;
    }

    public List<MedicamentoEntity> getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(List<MedicamentoEntity> medicamento) {
        this.medicamento = medicamento;
    }
}
