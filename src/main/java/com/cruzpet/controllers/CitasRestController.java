package com.cruzpet.controllers;

import com.cruzpet.entitys.CitaEntity;
import com.cruzpet.entitys.IpsaEntity;
import com.cruzpet.entitys.ClienteEntity;
import com.cruzpet.entitys.VeterinarioEntity;
import com.cruzpet.models.CitaModel;
import com.cruzpet.services.CitaService;
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
@RequestMapping("/v1.0/citas")
public class CitasRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("citaService")
    private CitaService citaService;

    @PostMapping("/crear")
    public ResponseEntity<String> agregarCita(@RequestBody CitaEntity citaEntity){
        try{
            citaService.crear(citaEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Se creo correctamente la cita");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo crear la cita");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarCita(@RequestBody CitaEntity citaEntity){
        try{
            citaService.actualizar(citaEntity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente la cita");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo actualizar la cita");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarCita(@PathVariable("id") Integer idCita){
        try{
            citaService.eliminar(idCita);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente la cita");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo eliminar la cita");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<CitaModel>> obtenerCitas(){
        try{

            return ResponseEntity.status(HttpStatus.OK).body(citaService.mostrarCitas());

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las citas", HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/individual/{id}")
    public ResponseEntity<CitaEntity> mostrarCita(@PathVariable("id") Integer idCita){
        try{

            return ResponseEntity.status(HttpStatus.OK).body(citaService.mostrarCita(idCita));

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las citas", HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/citasipsa")
    public ResponseEntity<List<CitaModel>> obtenerCitasIpsa(@RequestBody IpsaEntity ipsa) {

        try{

            return ResponseEntity.status(HttpStatus.OK).body(citaService.mostrarCitasIpsa(ipsa));

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las citas", HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/citasusuario")
    public ResponseEntity<List<CitaModel>> obtenerCitasUsuario(@RequestBody ClienteEntity usuario){

        try{

            return ResponseEntity.status(HttpStatus.OK).body(citaService.mostrarCitasUsuario(usuario));

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las citas", HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/citasveterinario")
    public ResponseEntity<List<CitaModel>> obtenerCitasVeterinarios(@RequestBody VeterinarioEntity veterinario){

        try{

            return ResponseEntity.status(HttpStatus.OK).body(citaService.mostrarCitasVeterinario(veterinario));

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las citas", HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/citasidcita")

    public ResponseEntity<CitaModel> obtenerCitaIdCita(@RequestBody CitaEntity citaEntity){

        try{

            return ResponseEntity.status(HttpStatus.OK).body(citaService.mostrarCitaId(citaEntity));

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las citas", HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping("/citasfecharut")
    public ResponseEntity<List<CitaModel>> obtenerCitasFechaRut(@RequestBody CitaEntity citaEntity){

        try{

            return ResponseEntity.status(HttpStatus.OK).body(citaService.mostrarCitasFechaRut(citaEntity));

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las citas", HttpStatus.BAD_REQUEST);

        }


    }

    @PostMapping("/actualizarestado")
    public ResponseEntity<String> actualizarEstadoCita(@RequestBody CitaEntity citaEntity){

        try{

            citaService.actualizarEstadoCita(citaEntity);
            return ResponseEntity.status(HttpStatus.OK).body("La cita a sido actualizada correctamente");

        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body("La cita no se pudo actualizar");

        }


    }
}
