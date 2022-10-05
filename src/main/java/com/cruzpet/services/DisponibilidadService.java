package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.*;
import com.cruzpet.models.BarrioModel;
import com.cruzpet.models.DisponibilidadModel;
import com.cruzpet.models.VeterinarioDisponibilidadesModel;
import com.cruzpet.repositorys.DisponibilidadRepository;
import com.cruzpet.repositorys.IntervalosRepository;
import com.cruzpet.repositorys.VeterinarioDisponibilidadesRepository;
import com.cruzpet.repositorys.VeterinarioRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service("disponibilidadService")
public class DisponibilidadService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    @Autowired
    @Qualifier("disponibilidadRepository")
    private DisponibilidadRepository disponibilidadRepository;

    @Autowired
    @Qualifier("intervalosRepository")
    private IntervalosRepository intervalosRepository;

    @Autowired
    @Qualifier("veterinarioRepository")
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    @Qualifier("veterinarioDisponibilidadesRepository")
    private VeterinarioDisponibilidadesRepository veterinarioDisponibilidadesRepository;

    public void crear(DisponibilidadEntity disponibilidadEntity){

        try{

            disponibilidadRepository.save(disponibilidadEntity);

            List<LocalTime> listaTiempos = new ArrayList<>();

            LocalTime localTime = disponibilidadEntity.getHoraEntrada();

            while (true){

                localTime = localTime.plusMinutes(45);
                if(localTime.isBefore(disponibilidadEntity.getHoraSalida())){

                    listaTiempos.add(localTime);

                }else{
                    break;
                }

            }

            for (LocalTime localTime1 : listaTiempos){

                intervalosRepository.save(new IntervalosEntity(localTime1, "a",disponibilidadEntity));

            }

        }catch (Exception e){
            log.error(e.getMessage());
        }

    }

    public void intervaloSeleccionado(Integer idIntervalo){

        IntervalosEntity intervalo = intervalosRepository.findByIdIntervalo(idIntervalo);

        intervalo.setEstadoIntervalo("i");

        intervalosRepository.save(intervalo);

    }

    public void actualizar(DisponibilidadEntity disponibilidadEntity){
        try{
            DisponibilidadEntity disponibilidad = disponibilidadRepository.findByIdDisponibilidad(disponibilidadEntity.getIdDisponibilidad());
            disponibilidad.setMesDisponibilidad(disponibilidadEntity.getMesDisponibilidad());
            disponibilidad.setDiaDisponibilidad(disponibilidadEntity.getDiaDisponibilidad());
            disponibilidad.setYearDisponibilidad(disponibilidadEntity.getYearDisponibilidad());
            disponibilidad.setHoraEntrada(disponibilidadEntity.getHoraEntrada());
            disponibilidad.setHoraSalida(disponibilidadEntity.getHoraSalida());
            disponibilidad.setEstado(disponibilidadEntity.getEstado());
            disponibilidadRepository.save(disponibilidadEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void eliminar(Integer idDisponibilidad){

        try{
            DisponibilidadEntity disponibilidad = disponibilidadRepository.findByIdDisponibilidad(idDisponibilidad);

            for(int i = 0; i < disponibilidad.getIntervalos().size(); i++){

                IntervalosEntity intervalo = intervalosRepository.findByIdIntervalo(disponibilidad.getIntervalos().get(i).getIdIntervalo());

                intervalosRepository.delete(intervalo);

            }

            disponibilidadRepository.delete(disponibilidad);
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }

    public List<DisponibilidadModel> mostrarDisponibilidades(){

        return convertidor.convertirListaDisponibilidades(disponibilidadRepository.findAll());
    }

    public  List<DisponibilidadModel> mostrarDisponibilidadesIpsa(Integer rut){

        return convertidor.convertirListaDisponibilidades(disponibilidadRepository.findByIpsafk_Rut(rut));

    }

    public List<DisponibilidadModel> mostrarDisponibilidadesDia(String dia){

        return convertidor.convertirListaDisponibilidades(disponibilidadRepository.findByDiaDisponibilidad(dia));

    }

    public List<DisponibilidadModel> mostrarDisponibilidadesMes(String mes){

        return convertidor.convertirListaDisponibilidades(disponibilidadRepository.findByMesDisponibilidad(mes));

    }

    public List<DisponibilidadModel> mostrarDisponibilidadesYear(String year){

        return convertidor.convertirListaDisponibilidades(disponibilidadRepository.findByYearDisponibilidad(year));

    }

    public List<DisponibilidadModel> mostrarDisponibilidadesDiaMes(String dia, String mes){

        return convertidor.convertirListaDisponibilidades(disponibilidadRepository.findByDiaDisponibilidadAndAndMesDisponibilidad(dia,mes));

    }

    public List<DisponibilidadModel> mostrarDisponibilidadDiaMesYear(String dia, String mes, String year){

        return convertidor.convertirListaDisponibilidadessinintervalosyveterinario(disponibilidadRepository.findByDiaDisponibilidadAndMesDisponibilidadAndYearDisponibilidad(dia, mes, year));

    }



    public List<VeterinarioDisponibilidadesModel> mostrarDisponibilidadDiaMesYearRut(String dia, String mes, String year, Integer rut) {

        return convertidor.convertirListaDisponibilidadessinintervalos(veterinarioDisponibilidadesRepository.findByDisponibilidadEntity_DiaDisponibilidadAndDisponibilidadEntity_MesDisponibilidadAndDisponibilidadEntity_Ipsafk_RutAndDisponibilidadEntity_YearDisponibilidad(dia, mes,rut, year));

    }


    public List<VeterinarioDisponibilidadesModel> mostrarDisponibilidadVeterinario(String dia, String mes, String year, Integer rut, String cedulaVeterinario){

        List<VeterinarioDisponibilidadesEntity> veterinarioDisponibilidadesEntities = veterinarioDisponibilidadesRepository.findByDisponibilidadEntity_DiaDisponibilidadAndDisponibilidadEntity_MesDisponibilidadAndDisponibilidadEntity_Ipsafk_RutAndDisponibilidadEntity_YearDisponibilidadAndVeterinarioEntity_CedVeterinario(dia, mes, rut, year, cedulaVeterinario);

        return convertidor.convertirListaDisponibilidadesconintervalos(veterinarioDisponibilidadesEntities);

    }

    public void actualizarEstadoDisponibilidad(DisponibilidadEntity disponibilidadEntity){

        DisponibilidadEntity disponibilidad = disponibilidadRepository.findByIdDisponibilidad(disponibilidadEntity.getIdDisponibilidad());

        disponibilidad.setEstado(disponibilidadEntity.getEstado());

        disponibilidadRepository.save(disponibilidad);

    }

    public void actualizarEstadoIntervalo(IntervalosEntity intervalosEntity){

            IntervalosEntity intervalo = intervalosRepository.findByIdIntervalo(intervalosEntity.getIdIntervalo());

            intervalo.setEstadoIntervalo(intervalosEntity.getEstadoIntervalo());

            intervalosRepository.save(intervalo);
    }

    public List<IntervalosEntity> traerIntervalos(){

        return intervalosRepository.findAll();

    }
}
