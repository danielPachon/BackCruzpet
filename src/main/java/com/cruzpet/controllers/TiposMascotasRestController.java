package com.cruzpet.controllers;

import com.cruzpet.entitys.TipoMascotaEntity;
import com.cruzpet.entitys.TipoSangreEntity;
import com.cruzpet.models.TipoMascotaModel;
import com.cruzpet.services.TipoMascotaService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/v1.0/tipos_mascotas")
public class TiposMascotasRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("tipoMascotaService")
    private TipoMascotaService tipoMascotaService;

    @PostMapping("/crear")
    public ResponseEntity<String> agregarTipoMascota(@RequestBody TipoMascotaEntity tipoMascotaEntity){
        try{
            tipoMascotaService.crear(tipoMascotaEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("El tipo de mascota fue registrada con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al registrar el tipo de mascota");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarTipoMascota(@RequestBody TipoMascotaEntity tipoMascotaEntity){
        try{
            tipoMascotaService.actualizar(tipoMascotaEntity);
            return ResponseEntity.status(HttpStatus.OK).body("El tipo de mascota fue actualizado con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al actualizar el tipo de mascota");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarTipoMascota(@PathVariable("id") Integer idTipoMascota){
        try{
            tipoMascotaService.borrar(idTipoMascota);
            return ResponseEntity.status(HttpStatus.OK).body("El tipo de mascota fue borrada con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al borrar el tipo de mascota");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<TipoMascotaModel>> obtenerTipoMascota(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(tipoMascotaService.obtenerTipoMascota());
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer los tipos de mascotas", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/traernombre")
    public ResponseEntity<TipoMascotaEntity> obtenerTipoMascotaNombre(@RequestBody TipoMascotaEntity tipoMascotaEntity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(tipoMascotaService.obtenerTipoMascotaNombre(tipoMascotaEntity));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo traer el tipo de mascota", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/obtenertiposmascotaid")
    public ResponseEntity<TipoMascotaEntity> obtenerTipoMascotaId(@RequestBody TipoMascotaEntity tipoMascotaEntity){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(tipoMascotaService.obtenerTipoMascotaId(tipoMascotaEntity));
        }catch (Exception ex){

            log.info(ex.getMessage());
            return null;

        }

    }

    @GetMapping("/tipomascotapaginador")
    public ResponseEntity<Page<TipoMascotaEntity>> obtenerTipoMascotaPaginador(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "6")int size){

        Page<TipoMascotaEntity> tipoMascotaEntities = tipoMascotaService.obtenerTipoMascotaPaginador(
                PageRequest.of(page, size));
        return new ResponseEntity<Page<TipoMascotaEntity>>(tipoMascotaEntities, HttpStatus.OK);
    }

}
