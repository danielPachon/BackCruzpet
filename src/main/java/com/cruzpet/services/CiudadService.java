package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.CiudadEntity;
import com.cruzpet.entitys.DepartamentoEntity;
import com.cruzpet.entitys.DireccionEntity;
import com.cruzpet.models.CiudadModel;
import com.cruzpet.repositorys.CiudadRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("ciudadService")
public class CiudadService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("ciudadRepository")
    private CiudadRepository ciudadRepository;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    public boolean crear(CiudadEntity ciudadEntity){

        try{
            ciudadRepository.save(ciudadEntity);
            return  true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }

    }

    public void actualizar(CiudadEntity ciudadEntity){
        try{
            CiudadEntity ciudades = ciudadRepository.findByIdCiudad(ciudadEntity.getIdCiudad());
            ciudades.setNombreCiudad(ciudadEntity.getNombreCiudad());
            ciudades.setDepartamentoOrigen(ciudadEntity.getDepartamentoOrigen());
            ciudades.setAdministradorCreador(ciudadEntity.getAdministradorCreador());
            ciudadRepository.save(ciudades);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void eliminar(Integer idCiudad){

        try{
            CiudadEntity ciudadEntity = ciudadRepository.findByIdCiudad(idCiudad);
            ciudadRepository.delete(ciudadEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }

    public List<CiudadModel> mostrarCiudades(){
        return convertidor.convertidorListaCiudad(ciudadRepository.findAll());
    }

    public List<CiudadModel> mostrarCiudadesDepartamento(Integer idDepartamento){

        return convertidor.convertidorListaCiudad(ciudadRepository.findByDepartamentoOrigen_IdDepartamento(idDepartamento));

    }

    public CiudadModel mostrarCiudadDepartamentoNombre(String nombre, Integer idDepartamento){

        CiudadEntity lista = ciudadRepository.findByDepartamentoOrigen_IdDepartamentoAndNombreCiudad(idDepartamento, nombre);
        log.info(lista.getNombreCiudad());
        return convertidor.convertidorObjetoCiudad(lista);

    }

    public List<CiudadEntity> mostrarCiudadesDepartamento(String nombreDepartamento){

        return ciudadRepository.findByDepartamentoOrigen_NombreDepartamento(nombreDepartamento);

    }

    public CiudadEntity traerCiudadId(CiudadEntity ciudadEntity){

        return ciudadRepository.findByIdCiudad(ciudadEntity.getIdCiudad());

    }

    public Page<CiudadEntity> obtenerCiudadesPaginador(Pageable pageable){
        return ciudadRepository.findAll(pageable);
    }

    public List<CiudadEntity> mostrarCiudadPalabra(String palabraCiudad){

        int espacios = 0;

        int indice = 0;

        for(int i = 0; i<palabraCiudad.length(); i++ ){

            if(palabraCiudad.charAt(i) == ' '){

                indice=i-1;
                espacios++;
                break;

            }

        }

        String primeraPalabra = palabraCiudad.substring(0, indice+1);

        List<CiudadEntity> ciudades = ciudadRepository.findAll();

        List<CiudadEntity> ciudadesMostrar = new ArrayList<>();

        if(espacios > 0){

            for(CiudadEntity ciudad: ciudades){

                String palabraClave = String.valueOf(ciudad.getNombreCiudad().charAt(0));

                for(int i =1; i<ciudad.getNombreCiudad().length(); i++){

                    if(ciudad.getNombreCiudad().charAt(i) != ' '){

                        palabraClave += ciudad.getNombreCiudad().charAt(i);
                        if(palabraClave.toLowerCase().equals(palabraCiudad.toLowerCase())){

                            if(!ciudadesMostrar.contains(ciudad)){
                                ciudadesMostrar.add(ciudad);
                                palabraClave = "";

                            }

                        }

                    }else{

                        if(palabraClave.toLowerCase().equals(primeraPalabra.toLowerCase())){


                            for(int a = indice+2; a<palabraCiudad.length(); a++){

                                if (palabraCiudad.charAt(a) == ' ' || palabraCiudad.charAt(a) == palabraCiudad.charAt(palabraCiudad.length()-1)){

                                    primeraPalabra += " " + palabraCiudad.substring(indice+2, a+1);
                                    palabraClave += " ";
                                    break;
                                }
                            }

                        }else{

                            palabraClave = "";

                        }

                    }

                }

                for(int j = 0; j<palabraCiudad.length(); j++ ){

                    if(palabraCiudad.charAt(j) == ' '){

                        indice=j-1;
                        espacios++;
                        break;

                    }

                }

                primeraPalabra = palabraCiudad.substring(0, indice+1);

            }


        }else{

            for (CiudadEntity ciudad: ciudades){

                String palabraClave = "";

                for(int i = 0; i < ciudad.getNombreCiudad().length(); i++){

                    if(ciudad.getNombreCiudad().charAt(i) != ' '){

                        palabraClave += ciudad.getNombreCiudad().charAt(i);
                        if(palabraClave.toLowerCase().equals(palabraCiudad.toLowerCase())){

                            if(!ciudadesMostrar.contains(ciudad)){
                                ciudadesMostrar.add(ciudad);
                                palabraClave = "";
                            }

                        }

                    }else{

                        palabraClave = "";

                    }


                }

            }

        }


        return ciudadesMostrar;

    }

}
