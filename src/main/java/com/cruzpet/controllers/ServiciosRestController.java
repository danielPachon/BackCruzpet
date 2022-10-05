package com.cruzpet.controllers;

import com.cruzpet.entitys.RazaEntity;
import com.cruzpet.entitys.ServiciosEntity;
import com.cruzpet.models.ServiciosModel;
import com.cruzpet.services.ServicioService;
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
@RequestMapping("/v1.0/servicios")
public class ServiciosRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("servicioService")
    private ServicioService servicioService;

    @PostMapping("/crear")
    public ResponseEntity<String> agregarServicio(@RequestBody ServiciosEntity serviciosEntity){
        try{
            servicioService.insertar(serviciosEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("El servicio fue creado con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al crear el servicio");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarServicio(@RequestBody ServiciosEntity serviciosEntity){
        try{
            servicioService.actualizar(serviciosEntity);
            return ResponseEntity.status(HttpStatus.OK).body("El servicio fue actualizado con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al actualizar el servicio");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarServicio(@PathVariable("id") Integer idServicio){
        try{
            servicioService.eliminar(idServicio);
            return ResponseEntity.status(HttpStatus.OK).body("El servicio fue eliminado con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al eliminar el servicio");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<ServiciosModel>> obtenerServicios(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicioService.traerServicios());
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer los servicios", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/serviciospaginador")
    public ResponseEntity<Page<ServiciosEntity>> obtenerServiciosPaginador(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "6")int size){

        Page<ServiciosEntity> servicios = servicioService.obtenerServiciosPaginador(
                PageRequest.of(page, size));
        return new ResponseEntity<Page<ServiciosEntity>>(servicios, HttpStatus.OK);
    }

    @PostMapping("/obtenerservicioid")
    public ResponseEntity<ServiciosEntity> obtenerServicioId(@RequestBody ServiciosEntity serviciosEntity){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicioService.obtenerServicioId(serviciosEntity));
        }catch (Exception ex){

            log.info(ex.getMessage());
            return null;

        }

    }

}
