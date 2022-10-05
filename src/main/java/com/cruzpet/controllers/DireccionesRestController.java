package com.cruzpet.controllers;

import com.cruzpet.entitys.BarrioEntity;
import com.cruzpet.entitys.DepartamentoEntity;
import com.cruzpet.entitys.DireccionEntity;
import com.cruzpet.models.DepartamentoModel;
import com.cruzpet.models.DireccionModel;
import com.cruzpet.services.DireccionService;
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
@RequestMapping("/v1.0/direcciones")
public class DireccionesRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("direccionService")
    private DireccionService direccionService;

    @PostMapping("/crear")
    public ResponseEntity<DireccionEntity> agregarDireccion(@RequestBody DireccionEntity direccionEntity){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(direccionService.crear(direccionEntity));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo crear la direccion", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarDireccion(@RequestBody DireccionEntity direccionEntity){
        try{
            direccionService.actualizar(direccionEntity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente la direccion");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo actualizar la direccion");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarDireccion(@PathVariable("id") Integer idDireccion){
        try{
            direccionService.eliminar(idDireccion);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente la direccion");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo eliminar la direccion");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<DireccionModel>> obtenerDireccion(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(direccionService.mostrarDirecciones());
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las direcciones", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/traerdireccionnombre")
    public ResponseEntity<DireccionEntity> obtenerDireccionNombre(@RequestBody DireccionEntity direccionEntity) {

        try{
            DireccionEntity direccion = direccionService.mostrarDireccion(direccionEntity);
            if(direccion == null){
                return new ResponseEntity("No existe la direccion", HttpStatus.BAD_REQUEST);
            }else{
                return ResponseEntity.status(HttpStatus.OK).body(direccionService.mostrarDireccion(direccionEntity));
            }
        }catch (Exception ex){

            log.error(ex.getMessage());
            log.error(ex.getLocalizedMessage());
            return new ResponseEntity("No se pudieron traer las direcciones", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/traerdireccionesid")
    public ResponseEntity<DireccionEntity> obtenerDireccionId(@RequestBody DireccionEntity direccionEntity) {

        try{

            return ResponseEntity.status(HttpStatus.OK).body(direccionService.mostrarDireccionId(direccionEntity));

        }catch (Exception ex){

            log.error(ex.getMessage());
            log.error(ex.getLocalizedMessage());
            return new ResponseEntity("No se pudieron traer las direcciones", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/direccionespaginador")
    public ResponseEntity<Page<DireccionEntity>> obtenerDireccionesPaginador(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "6")int size, @RequestParam(defaultValue = "idDireccion" +
            "" +
            "" +
            "" +
            "" +
            "") String order, @RequestParam(defaultValue = "true") boolean asc){

        Page<DireccionEntity> direcciones = direccionService.obtenerDirreccionesPaginador(
                PageRequest.of(page, size, Sort.by(order)));
        if(!asc){
            direcciones = direccionService.obtenerDirreccionesPaginador(
                    PageRequest.of(page, size, Sort.by(order).descending()));
        }
        return new ResponseEntity<Page<DireccionEntity>>(direcciones, HttpStatus.OK);
    }

    @GetMapping("/direccionespaginadorpalabra")
    public ResponseEntity<Page<DireccionEntity>> obtenerDireccionesPaginadorPalabra(@RequestParam(defaultValue = "0")int page,
                                                                             @RequestParam(defaultValue = "6")int size, @RequestParam("palabra") String palabra){

        try{
            List<DireccionEntity> direccionesSinP = direccionService.mostrarDireccionesPalabra(palabra);

            Pageable primero = PageRequest.of(page, size);

            int start = Math.min((int)primero.getOffset(), direccionesSinP.size());
            int end = Math.min((start + primero.getPageSize()), direccionesSinP.size());

            Page<DireccionEntity> direccionesConP = new PageImpl(direccionesSinP.subList(start, end), primero, direccionesSinP.size());

            return new ResponseEntity<>(direccionesConP, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity("No se pudieron traer los barrios", HttpStatus.BAD_REQUEST);
        }

    }
}
