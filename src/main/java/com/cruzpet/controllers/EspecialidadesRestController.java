package com.cruzpet.controllers;

import com.cruzpet.entitys.DepartamentoEntity;
import com.cruzpet.entitys.EspecialidadEntity;
import com.cruzpet.models.EspecialidadModel;
import com.cruzpet.services.EspecialidadService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/v1.0/especialidades")
public class EspecialidadesRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("especialidadService")
    private EspecialidadService especialidadService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearEspecialidad(@RequestBody EspecialidadEntity especialidadEntity){

        try{
            especialidadService.crear(especialidadEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Se creo correctamente la especialidad");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo crear la especialidad");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarEspecialidad(@RequestBody EspecialidadEntity especialidadEntity){
        try{
            especialidadService.crear(especialidadEntity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente la especialidad");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo actualizar la especialidad");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarEspecialidad(@PathVariable("id") Integer idEspecialidad){
        try{
            especialidadService.eliminar(idEspecialidad);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente la especialidad");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo eliminar la especialidad");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<EspecialidadModel>> mostrarEspecialidades(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(especialidadService.mostrarEspecialidades());
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las especialidades", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/nombreespecialidad")
    public ResponseEntity<EspecialidadEntity> mostrarEspecialidad(@RequestBody EspecialidadEntity especialidadEntity){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(especialidadService.mostrarEspecialidadNombres(especialidadEntity.getNombreEspecialidad()));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo traer la especialidad", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/traerespecialidadid")
    public ResponseEntity<EspecialidadEntity> mostrarEspecialidadId(@RequestBody EspecialidadEntity especialidadEntity){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(especialidadService.mostrarEspecialidadId(especialidadEntity.getIdEspecialidad()));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo traer la especialidad", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/especialidadpaginador")
    public ResponseEntity<Page<EspecialidadEntity>> obtenerEspecialidadessPaginador(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "6")int size){

        Page<EspecialidadEntity> especialidades = especialidadService.obtenerEspecialidadesPaginador(
                PageRequest.of(page, size));

        return new ResponseEntity<Page<EspecialidadEntity>>(especialidades, HttpStatus.OK);
    }
}
