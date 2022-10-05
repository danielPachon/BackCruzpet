package com.cruzpet.controllers;

import com.cruzpet.entitys.CiudadEntity;
import com.cruzpet.entitys.DepartamentoEntity;
import com.cruzpet.entitys.DireccionEntity;
import com.cruzpet.models.CiudadModel;
import com.cruzpet.services.CiudadService;
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
@RequestMapping("/v1.0/ciudades")
public class CiudadesRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("ciudadService")
    private CiudadService ciudadService;

    @PostMapping("/crear")
    public ResponseEntity<String> agregarCiudad(@RequestBody CiudadEntity ciudadEntity){
        try{
            ciudadService.crear(ciudadEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Se creo correctamente la ciudad");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo crear la ciudad");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarCiudad(@RequestBody CiudadEntity ciudadEntity){
        try{
            ciudadService.actualizar(ciudadEntity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente la ciudad");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo actualizar la ciudad");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarCiudad(@PathVariable("id") Integer idCiudad){
        try{
            ciudadService.eliminar(idCiudad);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente la ciudad");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo eliminar la ciudad");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<CiudadModel>> obtenerCiudades(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(ciudadService.mostrarCiudades());
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las ciudad", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/ciudadesdepartamento")
    public ResponseEntity<List<CiudadModel>> obtenerCiudadesDepartamento(@RequestBody CiudadEntity ciudad){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(ciudadService.mostrarCiudadesDepartamento(ciudad.getDepartamentoOrigen().getIdDepartamento()));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las ciudad", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/nombreciudaddepartamento")
    public ResponseEntity<CiudadModel> obtenerCiudadNombreDepartamento(@RequestBody CiudadEntity ciudadEntity){

        try{
            log.error(ciudadEntity.getNombreCiudad());
            return ResponseEntity.status(HttpStatus.OK).body(ciudadService.mostrarCiudadDepartamentoNombre(ciudadEntity.getNombreCiudad(),ciudadEntity.getDepartamentoOrigen().getIdDepartamento()));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo traer las ciudad", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/ciudadesnombredepartamento")
    public ResponseEntity<List<CiudadEntity>> obtenerCiudadesNombreDepartamento(@RequestBody CiudadEntity ciudadEntity){
        try{
            log.error(ciudadEntity.getNombreCiudad());
            return ResponseEntity.status(HttpStatus.OK).body(ciudadService.mostrarCiudadesDepartamento(ciudadEntity.getDepartamentoOrigen().getNombreDepartamento()));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las ciudad", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/traerciudadid")
    public ResponseEntity<CiudadEntity> obtenerCiudadId(@RequestBody CiudadEntity ciudadEntity){
        try{
            log.error(ciudadEntity.getNombreCiudad());
            return ResponseEntity.status(HttpStatus.OK).body(ciudadService.traerCiudadId(ciudadEntity));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las ciudad", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/ciudadespaginador")
    public ResponseEntity<Page<CiudadEntity>> obtenerCiudadesPaginador(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "6")int size, @RequestParam(defaultValue = "idCiudad" +
            "" +
            "" +
            "" +
            "" +
            "") String order, @RequestParam(defaultValue = "true") boolean asc){

        Page<CiudadEntity> ciudades = ciudadService.obtenerCiudadesPaginador(
                PageRequest.of(page, size, Sort.by(order)));
        if(!asc){
            ciudades = ciudadService.obtenerCiudadesPaginador(
                    PageRequest.of(page, size, Sort.by(order).descending()));
        }
        return new ResponseEntity<Page<CiudadEntity>>(ciudades, HttpStatus.OK);
    }

    @GetMapping("/ciudadespaginadorpalabra")
    public ResponseEntity<Page<CiudadEntity>> obtenerCiudadesPaginadorPalabra(@RequestParam(defaultValue = "0")int page,
                                                                                         @RequestParam(defaultValue = "6")int size, @RequestParam("palabra") String palabra){

        try{
            List<CiudadEntity> ciudadesSinP = ciudadService.mostrarCiudadPalabra(palabra);

            Pageable primero = PageRequest.of(page, size);

            int start = Math.min((int)primero.getOffset(), ciudadesSinP.size());
            int end = Math.min((start + primero.getPageSize()), ciudadesSinP.size());

            Page<CiudadEntity> ciudadesConP = new PageImpl(ciudadesSinP.subList(start, end), primero, ciudadesSinP.size());

            return new ResponseEntity<>(ciudadesConP, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity("No se pudieron traer las ciudades", HttpStatus.BAD_REQUEST);
        }

    }
}
