package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.GeneroEntity;
import com.cruzpet.models.GenerosModel;
import com.cruzpet.repositorys.GenerosRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("generoService")
public class GeneroService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("generosRepository")
    private GenerosRepository generosRepository;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    public void crear(GeneroEntity generos){
        try{
            generosRepository.save(generos);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void actualizar(GeneroEntity generos){

        try {
            GeneroEntity genero = generosRepository.findByIdGenero(generos.getIdGenero());
            genero.setIdGenero(generos.getIdGenero());
            genero.setGenero(generos.getGenero());
            genero.setAdministradorCreador(generos.getAdministradorCreador());
            generosRepository.save(genero);
        }catch (IllegalArgumentException i){
            log.error(i.getMessage());
        }
    }

    public void borrar(int id){
        try{
            GeneroEntity raza = generosRepository.findByIdGenero(id);
            generosRepository.delete(raza);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public List<GenerosModel> obtenerGeneros(){

        return convertidor.convertidorListaGeneros(generosRepository.findAll());

    }

    public GeneroEntity obtenerGeneroNombre(GeneroEntity generoEntity){

        return generosRepository.findByGenero(generoEntity.getGenero());

    }

    public GeneroEntity obtenerGeneroId(GeneroEntity generoEntity) {

        return  generosRepository.findByIdGenero(generoEntity.getIdGenero());

    }

}
