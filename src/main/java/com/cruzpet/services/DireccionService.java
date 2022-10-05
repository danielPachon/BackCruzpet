package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.BarrioEntity;
import com.cruzpet.entitys.DepartamentoEntity;
import com.cruzpet.entitys.DireccionEntity;
import com.cruzpet.models.BarrioModel;
import com.cruzpet.models.DireccionModel;
import com.cruzpet.repositorys.DireccionRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service("direccionService")
public class DireccionService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("direccionRepository")
    private DireccionRepository direccionRepository;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    public DireccionEntity crear(DireccionEntity direccionEntity){

        try{
            direccionRepository.save(direccionEntity);
            return direccionEntity;
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }

    }

    public void actualizar(DireccionEntity direccionEntity){
        try{
            DireccionEntity direccion = direccionRepository.findByIdDireccion(direccionEntity.getIdDireccion());
            direccion.setDireccionCasa(direccionEntity.getDireccionCasa());
            direccion.setBarrios(direccionEntity.getBarrios());
            direccion.setAdministradorCreador(direccionEntity.getAdministradorCreador());
            direccionRepository.save(direccion);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void eliminar(Integer idDireccion){

        try{
            DireccionEntity direccionEntity = direccionRepository.findByIdDireccion(idDireccion);
            direccionRepository.delete(direccionEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }

    public List<DireccionModel> mostrarDirecciones(){
        return convertidor.convertidorListaDireccion(direccionRepository.findAll());
    }

    public DireccionEntity mostrarDireccion(@RequestBody DireccionEntity direccionEntity) {

        return direccionRepository.findByDireccionCasaAndBarrios_IdBarrio(direccionEntity.getDireccionCasa(), direccionEntity.getBarrios().getIdBarrio());

    }

    public DireccionEntity mostrarDireccionId(@RequestBody DireccionEntity direccionEntity){

        return direccionRepository.findByIdDireccion(direccionEntity.getIdDireccion());

    }

    public Page<DireccionEntity> obtenerDirreccionesPaginador(Pageable pageable){
        return direccionRepository.findAll(pageable);
    }

    public List<DireccionEntity> mostrarDireccionesPalabra(String palabraDireccion){

        int espacios = 0;

        int indice = 0;

        for(int i = 0; i<palabraDireccion.length(); i++ ){

            if(palabraDireccion.charAt(i) == ' '){

                indice=i-1;
                espacios++;
                break;

            }

        }

        String primeraPalabra = palabraDireccion.substring(0, indice+1);

        List<DireccionEntity> direcciones = direccionRepository.findAll();

        List<DireccionEntity> direccionesMostrar = new ArrayList<>();

        if(espacios > 0){

            for(DireccionEntity direccion: direcciones){

                String palabraClave = String.valueOf(direccion.getDireccionCasa().charAt(0));

                for(int i =1; i<direccion.getDireccionCasa().length(); i++){

                    if(direccion.getDireccionCasa().charAt(i) != ' '){

                        palabraClave += direccion.getDireccionCasa().charAt(i);
                        if(palabraClave.toLowerCase().equals(palabraDireccion.toLowerCase())){

                            if(!direccionesMostrar.contains(direccion)){
                                direccionesMostrar.add(direccion);
                                palabraClave = "";

                            }

                        }

                    }else{

                        if(palabraClave.toLowerCase().equals(primeraPalabra.toLowerCase())){


                            for(int a = indice+2; a<palabraDireccion.length(); a++){

                                if (palabraDireccion.charAt(a) == ' ' || palabraDireccion.charAt(a) == palabraDireccion.charAt(palabraDireccion.length()-1)){

                                    primeraPalabra += " " + palabraDireccion.substring(indice+2, a+1);
                                    palabraClave += " ";
                                    break;
                                }
                            }

                        }else{

                            palabraClave = "";

                        }

                    }

                }

                for(int j = 0; j<palabraDireccion.length(); j++ ){

                    if(palabraDireccion.charAt(j) == ' '){

                        indice=j-1;
                        espacios++;
                        break;

                    }

                }

                primeraPalabra = palabraDireccion.substring(0, indice+1);

            }


        }else{

            for (DireccionEntity direccion: direcciones){

                String palabraClave = "";

                for(int i = 0; i < direccion.getDireccionCasa().length(); i++){

                    if(direccion.getDireccionCasa().charAt(i) != ' '){

                        palabraClave += direccion.getDireccionCasa().charAt(i);
                        if(palabraClave.toLowerCase().equals(palabraDireccion.toLowerCase())){

                            if(!direccionesMostrar.contains(direccion)){
                                direccionesMostrar.add(direccion);
                                palabraClave = "";
                            }

                        }

                    }else{

                        palabraClave = "";

                    }


                }

            }

        }


        return direccionesMostrar;

    }

}
