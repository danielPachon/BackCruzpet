package com.cruzpet.controllers;

import com.cruzpet.entitys.VacunaEntity;
import com.cruzpet.models.VacunasModel;
import com.cruzpet.services.VacunasService;
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
@RequestMapping("/v1.0/vacunas")
public class VacunasRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("vacunasService")
    private VacunasService vacunasService;

    @PostMapping("/crear")
    public ResponseEntity<String> agregarVacuna(@RequestBody VacunaEntity vacunaEntity){
        try{
            vacunasService.crear(vacunaEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("La vacuna fue registrada con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al registrar la vacuna");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarVacuna(@RequestBody VacunaEntity vacunaEntity){
        try{
            vacunasService.actualizar(vacunaEntity);
            return ResponseEntity.status(HttpStatus.OK).body("La vacuna fue actualizada con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al actualizar la vacuna");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarVacuna(@PathVariable("id") Integer idVacuna){
        try{
            vacunasService.borrar(idVacuna);
            return ResponseEntity.status(HttpStatus.OK).body("La vacuna fue borrada con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al borrar la vacuna");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<VacunasModel>> obtenerVacunas(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(vacunasService.obtenerVacunas());
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las vacunas", HttpStatus.BAD_REQUEST);
        }
    }

}
