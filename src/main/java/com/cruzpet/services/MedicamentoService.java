package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.ClienteEntity;
import com.cruzpet.entitys.MedicamentoEntity;
import com.cruzpet.models.MedicamentosModel;
import com.cruzpet.repositorys.MedicamentoRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("medicamentoService")
public class MedicamentoService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("medicamentoRepository")
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    public void crear(MedicamentoEntity medicamentos){
        try{
            medicamentoRepository.save(medicamentos);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void actualizar(MedicamentoEntity medicamentos){

        try {
            MedicamentoEntity medicamento = medicamentoRepository.findByIdMedicamento(medicamentos.getIdMedicamento());
            medicamento.setIdMedicamento(medicamentos.getIdMedicamento());
            medicamento.setNombreMedicamento(medicamentos.getNombreMedicamento());
            medicamento.setDescripcionMedicamento(medicamentos.getDescripcionMedicamento());
            medicamento.setAdministradorCreador(medicamentos.getAdministradorCreador());
            medicamento.setEstado(medicamentos.getEstado());
            medicamentoRepository.save(medicamento);
        }catch (IllegalArgumentException i){
            log.error(i.getMessage());
        }
    }

    public void borrar(int id){
        try{
            MedicamentoEntity medicamento = medicamentoRepository.findByIdMedicamento(id);
            medicamentoRepository.delete(medicamento);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public List<MedicamentosModel> obtenerMedicamentos(){

        return convertidor.convertidorListaMedicamento(medicamentoRepository.findAll());

    }

    public MedicamentoEntity obtenerMedicamentoId(MedicamentoEntity medicamentoEntity){

        return medicamentoRepository.findByIdMedicamento(medicamentoEntity.getIdMedicamento());

    }

    public Page<MedicamentoEntity> obtenerMedicamentosPaginador(Pageable pageable){
        return medicamentoRepository.findAll(pageable);
    }

}
