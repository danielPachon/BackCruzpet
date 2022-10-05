package com.cruzpet.controllers;

import com.cruzpet.entitys.RazaEntity;
import com.cruzpet.entitys.TipoMascotaEntity;
import com.cruzpet.entitys.TipoSangreEntity;
import com.cruzpet.models.TipoMascotaModel;
import com.cruzpet.models.TipoSangreModel;
import com.cruzpet.services.TipoMascotaService;
import com.cruzpet.services.TipoSangreService;
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
@RequestMapping("/v1.0/tipossangre")
public class TipoSangreRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("tipoSangreService")
    private TipoSangreService tipoSangreService;

    @PostMapping("/crear")
    public ResponseEntity<String> agregarTipoSangre(@RequestBody TipoSangreEntity tipoSangreEntity){
        try{
            tipoSangreService.crear(tipoSangreEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("El tipo de sangre fue registrada con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al registrar el tipo de sangre");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarTipoSangre(@RequestBody TipoSangreEntity tipoSangreEntity){
        try{
            tipoSangreService.actualizar(tipoSangreEntity);
            return ResponseEntity.status(HttpStatus.OK).body("El tipo de sangre fue actualizado con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al actualizar el tipo de sangre");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarTipoSangre(@PathVariable("id") Integer idTipoSangre){
        try{
            tipoSangreService.borrar(idTipoSangre);
            return ResponseEntity.status(HttpStatus.OK).body("El tipo de sangre fue borrada con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al borrar el tipo de sangre");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<TipoSangreModel>> obtenerTipoSangre(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(tipoSangreService.obtenerTiposSangre());
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer los tipos de sangre", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/traertipomascota")
    public ResponseEntity<List<TipoSangreModel>> obtenerTipoSangreTipoMascota(@RequestBody TipoSangreEntity tipoSangreEntity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(tipoSangreService.obtenerTipoSangreTipoMascota(tipoSangreEntity.getTipoMascotaEntity().getIdTipoMascota()));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer los tipos de sangre", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/traertipomascotanombre")
    public ResponseEntity<TipoSangreEntity> obtenerTipoSangreTipoMascotaNombre(@RequestBody TipoSangreEntity tipoSangreEntity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(tipoSangreService.obtenerTipoSangreNombre(tipoSangreEntity));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo traer el tipo de sangre", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/obtenertiposangreid")
    public ResponseEntity<TipoSangreEntity> obtenerTipoSangreId(@RequestBody TipoSangreEntity tipoSangreEntity){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(tipoSangreService.obtenerTipoSangreId(tipoSangreEntity));
        }catch (Exception ex){

            log.info(ex.getMessage());
            return null;

        }

    }

    @GetMapping("/tiposangrepaginador")
    public ResponseEntity<Page<TipoSangreEntity>> obtenerTipoSangrePaginador(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "6")int size){

        Page<TipoSangreEntity> tipoSangreEntity = tipoSangreService.obtenerTipoSangrePaginador(
                PageRequest.of(page, size));
        return new ResponseEntity<Page<TipoSangreEntity>>(tipoSangreEntity, HttpStatus.OK);
    }
}
