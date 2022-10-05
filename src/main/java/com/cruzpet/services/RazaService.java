package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.ProductosEntity;
import com.cruzpet.entitys.RazaEntity;
import com.cruzpet.models.ProductosModel;
import com.cruzpet.models.RazaModel;
import com.cruzpet.repositorys.ProductosRepository;
import com.cruzpet.repositorys.RazaRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("razaService")
public  class RazaService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("razaRepository")
    private RazaRepository razaRepository;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    public void crear(RazaEntity raza){
        try{
            razaRepository.save(raza);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void actualizar(RazaEntity raza){

        try {
            razaRepository.save(raza);
        }catch (IllegalArgumentException i){
            log.error(i.getMessage());
        }
    }

    public void borrar(int id){
        try{
            RazaEntity raza = razaRepository.findByIdRaza(id);
            razaRepository.delete(raza);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public List<RazaModel> obtenerRazas(){

        return convertidor.convertirListaRaza(razaRepository.findAll());

    }

    public RazaEntity buscarRazaNombre(RazaEntity razaEntity) {
        return razaRepository.findByNombreRaza(razaEntity.getNombreRaza());
    }

    public List<RazaEntity> buscarRazsaTipoMascota(RazaEntity razaEntity){

        return razaRepository.findByTipoMascotaEntity_IdTipoMascota(razaEntity.getTipoMascotaEntity().getIdTipoMascota());

    }

    public Page<RazaEntity> obtenerRazasPaginador(Pageable pageable){
        return razaRepository.findAll(pageable);
    }

    public RazaEntity obtenerRazaId(RazaEntity razaEntity){

        return razaRepository.findByIdRaza(razaEntity.getIdRaza());

    }

}