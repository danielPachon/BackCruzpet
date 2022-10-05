package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.VeterinarioDisponibilidadesEntity;
import com.cruzpet.models.VeterinarioDisponibilidadesModel;
import com.cruzpet.repositorys.VeterinarioDisponibilidadesRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("veterinarioDisponibilidadesService")
public class VeterinarioDisponibilidadesService {

    Log logger = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("veterinarioDisponibilidadesRepository")
    private VeterinarioDisponibilidadesRepository veterinarioDisponibilidadesRepository;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    public void crearRelacionVeterinarioDisponibilidad(VeterinarioDisponibilidadesEntity veterinarioDisponibilidades){

        veterinarioDisponibilidadesRepository.save(veterinarioDisponibilidades);

    }

    public List<VeterinarioDisponibilidadesModel> traer(){

        return convertidor.convertirListaVeterinarioDisponibilidades(veterinarioDisponibilidadesRepository.findAll());

    }



}
