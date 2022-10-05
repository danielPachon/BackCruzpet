package com.cruzpet.controllers;

import com.cruzpet.entitys.BarrioEntity;
import com.cruzpet.entitys.CiudadEntity;
import com.cruzpet.models.BarrioModel;
import com.cruzpet.services.BarrioService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/v1.0/barrios")
public class BarriosRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("barrioService")
    private BarrioService barrioService;

    @PostMapping("/crear")
    public ResponseEntity<BarrioEntity> agregarBarrio(@RequestBody BarrioEntity barrioEntity){

        try{

            return new ResponseEntity(barrioService.crear(barrioEntity), HttpStatus.CREATED);

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo crear el barrio", HttpStatus.BAD_REQUEST);

        }

    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarBarrio(@RequestBody BarrioEntity barrioEntity){

        try{

            barrioService.actualizar(barrioEntity);
            return new ResponseEntity("El barrio se actualizo correctamente", HttpStatus.OK);

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("El barrio no se pudo actualizar", HttpStatus.BAD_REQUEST);
        }


    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarBarrio(@PathVariable("id") Integer idBarrio){

        try{
            barrioService.eliminar(idBarrio);
            return new ResponseEntity("El barrio se elimino correctamente", HttpStatus.OK);
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("El barrio no se pudo eliminar", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/")
    public ResponseEntity<List<BarrioModel>> obtenerBarrios(){

        try {

            return new ResponseEntity(barrioService.mostrarBarrios(), HttpStatus.OK);
        }catch (Exception ex){

            return new ResponseEntity("Hubo un errror en traer los barrios", HttpStatus.BAD_REQUEST);

        }

    }

    @GetMapping("/barriospaginador")
    public ResponseEntity<Page<BarrioEntity>> obtenerBarriosPaginador(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "6")int size, @RequestParam(defaultValue = "idBarrio") String order, @RequestParam(defaultValue = "true") boolean asc){

       Page<BarrioEntity> barrios = barrioService.mostrarBarriosPaginador(
               PageRequest.of(page, size, Sort.by(order)));
           if(!asc){
               barrios = barrioService.mostrarBarriosPaginador(
                       PageRequest.of(page, size, Sort.by(order).descending()));
           }
           return new ResponseEntity<Page<BarrioEntity>>(barrios, HttpStatus.OK);
    }

    @GetMapping("/barriospaginadorpalabra")
    public ResponseEntity<Page<BarrioEntity>> obtenerBarriosPaginadorPalabra(@RequestParam(defaultValue = "0")int page,
                                                                              @RequestParam(defaultValue = "6")int size, @RequestParam("palabra") String palabra){

        try{
            List<BarrioEntity> barriosSinP = barrioService.mostrarBarrioPalabra(palabra);

            Pageable primero = PageRequest.of(page, size);

            int start = Math.min((int)primero.getOffset(), barriosSinP.size());
            int end = Math.min((start + primero.getPageSize()), barriosSinP.size());

            Page<BarrioEntity> barriosConP = new PageImpl(barriosSinP.subList(start, end), primero, barriosSinP.size());

            return new ResponseEntity<>(barriosConP, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity("No se pudieron traer los barrios", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/mostrarbarriociudad")
    public ResponseEntity<BarrioModel> obtenerBarrioCiudad(@RequestBody BarrioEntity barrioEntity){

        try{
            return new ResponseEntity(barrioService.mostrarBarrioCiudad(barrioEntity), HttpStatus.OK);
        }catch (Exception ex) {

            return new ResponseEntity("Hubo un errror en traer los barrios", HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping("/mostrarbarrioid")
    public ResponseEntity<BarrioEntity> obtenerBarrioId(@RequestBody BarrioEntity barrioEntity){

        try{
            return new ResponseEntity(barrioService.taerBarrioId(barrioEntity), HttpStatus.OK);
        }catch (Exception ex) {

            return new ResponseEntity("Hubo un errror en traer los barrios", HttpStatus.BAD_REQUEST);

        }

    }

}
