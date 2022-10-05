package com.cruzpet.controllers;

import com.cruzpet.entitys.*;
import com.cruzpet.models.BarrioModel;
import com.cruzpet.models.DisponibilidadModel;
import com.cruzpet.models.VeterinarioDisponibilidadesModel;
import com.cruzpet.services.BarrioService;
import com.cruzpet.services.DisponibilidadService;
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
@RequestMapping("/v1.0/disponibilidades")
public class DisponibilidadesRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("disponibilidadService")
    private DisponibilidadService disponibilidadService;

    @PostMapping("/crear")
    public ResponseEntity<String> agregarDisponibilidad(@RequestBody DisponibilidadEntity disponibilidadEntity){

        try{
            disponibilidadService.crear(disponibilidadEntity);
            return new ResponseEntity("Se creo correctamente la fecha de disposicion", HttpStatus.CREATED);

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo crear la fecha de disposicion", HttpStatus.BAD_REQUEST);

        }

    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarDisponibilidad(@RequestBody DisponibilidadEntity disponibilidadEntity){

        try{

            disponibilidadService.actualizar(disponibilidadEntity);
            return new ResponseEntity("La fecha de disposicion se actualizo correctament", HttpStatus.OK);

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("La fecha de disposcion no se pudo actualizar", HttpStatus.BAD_REQUEST);
        }


    }

    @PutMapping("/seleccionarintervalo")
    public ResponseEntity<String> inactivarIntervalo(@RequestBody IntervalosEntity intervalosEntity){

        try{

            disponibilidadService.intervaloSeleccionado(intervalosEntity.getIdIntervalo());
            return new ResponseEntity("El intervalo a sido seleccionado correctamente", HttpStatus.OK);

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("El intervalo no se pudo seleccionar", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarDisponibilidad(@PathVariable("id") Integer idDisponibilidad){

        try{
            disponibilidadService.eliminar(idDisponibilidad);
            return new ResponseEntity("La disponibilidad se elimino correctamente", HttpStatus.OK);
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("La disponibilidad no se pudo eliminar", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/")
    public ResponseEntity<List<DisponibilidadModel>> obtenerDisponibilidades(){

        try {

            return new ResponseEntity(disponibilidadService.mostrarDisponibilidades(), HttpStatus.OK);
        }catch (Exception ex){

            return new ResponseEntity("Hubo un errror en traer las disponibilidades", HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping("/disponibilidadrut")
    public ResponseEntity<List<DisponibilidadModel>> obtenerDisponibilidadesRut(@RequestBody DisponibilidadEntity disponibilidadEntity){

        try {

            return new ResponseEntity(disponibilidadService.mostrarDisponibilidadesIpsa(disponibilidadEntity.getIpsafk().getRut()), HttpStatus.OK);
        }catch (Exception ex){

            return new ResponseEntity("Hubo un errror en traer las disponibilidades", HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/disponibilidaddia")
    public ResponseEntity<List<DisponibilidadModel>> obtenerDisponibilidadesDia(@RequestBody DisponibilidadEntity disponibilidadEntity){

        try {

            return new ResponseEntity(disponibilidadService.mostrarDisponibilidadesDia(disponibilidadEntity.getDiaDisponibilidad()), HttpStatus.OK);
        }catch (Exception ex){

            return new ResponseEntity("Hubo un errror en traer las disponibilidades", HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/disponibilidadmes")
    public ResponseEntity<List<DisponibilidadModel>> obtenerDisponibilidadesMes(@RequestBody DisponibilidadEntity disponibilidadEntity){

        try {

            return new ResponseEntity(disponibilidadService.mostrarDisponibilidadesMes(disponibilidadEntity.getMesDisponibilidad()), HttpStatus.OK);
        }catch (Exception ex){

            return new ResponseEntity("Hubo un errror en traer las disponibilidades", HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/disponibilidadyear")
    public ResponseEntity<List<DisponibilidadModel>> obtenerDisponibilidadesYear(@RequestBody DisponibilidadEntity disponibilidadEntity){

        try {

            return new ResponseEntity(disponibilidadService.mostrarDisponibilidadesYear(disponibilidadEntity.getYearDisponibilidad()), HttpStatus.OK);
        }catch (Exception ex){

            return new ResponseEntity("Hubo un errror en traer las disponibilidades", HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/disponibilidaddiames")
    public ResponseEntity<List<DisponibilidadModel>> obtenerDisponibilidadesDiaMes(@RequestBody DisponibilidadEntity disponibilidadEntity){

        try {

            return new ResponseEntity(disponibilidadService.mostrarDisponibilidadesDiaMes(disponibilidadEntity.getDiaDisponibilidad(), disponibilidadEntity.getMesDisponibilidad()), HttpStatus.OK);
        }catch (Exception ex){

            return new ResponseEntity("Hubo un errror en traer las disponibilidades", HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/disponiblidaddiamesyear")
    public ResponseEntity<List<DisponibilidadModel>> obtenerDisponibilidadesDiaMesYear(@RequestBody DisponibilidadEntity disponibilidadEntity) {

        try{


            return ResponseEntity.status(HttpStatus.OK).body(disponibilidadService.mostrarDisponibilidadDiaMesYear(disponibilidadEntity.getDiaDisponibilidad(), disponibilidadEntity.getMesDisponibilidad(), disponibilidadEntity.getYearDisponibilidad()));

        }catch (Exception ex){

            return new ResponseEntity("Hubo un error en traer las disponibilidades", HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping("/disponiblidaddiamesyearrut")
    public ResponseEntity<List<VeterinarioDisponibilidadesModel>> obtenerDisponibilidadesDiaMesYearrut(@RequestBody DisponibilidadEntity disponibilidadEntity) {

        try{


            return ResponseEntity.status(HttpStatus.OK).body(disponibilidadService.mostrarDisponibilidadDiaMesYearRut(disponibilidadEntity.getDiaDisponibilidad(), disponibilidadEntity.getMesDisponibilidad(), disponibilidadEntity.getYearDisponibilidad(), disponibilidadEntity.getIpsafk().getRut()));

        }catch (Exception ex){

            return new ResponseEntity("Hubo un error en traer las disponibilidades", HttpStatus.BAD_REQUEST);

        }
    }


    @PostMapping("/disponiblidaddveterinario")
    public ResponseEntity<List<VeterinarioDisponibilidadesModel>> obtenerDisponibilidadesVeterinario(@RequestBody VeterinarioDisponibilidadesEntity veterinarioDisponibilidadesEntity) {

        try{

            return ResponseEntity.status(HttpStatus.OK).body(disponibilidadService.mostrarDisponibilidadVeterinario(veterinarioDisponibilidadesEntity.getDisponibilidadEntity().getDiaDisponibilidad(), veterinarioDisponibilidadesEntity.getDisponibilidadEntity().getMesDisponibilidad(),  veterinarioDisponibilidadesEntity.getDisponibilidadEntity().getYearDisponibilidad(), veterinarioDisponibilidadesEntity.getDisponibilidadEntity().getIpsafk().getRut(),veterinarioDisponibilidadesEntity.getVeterinarioEntity().getCedVeterinario()));

        }catch (Exception ex){

            log.error(ex.getMessage());
            log.info(ex.getMessage());
            return new ResponseEntity("Hubo un error en traer las disponibilidades", HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/actualizarestadodisponibilidad")
    public ResponseEntity<List<String>> actualizarEstadoDisponibilidad(@RequestBody DisponibilidadEntity disponibilidad){

        try{

            disponibilidadService.actualizarEstadoDisponibilidad(disponibilidad);
            return new ResponseEntity("Se actualizo correctamente", HttpStatus.OK);

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("Hubo un error en actualizar el estado de la disponibilidad", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/actualizarestadointervalo")
    public ResponseEntity<List<String>> actualizarEstadoIntervalo(@RequestBody IntervalosEntity intervalosEntity){

        try{

            disponibilidadService.actualizarEstadoIntervalo(intervalosEntity);
            return new ResponseEntity("Se actualizo correctamente", HttpStatus.OK);

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("Hubo un error en actualizar el estado del intervalo", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/traerintervalos")
    public ResponseEntity<List<IntervalosEntity>> traerIntervalos(){

        try{

            return new ResponseEntity(disponibilidadService.traerIntervalos(), HttpStatus.OK);

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("Hubo un error en traer los intervalos", HttpStatus.BAD_REQUEST);

        }

    }

}

