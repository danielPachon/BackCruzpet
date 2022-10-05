package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.IpsaEntity;
import com.cruzpet.entitys.ServiciosEntity;
import com.cruzpet.models.ServiciosModel;
import com.cruzpet.repositorys.ServicioRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service("servicioService")
public class ServicioService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("servicioRepository")
    private ServicioRepository servicioRepository;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    public void insertar(ServiciosEntity serviciosEntity){

        try{

            servicioRepository.save(serviciosEntity);

        }catch (Exception ex){

            log.error(ex.getMessage());

        }

    }

    public void actualizar(ServiciosEntity serviciosEntity){

        try{

            ServiciosEntity servicio = servicioRepository.findByIdServicio(serviciosEntity.getIdServicio());
            servicio.setNombreServicio(serviciosEntity.getNombreServicio());
            servicio.setRutaImagenServicio(serviciosEntity.getRutaImagenServicio());

            try{

                servicioRepository.save(servicio);

            }catch (Exception ex){

                log.error(ex.getMessage());

            }

        }catch (Exception ex){

            log.error(ex.getMessage());
        }

    }

    public void eliminar(Integer id){

        ServiciosEntity serviciosEntity = servicioRepository.findByIdServicio(id);

        try {

            servicioRepository.delete(serviciosEntity);

        }catch (Exception ex){

            log.error(ex.getMessage());

        }

    }

    public List<ServiciosModel> traerServicios(){

        return convertidor.convertidorListaServicio(servicioRepository.findAll());

    }

    public Page<ServiciosEntity> obtenerServiciosPaginador(Pageable pageable){
        return servicioRepository.findAll(pageable);
    }

    public ServiciosEntity obtenerServicioId(ServiciosEntity serviciosEntity){

        return servicioRepository.findByIdServicio(serviciosEntity.getIdServicio());

    }


}
