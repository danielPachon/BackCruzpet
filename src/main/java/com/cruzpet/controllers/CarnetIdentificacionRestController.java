package com.cruzpet.controllers;

import com.cruzpet.entitys.BarrioEntity;
import com.cruzpet.entitys.CarnetIdentificacionEntity;
import com.cruzpet.services.CarnetIdentificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/v1.0/carnetidentificacion")
public class CarnetIdentificacionRestController {

    @Autowired
    private CarnetIdentificacionService carnetIdentificacionService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearCarnetIdentificacion(@RequestBody CarnetIdentificacionEntity carnetIdentificacionEntity){

        try{

            carnetIdentificacionService.crearCarnetIdentificacion(carnetIdentificacionEntity);
            return new ResponseEntity("Se creo correctamente", HttpStatus.CREATED);

        }catch (Exception ex){

            return new ResponseEntity("No se pudo crear", HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping("/traercarnetidentificacionid")
    public ResponseEntity<CarnetIdentificacionEntity> traerCarnetIdentificacionId(@RequestBody CarnetIdentificacionEntity carnetIdentificacionEntity){

        try{

            return new ResponseEntity(carnetIdentificacionService.traerCarnetIdentificacionMascota(carnetIdentificacionEntity), HttpStatus.CREATED);

        }catch (Exception ex){

            return new ResponseEntity("No se pudo crear", HttpStatus.BAD_REQUEST);

        }

    }

}
