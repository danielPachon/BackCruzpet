package com.cruzpet.controllers;

import com.cruzpet.entitys.BarrioEntity;
import com.cruzpet.entitys.CarnetVacunacionEntity;
import com.cruzpet.models.BarrioModel;
import com.cruzpet.models.CarnetVacunacionModel;
import com.cruzpet.services.BarrioService;
import com.cruzpet.services.CarnetVacunacionService;
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
@RequestMapping("/v1.0/carnetvacunacion")
public class CarnetVacunacionRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("carnetVacunacionService")
    private CarnetVacunacionService carnetVacunacionService;

    @PostMapping("/crear")
    public ResponseEntity<String> agregarCarnetVacunacion(@RequestBody CarnetVacunacionEntity carnetVacunacionEntity){
        try{
            carnetVacunacionService.insertar(carnetVacunacionEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Se creo correctamente el carnet de vacunacion");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo crear el carnet de vacunacion");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarCarnetVacunacion(@RequestBody CarnetVacunacionEntity carnetVacunacionEntity){

        try{
            carnetVacunacionService.actualizar(carnetVacunacionEntity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente el carnet de vacunacion");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo actualizar el carnet de vacunacion");

        }

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarCarnetVacunacion(@PathVariable("id") Integer idCarnetVacunacion){

        try{

            carnetVacunacionService.eliminar(idCarnetVacunacion);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente el carnet de vacunacion");

        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo eliminar el carnet de vacunacion");

        }

    }

    @GetMapping("/")
    public ResponseEntity<List<CarnetVacunacionModel>> obtenerCarnetsVacunacion(){

        try{

            return ResponseEntity.status(HttpStatus.OK).body(carnetVacunacionService.obtenerCarnetVacunacion());

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer todos los carnet de vacunacion", HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping("/carnetmascota")
    private  ResponseEntity<CarnetVacunacionEntity> obtenerCarnetVacunacionIdentificacion(@RequestBody CarnetVacunacionEntity carnetVacunacionEntity){

        try{

            return ResponseEntity.status(HttpStatus.OK).body(carnetVacunacionService.obternerCarnetVacunacionMascota(carnetVacunacionEntity));

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo traer el carnet de vacunacion", HttpStatus.BAD_REQUEST);

        }

    }

}
