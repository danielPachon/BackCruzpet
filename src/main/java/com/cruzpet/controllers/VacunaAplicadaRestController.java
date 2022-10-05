package com.cruzpet.controllers;

import com.cruzpet.entitys.BeneficiosEntity;
import com.cruzpet.entitys.VacunaAplicadaEntity;
import com.cruzpet.services.BeneficiosService;
import com.cruzpet.services.VacunaAplicadaServices;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/v1.0/vacunaaplicada")
public class VacunaAplicadaRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    private VacunaAplicadaServices vacunaAplicadaServices;


    @PostMapping("/crear")
    public ResponseEntity<VacunaAplicadaEntity> agregarVacunaAplicada(@RequestBody VacunaAplicadaEntity vacunaAplicadaEntity){

        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(vacunaAplicadaServices.crear(vacunaAplicadaEntity));

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo crear la vacuna aplicada", HttpStatus.BAD_REQUEST);

        }

    }

}
