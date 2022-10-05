package com.cruzpet.services;

import com.cruzpet.entitys.VacunaAplicadaEntity;
import com.cruzpet.entitys.VacunaEntity;
import com.cruzpet.repositorys.VacunaAplicadaRepository;
import com.cruzpet.repositorys.VacunasRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("vacunaaplicadaservice")
public class VacunaAplicadaServices {

    Log log = LogFactory.getLog(getClass());
    @Autowired
    private VacunaAplicadaRepository vacunaAplicadaRepository;

    @Autowired
    private VacunasRepository vacunasRepository;

    public VacunaAplicadaEntity crear(VacunaAplicadaEntity vacunaAplicadaEntity){

        try{

            vacunaAplicadaRepository.save(vacunaAplicadaEntity);

            return vacunaAplicadaRepository.save(vacunaAplicadaEntity);
        }catch (Exception ex){

            log.info(ex.getMessage());
            return null;
        }

    }

}
