package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.ServiciosEntity;
import com.cruzpet.entitys.TipoMascotaEntity;
import com.cruzpet.entitys.TipoSangreEntity;
import com.cruzpet.models.TipoMascotaModel;
import com.cruzpet.models.TipoSangreModel;
import com.cruzpet.repositorys.TipoMascotaRepository;
import com.cruzpet.repositorys.TipoSangreRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tipoSangreService")
public class TipoSangreService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("tipoSangreRepositorio")
    private TipoSangreRepository tipoSangreRepository;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    public void crear(TipoSangreEntity tipoSangreEntity){
        try{
            tipoSangreRepository.save(tipoSangreEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void actualizar(TipoSangreEntity tipoSangreEntity){

        try {
            TipoSangreEntity tipoSangre = tipoSangreRepository.findByIdTipoSangre(tipoSangreEntity.getIdTipoSangre());
            tipoSangre.setIdTipoSangre(tipoSangreEntity.getIdTipoSangre());
            tipoSangre.setTipoSangre(tipoSangreEntity.getTipoSangre());
            tipoSangre.setTipoMascotaEntity(tipoSangreEntity.getTipoMascotaEntity());
            tipoSangre.setAdministradorCreador(tipoSangreEntity.getAdministradorCreador());
            tipoSangreRepository.save(tipoSangre);
        }catch (IllegalArgumentException i){
            log.error(i.getMessage());
        }
    }

    public void borrar(int id){
        try{
            TipoSangreEntity tipoSangre = tipoSangreRepository.findByIdTipoSangre(id);
            tipoSangreRepository.delete(tipoSangre);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public List<TipoSangreModel> obtenerTiposSangre(){

        return convertidor.convertidorListaTipoSangre(tipoSangreRepository.findAll());

    }

    public List<TipoSangreModel> obtenerTipoSangreTipoMascota(Integer idTipoMascota){

        return convertidor.convertidorListaTipoSangre(tipoSangreRepository.findByTipoMascotaEntity_IdTipoMascota(idTipoMascota));

    }

    public TipoSangreEntity obtenerTipoSangreNombre(TipoSangreEntity tipoSangreEntity){

        return tipoSangreRepository.findByTipoSangre(tipoSangreEntity.getTipoSangre());

    }

    public Page<TipoSangreEntity> obtenerTipoSangrePaginador(Pageable pageable){
        return tipoSangreRepository.findAll(pageable);
    }

    public TipoSangreEntity obtenerTipoSangreId(TipoSangreEntity tipoSangreEntity){

        return tipoSangreRepository.findByIdTipoSangre(tipoSangreEntity.getIdTipoSangre());

    }

}
