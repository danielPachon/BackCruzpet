package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "formulas")
public class FormulaEntity {

    public FormulaEntity() {
    }

    public FormulaEntity(Date fecha, String detallesFormulas, List<MedicamentoEntity> medicamento, String estado, MascotaEntity mascotaEntity) {
        this.fecha = fecha;
        this.detallesFormulas = detallesFormulas;
        this.medicamento = medicamento;
        this.estado = estado;
        this.mascotaEntity = mascotaEntity;
    }

    public FormulaEntity(Integer idFormula, Date fecha, String detallesFormulas, List<MedicamentoEntity> medicamento, String estado, MascotaEntity mascotaEntity) {
        this.idFormula = idFormula;
        this.fecha = fecha;
        this.detallesFormulas = detallesFormulas;
        this.medicamento = medicamento;
        this.estado = estado;
        this.mascotaEntity = mascotaEntity;
    }

    @Id
    @GeneratedValue(generator = "secuenciaformulas")
    @SequenceGenerator(name = "secuenciaformulas", sequenceName = "autoformulas", allocationSize = 1)
    @Column(name = "idformula")
    private Integer idFormula;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "detallesformulas", nullable = false, length = 1000)
    private String detallesFormulas;

    @ManyToMany
    @JoinTable(
            name = "formulas_medicamentos",
            joinColumns = @JoinColumn(name = "idformulafk"),
            inverseJoinColumns = @JoinColumn(name = "idmedicamentofk")
    )
    private List<MedicamentoEntity> medicamento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idmascotafk")
    private MascotaEntity mascotaEntity;

    @Column(name = "estado")
    private String estado;

    public MascotaEntity getMascotaEntity() {
        return mascotaEntity;
    }

    public void setMascotaEntity(MascotaEntity mascotaEntity) {
        this.mascotaEntity = mascotaEntity;
    }

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
