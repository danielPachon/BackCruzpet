package com.cruzpet.controllers;

import com.cruzpet.entitys.VeterinarioDisponibilidadesEntity;
import com.cruzpet.models.VeterinarioDisponibilidadesModel;
import com.cruzpet.services.VeterinarioDisponibilidadesService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/v1.0/veterinariosdisponibilidades")
public class VeterinarioDisponibilidadesRestController {

    Log logger = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("veterinarioDisponibilidadesService")
    private VeterinarioDisponibilidadesService veterinarioDisponibilidadesService;

    @PostMapping("/crear")
    public ResponseEntity<?> agregarVeterinario(@RequestBody VeterinarioDisponibilidadesEntity veterinarioDisponibilidades){

        try {
            veterinarioDisponibilidadesService.crearRelacionVeterinarioDisponibilidad(veterinarioDisponibilidades);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity("La relacion se realizo correctamente", HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<VeterinarioDisponibilidadesModel>> traer(){

        return ResponseEntity.status(HttpStatus.OK).body(veterinarioDisponibilidadesService.traer());

    }




}
