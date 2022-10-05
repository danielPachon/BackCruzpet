package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.*;
import com.cruzpet.models.MascotaModel;
import com.cruzpet.repositorys.*;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service("mascotaService")
public class MascotaService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("mascotaRepository")
    private MascotaRepository mascotaRepository;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    public void crear(MascotaEntity mascotaEntity){

        Random random = new Random();

        List<MascotaEntity> mascotaEntities = mascotaRepository.findAll();

        String cadena;

        int repetido = 0;

        try{
            while(true){
                cadena = "";
                for(int i = 0; i<10; i++){

                    cadena += (random.nextInt(10));

                }

                for(MascotaEntity mascota:mascotaEntities){
                    if(cadena.toUpperCase().equals(mascota.getNumeroIdentidad())){
                        repetido++;
                        break;
                    }
                }

                if(repetido == 0){
                    break;
                }

            }

            mascotaEntity.setNumeroIdentidad(cadena);
            mascotaEntity.setNumeroIdentidad(mascotaEntity.getNumeroIdentidad().replace( " ", ""));
            mascotaRepository.save(mascotaEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void actualizar(MascotaEntity mascotaEntity)  {


        try {
            MascotaEntity mascota = mascotaRepository.findByNumeroIdentidad(mascotaEntity.getNumeroIdentidad());

            mascota.setEdad(mascotaEntity.getEdad());
            mascota.setNombreMascota(mascotaEntity.getNombreMascota());
            mascota.setFechaNacimiento(mascotaEntity.getFechaNacimiento());
            mascota.setEstado(mascotaEntity.getEstado());
            mascota.setImagenMascota(mascotaEntity.getImagenMascota());
            mascota.setRazaMascota(mascotaEntity.getRazaMascota());
            mascota.setTipoMascota(mascotaEntity.getTipoMascota());
            mascota.setGeneroMascota(mascotaEntity.getGeneroMascota());
            mascota.setTipoSangreMascota(mascotaEntity.getTipoSangreMascota());
            mascota.setAdministradorCreador(mascotaEntity.getAdministradorCreador());
            mascotaRepository.save(mascota);

        }catch (Exception i) {
            log.error(i.getMessage());
        }
    }

    public void borrar(String numeroIdentidad) {

        try{
            MascotaEntity mascota = mascotaRepository.findByNumeroIdentidad(numeroIdentidad);
            mascotaRepository.delete(mascota);

        }catch (Exception e){
            log.error(e.getMessage());
        }
    }


    public List<MascotaModel> obtenerMascotas(){

        return convertidor.convertidorListaMascotas(mascotaRepository.findAll());

    }

    public MascotaModel mostrarMascota(String idMascota){
        try{
            MascotaEntity mascota = mascotaRepository.findByNumeroIdentidad(idMascota);
            return convertidor.convertirObjetoMascota(mascota);
        }catch(Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public List<MascotaModel> obtenerMascotaUsuario(String cedula){

        return convertidor.convertidorListaMascotas(mascotaRepository.findByClienteMascota_CedulaCliente(cedula));

    }

    public boolean existenciaMascota(String numeroIdentidad){
        return mascotaRepository.existsByNumeroIdentidad(numeroIdentidad);
    }

    public Page<MascotaEntity> obtenerMascotassPaginador(Pageable pageable){
        return mascotaRepository.findAll(pageable);
    }

    public List<MascotaEntity> mostrarMascotasCliente(String palabraMascota){

        int espacios = 0;

        int indice = 0;

        for(int i = 0; i<palabraMascota.length(); i++ ){

            if(palabraMascota.charAt(i) == ' '){

                indice=i-1;
                espacios++;
                break;

            }

        }

        String primeraPalabra = palabraMascota.substring(0, indice+1);

        List<MascotaEntity> mascotas = mascotaRepository.findAll();

        List<MascotaEntity> mascotasMostrar = new ArrayList<>();

        if(espacios > 0){

            for(MascotaEntity mascota: mascotas){

                String palabraClave = String.valueOf(mascota.getClienteMascota().getCedulaCliente().charAt(0));

                for(int i =1; i<mascota.getClienteMascota().getCedulaCliente().length(); i++){

                    if(mascota.getClienteMascota().getCedulaCliente().charAt(i) != ' '){

                        palabraClave += mascota.getClienteMascota().getCedulaCliente().charAt(i);
                        if(palabraClave.toLowerCase().equals(palabraMascota.toLowerCase())){

                            if(!mascotasMostrar.contains(mascota)){
                                mascotasMostrar.add(mascota);
                                palabraClave = "";

                            }

                        }

                    }else{

                        if(palabraClave.toLowerCase().equals(primeraPalabra.toLowerCase())){


                            for(int a = indice+2; a<palabraMascota.length(); a++){

                                if (palabraMascota.charAt(a) == ' ' || palabraMascota.charAt(a) == palabraMascota.charAt(palabraMascota.length()-1)){

                                    primeraPalabra += " " + palabraMascota.substring(indice+2, a+1);
                                    palabraClave += " ";
                                    break;
                                }
                            }

                        }else{

                            palabraClave = "";

                        }

                    }

                }

                for(int j = 0; j<palabraMascota.length(); j++ ){

                    if(palabraMascota.charAt(j) == ' '){

                        indice=j-1;
                        espacios++;
                        break;

                    }

                }

                primeraPalabra = palabraMascota.substring(0, indice+1);

            }


        }else{

            for (MascotaEntity mascota: mascotas){

                String palabraClave = "";

                for(int i = 0; i < mascota.getClienteMascota().getCedulaCliente().length(); i++){

                    if(mascota.getClienteMascota().getCedulaCliente().charAt(i) != ' '){

                        palabraClave += mascota.getClienteMascota().getCedulaCliente().charAt(i);
                        if(palabraClave.toLowerCase().equals(palabraMascota.toLowerCase())){

                            if(!mascotasMostrar.contains(mascota)){
                                mascotasMostrar.add(mascota);
                                palabraClave = "";
                            }

                        }

                    }else{

                        palabraClave = "";

                    }


                }

            }

        }


        return mascotasMostrar;

    }

    public Page<MascotaEntity> obtenerMascotassPaginadorCliente(String cedula,Pageable pageable){
        return mascotaRepository.findAllByClienteMascota_CedulaCliente(cedula,pageable);
    }
}
