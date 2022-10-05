package com.cruzpet.services;


import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.*;
import com.cruzpet.models.CitaModel;
import com.cruzpet.repositorys.*;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("citaService")
public class CitaService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("citaRepository")
    private CitaRepository citaRepository;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    @Autowired
    @Qualifier("clienteRepository")
    private ClienteRepository clienteRepository;

    public void crear(CitaEntity citaEntity){

        try{
            ClienteEntity clienteEntity = clienteRepository.findByCedulaCliente(citaEntity.getUsuarioCita().getCedulaCliente());

            citaEntity.setUsuarioCita(clienteEntity);

            citaRepository.save(citaEntity);

        }catch (Exception e){
            log.error(e.getMessage());

        }

    }

    public void actualizar(CitaEntity citaEntity){
        try{
            log.info(citaEntity.getUsuarioCita());
            CitaEntity cita = citaRepository.findByIdCita(citaEntity.getIdCita());
            cita.setCitaMascota(citaEntity.getCitaMascota());
            cita.setCitaVeterinario(citaEntity.getCitaVeterinario());
            cita.setEstadoCita(citaEntity.getEstadoCita());
            cita.setFecha(citaEntity.getFecha());
            cita.setHora(citaEntity.getHora());
            cita.setIpsaPropetaria(citaEntity.getIpsaPropetaria());
            ClienteEntity clienteEntity = clienteRepository.findByCedulaCliente(citaEntity.getUsuarioCita().getCedulaCliente());
            citaEntity.setUsuarioCita(clienteEntity);
            citaRepository.save(cita);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void eliminar(Integer idCita){

        try{
            CitaEntity citaEntity = citaRepository.findByIdCita(idCita);
            citaRepository.delete(citaEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }

    public CitaEntity mostrarCita(Integer idCita){
        try{
            CitaEntity citaEntity = citaRepository.findByIdCita(idCita);
            return citaEntity;
        }catch(Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public List<CitaModel> mostrarCitas(){
        return convertidor.convertidorListaCitas(citaRepository.findAll());
    }

    public List<CitaModel> mostrarCitasIpsa(IpsaEntity ipsa){

        return convertidor.convertidorListaCitas(citaRepository.findByIpsaPropetaria_Rut(ipsa.getRut()));

    }

    public List<CitaModel> mostrarCitasUsuario(ClienteEntity usuarioEntity){

        return convertidor.convertidorListaCitas(citaRepository.findByClienteCita_CedulaCliente(usuarioEntity.getCedulaCliente()));

    }

    public List<CitaModel> mostrarCitasVeterinario(VeterinarioEntity veterinario){

        return convertidor.convertidorListaCitas(citaRepository.findByCitaVeterinario_CedVeterinario(veterinario.getCedVeterinario().replace(" ", "")));

    }

    public CitaModel mostrarCitaId(CitaEntity citaEntity){

        return convertidor.convertirObjetoCitas(citaRepository.findByIdCita(citaEntity.getIdCita()));

    }

    public List<CitaModel> mostrarCitasFechaRut(CitaEntity citaEntity){

        return convertidor.convertidorListaCitas(citaRepository.findByFechaAndIpsaPropetaria_Rut(citaEntity.getFecha(), citaEntity.getIpsaPropetaria().getRut()));

    }

    public void actualizarEstadoCita(CitaEntity citaEntity){

        CitaEntity cita = citaRepository.findByIdCita(citaEntity.getIdCita());

        cita.setEstadoCita(citaEntity.getEstadoCita());

        citaRepository.save(cita);

    }


}
