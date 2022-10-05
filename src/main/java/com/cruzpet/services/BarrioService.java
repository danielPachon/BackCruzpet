package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.BarrioEntity;
import com.cruzpet.entitys.CiudadEntity;
import com.cruzpet.models.BarrioModel;
import com.cruzpet.repositorys.BarrioRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("barrioService")
public class BarrioService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("barrioRepository")
    private BarrioRepository barrioRepository;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    public BarrioEntity crear(BarrioEntity barrioEntity){

        try{
            barrioRepository.save(barrioEntity);
            return barrioEntity;
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }

    }

    public void actualizar(BarrioEntity barrioEntity){
        try{
            BarrioEntity barrio = barrioRepository.findByIdBarrio(barrioEntity.getIdBarrio());
            barrio.setNombreBarrio(barrioEntity.getNombreBarrio());
            barrio.setCiudadOrigen(barrioEntity.getCiudadOrigen());
            barrio.setCodigoPostal(barrioEntity.getCodigoPostal());
            barrio.setAdministradorCreador(barrioEntity.getAdministradorCreador());
            barrioRepository.save(barrio);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void eliminar(Integer idBarrio){

        try{
            BarrioEntity barrioEntity = barrioRepository.findByIdBarrio(idBarrio);
            barrioRepository.delete(barrioEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }

    public List<BarrioModel> mostrarBarrios(){
        return convertidor.convertidorListaBarrios(barrioRepository.findAll());
    }

    public BarrioModel mostrarBarrioCiudad(BarrioEntity barrioEntity){

        return convertidor.convertidorObjetoBarrios(barrioRepository.findByCiudadOrigen_IdCiudadAndNombreBarrio(barrioEntity.getCiudadOrigen().getIdCiudad(), barrioEntity.getNombreBarrio()));

    }

    public BarrioEntity taerBarrioId(BarrioEntity barrioEntity){
        return barrioRepository.findByIdBarrio(barrioEntity.getIdBarrio());
    }

    public Page<BarrioEntity> mostrarBarriosPaginador(Pageable pageable){
        return barrioRepository.findAll(pageable);
    }

    public List<BarrioEntity> mostrarBarrioPalabra(String palabraBarrio){

        int espacios = 0;

        int indice = 0;

        for(int i = 0; i<palabraBarrio.length(); i++ ){

            if(palabraBarrio.charAt(i) == ' '){

                indice=i-1;
                espacios++;
                break;

            }

        }

        String primeraPalabra = palabraBarrio.substring(0, indice+1);

        List<BarrioEntity> barrios = barrioRepository.findAll();

        List<BarrioEntity> barriosMostrar = new ArrayList<>();

        if(espacios > 0){

            for(BarrioEntity barrio: barrios){

                String palabraClave = String.valueOf(barrio.getNombreBarrio().charAt(0));

                for(int i =1; i<barrio.getNombreBarrio().length(); i++){

                    if(barrio.getNombreBarrio().charAt(i) != ' '){

                        palabraClave += barrio.getNombreBarrio().charAt(i);
                        if(palabraClave.toLowerCase().equals(palabraBarrio.toLowerCase())){

                            if(!barriosMostrar.contains(barrio)){
                                barriosMostrar.add(barrio);
                                palabraClave = "";

                            }

                        }

                    }else{

                        if(palabraClave.toLowerCase().equals(primeraPalabra.toLowerCase())){


                            for(int a = indice+2; a<palabraBarrio.length(); a++){

                                if (palabraBarrio.charAt(a) == ' ' || palabraBarrio.charAt(a) == palabraBarrio.charAt(palabraBarrio.length()-1)){

                                    primeraPalabra += " " + palabraBarrio.substring(indice+2, a+1);
                                    palabraClave += " ";
                                    break;
                                }
                            }

                        }else{

                            palabraClave = "";

                        }

                    }

                }

                for(int j = 0; j<palabraBarrio.length(); j++ ){

                    if(palabraBarrio.charAt(j) == ' '){

                        indice=j-1;
                        espacios++;
                        break;

                    }

                }

                primeraPalabra = palabraBarrio.substring(0, indice+1);

            }


        }else{

            for (BarrioEntity barrio: barrios){

                String palabraClave = "";

                for(int i = 0; i < barrio.getNombreBarrio().length(); i++){

                    if(barrio.getNombreBarrio().charAt(i) != ' '){

                        palabraClave += barrio.getNombreBarrio().charAt(i);
                        if(palabraClave.toLowerCase().equals(palabraBarrio.toLowerCase())){

                            if(!barriosMostrar.contains(barrio)){
                                barriosMostrar.add(barrio);
                                palabraClave = "";
                            }

                        }

                    }else{

                        palabraClave = "";

                    }


                }

            }

        }


        return barriosMostrar;

    }

}
