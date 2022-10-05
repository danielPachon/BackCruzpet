package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.VacunaDetalleEntity;
import com.cruzpet.entitys.VacunaEntity;
import com.cruzpet.models.VacunaDetalleModel;
import com.cruzpet.models.VacunasModel;
import com.cruzpet.repositorys.VacunaDetalleRepository;
import com.cruzpet.repositorys.VacunasRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("vacunaDetalleService")
public class VacunaDetalleService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("vacunaDetalleRepository")
    private VacunaDetalleRepository vacunaDetalleRepository;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    public void crear(VacunaDetalleEntity vacunaDetalleEntity){
        try{
            vacunaDetalleRepository.save(vacunaDetalleEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void actualizar(VacunaDetalleEntity vacunaDetalleEntity){

        try {
            VacunaDetalleEntity vacunaDetalle = vacunaDetalleRepository.findByIdVacunasDetalles(vacunaDetalleEntity.getIdVacunasDetalles());
            vacunaDetalle.setNombreVacuna(vacunaDetalleEntity.getNombreVacuna());
            vacunaDetalle.setTipoVacuna(vacunaDetalleEntity.getTipoVacuna());
            vacunaDetalle.setAdministradorCreador(vacunaDetalle.getAdministradorCreador());
            vacunaDetalleRepository.save(vacunaDetalle);
        }catch (IllegalArgumentException i){
            log.error(i.getMessage());
        }
    }

    public void borrar(Integer idVacunaDetalle){
        try{
            VacunaDetalleEntity vacunaDetalle = vacunaDetalleRepository.findByIdVacunasDetalles(idVacunaDetalle);
            vacunaDetalleRepository.delete(vacunaDetalle);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public List<VacunaDetalleModel> obtenerVacunasDetalles(){

        return convertidor.convertidorListaVacunasDetalles(vacunaDetalleRepository.findAll());

    }

}
