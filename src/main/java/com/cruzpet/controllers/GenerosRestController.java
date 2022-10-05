package com.cruzpet.controllers;

import com.cruzpet.entitys.GeneroEntity;
import com.cruzpet.models.GenerosModel;
import com.cruzpet.services.GeneroService;
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
@RequestMapping("/v1.0/generos")
public class GenerosRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("generoService")
    private GeneroService generoService;

    @PostMapping("/crear")
    public ResponseEntity<String> agregarGeneros(@RequestBody GeneroEntity generoEntity){
        try{
            generoService.crear(generoEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Se creo correctamente el genero");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo crear la especialidad");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarGeneros(@RequestBody GeneroEntity generoEntity){
        try{
            generoService.actualizar(generoEntity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente el genero");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo actualizar el genero");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarGeneros(@PathVariable("id") Integer idGenero){
        try{
            generoService.borrar(idGenero);
            return ResponseEntity.status(HttpStatus.OK).body("Se borro correctamente el genero");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo borrar el genero");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<GenerosModel>> obtenerGeneros(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(generoService.obtenerGeneros());
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer los generos", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/traergeneronombre")
    public ResponseEntity<GeneroEntity> obtenerGeneroNombre(@RequestBody GeneroEntity generoEntity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(generoService.obtenerGeneroNombre(generoEntity));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer los generos", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/traergeneroid")
    public ResponseEntity<GeneroEntity> obtenerGeneroId(@RequestBody GeneroEntity generoEntity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(generoService.obtenerGeneroId(generoEntity));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer los generos", HttpStatus.BAD_REQUEST);
        }
    }

}
