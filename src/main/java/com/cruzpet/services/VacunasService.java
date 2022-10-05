package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.VacunaDetalleEntity;
import com.cruzpet.entitys.VacunaEntity;
import com.cruzpet.models.VacunasModel;
import com.cruzpet.repositorys.VacunaDetalleRepository;
import com.cruzpet.repositorys.VacunasRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("vacunasService")
public class VacunasService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("vacunasRepository")
    private VacunasRepository vacunasRepository;

    @Autowired
    @Qualifier("vacunaDetalleRepository")
    private VacunaDetalleRepository vacunaDetalleRepository;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    public void crear(VacunaEntity vacunaEntity){
        VacunaEntity vacuna = new VacunaEntity();

        vacuna.setIdVacuna(vacunaEntity.getIdVacuna());
        vacuna.setCantidadVacunas(vacunaEntity.getCantidadVacunas());
        vacuna.setVacunasDetalles(vacunaEntity.getVacunasDetalles().stream().map(vacunaDetalleEntity -> {
            VacunaDetalleEntity vacunaDetalle = vacunaDetalleEntity;
            if(vacunaDetalle.getIdVacunasDetalles() > 0){
                vacunaDetalle = vacunaDetalleRepository.findByIdVacunasDetalles(vacunaDetalle.getIdVacunasDetalles());
            }
            vacunaDetalle.incluirVacuna(vacuna);
            return vacunaDetalle;
        }).collect(Collectors.toList()));
        vacuna.setAdministradorCreador(vacunaEntity.getAdministradorCreador());
        try{
            vacunasRepository.save(vacuna);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void actualizar(VacunaEntity vacunaEntity){

        try {
            VacunaEntity vacunas = vacunasRepository.findByIdVacuna(vacunaEntity.getIdVacuna());
            vacunas.setCantidadVacuanas(vacunaEntity.getCantidadVacuanas());
            vacunas.setVacunasDetalles(vacunaEntity.getVacunasDetalles());
            vacunas.setAdministradorCreador(vacunaEntity.getAdministradorCreador());
            vacunasRepository.save(vacunas);
        }catch (IllegalArgumentException i){
            log.error(i.getMessage());
        }
    }

    public void borrar(Integer idVacuna){
        try{
            VacunaEntity vacuna = vacunasRepository.findByIdVacuna(idVacuna);
            vacunasRepository.delete(vacuna);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public List<VacunasModel> obtenerVacunas(){

        return convertidor.convertidorListaVacunas(vacunasRepository.findAll());

    }


}
