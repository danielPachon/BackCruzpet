package com.cruzpet.controllers;

import com.cruzpet.entitys.BarrioEntity;
import com.cruzpet.entitys.CalificacionEntity;
import com.cruzpet.entitys.ClienteEntity;
import com.cruzpet.models.BarrioModel;
import com.cruzpet.models.CalificacionesModel;
import com.cruzpet.services.CalificacionesService;
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
@RequestMapping("/v1.0/calificaciones")
public class CalificacionesRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("claificacionesservice")
    private CalificacionesService calificacionesService;

    @PostMapping("/crear")
    public ResponseEntity<String> agregarBarrio(@RequestBody CalificacionEntity calificacionEntity){

        try{
            calificacionesService.crear(calificacionEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("La calificacion fue creada con exito");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hubo un error en la creacion");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarCalificacion(@RequestBody CalificacionEntity calificacionEntity){

        try{

            calificacionesService.actualizar(calificacionEntity);
            return ResponseEntity.status(HttpStatus.OK).body("La calificacion fue actualizada con exito");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hubo un error en la actualizacion de la calificacion");

        }

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarCalificacion(@PathVariable("id") Integer idCalificacion){
        try{

            calificacionesService.eliminar(idCalificacion);
            return ResponseEntity.status(HttpStatus.OK).body("La calificacion fue borrada con exito");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hubo un error en la eliminacion de la calificacion");

        }
    }

    @GetMapping("/")
    public ResponseEntity<List<CalificacionesModel>> obtenerCalificaciones(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(calificacionesService.mostrarCalificaciones());
        }catch (Exception ex){

            return new ResponseEntity("Hubo un error en traer las calificacion", HttpStatus.BAD_REQUEST);

        }

    }



}
