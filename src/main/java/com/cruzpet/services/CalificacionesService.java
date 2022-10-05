package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.BarrioEntity;
import com.cruzpet.entitys.CalificacionEntity;
import com.cruzpet.models.BarrioModel;
import com.cruzpet.models.CalificacionesModel;
import com.cruzpet.repositorys.CalificacionesRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("claificacionesservice")
public class CalificacionesService {

    Log logger = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    @Autowired
    @Qualifier("calificacionesRepository")
    private CalificacionesRepository calificacionesRepository;

    public void crear(CalificacionEntity calificacionEntity){
        try{
            calificacionesRepository.save(calificacionEntity);
            logger.info("Se creo correctamente");
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }

    public void actualizar(CalificacionEntity calificacionEntity){
        try{
            CalificacionEntity calificacion = calificacionesRepository.findByIdCalificacion(calificacionEntity.getIdCalificacion());
            calificacion.setIdCalificacion(calificacionEntity.getIdCalificacion());
            calificacion.setNumeroCalificacion(calificacionEntity.getNumeroCalificacion());
            calificacionesRepository.save(calificacion);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public void eliminar(Integer idCalificacion){

        try{
            CalificacionEntity calificacionEntity = calificacionesRepository.findByIdCalificacion(idCalificacion);
            calificacionesRepository.delete(calificacionEntity);
        }catch (Exception e){
            logger.error(e.getMessage());
        }

    }

    public List<CalificacionesModel> mostrarCalificaciones(){
        return convertidor.convertidorListaCalificaciones(calificacionesRepository.findAll());
    }
}
