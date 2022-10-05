package com.cruzpet.controllers;

import com.cruzpet.entitys.VacunaDetalleEntity;
import com.cruzpet.models.VacunaDetalleModel;
import com.cruzpet.services.VacunaDetalleService;
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
@RequestMapping("/v1.0/vacunas_detalles")
public class VaunasDetallesRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("vacunaDetalleService")
    private VacunaDetalleService vacunaDetalleService;

    @PostMapping("/crear")
    public ResponseEntity<String> agregarVacunaDetalle(@RequestBody VacunaDetalleEntity vacunaDetalleEntity){
        try{
            vacunaDetalleService.crear(vacunaDetalleEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Los detalles de la vacuna fue registrada con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al registrar los detalles de la vacuna");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarVacunaDetalle(@RequestBody VacunaDetalleEntity vacunaDetalleEntity){
        try{
            vacunaDetalleService.actualizar(vacunaDetalleEntity);
            return ResponseEntity.status(HttpStatus.OK).body("Los detalles de la vacuna fueron actualizadasa con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al actualizar los detalles de la vacuna");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarVacunaDetalle(@PathVariable("id") Integer idVacunaDetalle){
        try{
            vacunaDetalleService.borrar(idVacunaDetalle);
            return ResponseEntity.status(HttpStatus.OK).body("Los detalles de la vacuna fueron borradas con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al borrar los detalles de la vacuna");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<VacunaDetalleModel>> obtenerVacunasDetalles(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(vacunaDetalleService.obtenerVacunasDetalles());
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer los detalles de la vacuna", HttpStatus.BAD_REQUEST);
        }
    }

}
