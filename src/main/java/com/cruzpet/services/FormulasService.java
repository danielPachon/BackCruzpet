package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.FormulaEntity;
import com.cruzpet.entitys.MedicamentoEntity;
import com.cruzpet.models.FormulasModel;
import com.cruzpet.repositorys.FormulasRepository;
import com.cruzpet.repositorys.MedicamentoRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("formulasService")
public class FormulasService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("formulasRepository")
    private FormulasRepository formulasRepository;

    @Autowired
    @Qualifier("medicamentoRepository")
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    public void crear(FormulaEntity formulaEntity){

        FormulaEntity formula = new FormulaEntity();

        formula.setIdFormula(formulaEntity.getIdFormula());
        formula.setDetallesFormulas(formulaEntity.getDetallesFormulas());
        formula.setFecha(formulaEntity.getFecha());
        formula.setMedicamento(formulaEntity.getMedicamento().stream().map(medicamentoEntity -> {
            MedicamentoEntity medicamento = medicamentoEntity;
            if (medicamento.getIdMedicamento() > 0) {
                medicamento = medicamentoRepository.findByIdMedicamento(medicamento.getIdMedicamento());
            }
            medicamento.incluirFormula(formula);
            return medicamento;
        }).collect(Collectors.toList()));
        formula.setEstado(formulaEntity.getEstado());
        formula.setMascotaEntity(formulaEntity.getMascotaEntity());

        try{
            formulasRepository.save(formula);
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }

    public void actualizar(FormulaEntity formulaEntity){
        try{
            FormulaEntity formulas = formulasRepository.findByIdFormula(formulaEntity.getIdFormula());
            formulas.setFecha(formulaEntity.getFecha());
            formulas.setDetallesFormulas(formulaEntity.getDetallesFormulas());
            formulas.setMedicamento(formulaEntity.getMedicamento());
            formulasRepository.save(formulas);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void eliminar(Integer idFormulas){

        try{
            FormulaEntity formulaEntity = formulasRepository.findByIdFormula(idFormulas);
            formulasRepository.delete(formulaEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }

    public List<FormulasModel> mostrarEspecialidades(){
        return convertidor.convertidorListaFormulas(formulasRepository.findAll());
    }

    public void actualizarEstadoFormula(FormulaEntity formulaEntity){
        FormulaEntity formula = formulasRepository.findByIdFormula(formulaEntity.getIdFormula());

        formula.setEstado(formulaEntity.getEstado());

        formulasRepository.save(formula);
    }

    public List<FormulaEntity> traerFormulasMascota(FormulaEntity formulaEntity){

        return  formulasRepository.findByMascotaEntity_NumeroIdentidad(formulaEntity.getMascotaEntity().getNumeroIdentidad());

    }


}
