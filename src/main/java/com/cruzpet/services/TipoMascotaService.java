package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.TipoMascotaEntity;
import com.cruzpet.entitys.TipoSangreEntity;
import com.cruzpet.models.TipoMascotaModel;
import com.cruzpet.repositorys.TipoMascotaRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tipoMascotaService")
public class TipoMascotaService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("tipoMascotaRepository")
    private TipoMascotaRepository tipoMascotaRepository;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    public void crear(TipoMascotaEntity tipoMascotaEntity){
        try{
            tipoMascotaRepository.save(tipoMascotaEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void actualizar(TipoMascotaEntity tipoMascotaEntity){

        try {
            TipoMascotaEntity tipoMascota = tipoMascotaRepository.findByIdTipoMascota(tipoMascotaEntity.getIdTipoMascota());
            tipoMascota.setIdTipoMascota(tipoMascotaEntity.getIdTipoMascota());
            tipoMascota.setTipoMascota(tipoMascotaEntity.getTipoMascota());
            tipoMascota.setAdministradorCreador(tipoMascotaEntity.getAdministradorCreador());
            tipoMascotaRepository.save(tipoMascota);
        }catch (IllegalArgumentException i){
            log.error(i.getMessage());
        }
    }

    public void borrar(int id){
        try{
            TipoMascotaEntity tipoMascota = tipoMascotaRepository.findByIdTipoMascota(id);
            tipoMascotaRepository.delete(tipoMascota);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public List<TipoMascotaModel> obtenerTipoMascota(){

        return convertidor.convertidorListaTipoMascota(tipoMascotaRepository.findAll());

    }

    public TipoMascotaEntity obtenerTipoMascotaNombre(TipoMascotaEntity tipoMascotaEntity){

        return tipoMascotaRepository.findByTipoMascota(tipoMascotaEntity.getTipoMascota());

    }

    public Page<TipoMascotaEntity> obtenerTipoMascotaPaginador(Pageable pageable){
        return tipoMascotaRepository.findAll(pageable);
    }

    public TipoMascotaEntity obtenerTipoMascotaId(TipoMascotaEntity tipoMascotaEntity){

        return tipoMascotaRepository.findByIdTipoMascota(tipoMascotaEntity.getIdTipoMascota());

    }

}
