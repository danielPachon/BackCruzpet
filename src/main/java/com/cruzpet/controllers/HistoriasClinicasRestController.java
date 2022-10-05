package com.cruzpet.controllers;

import com.cruzpet.entitys.*;
import com.cruzpet.models.HistoriaClinicaModel;
import com.cruzpet.services.HistoriaClinicaService;
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
@RequestMapping("/v1.0/historias_clinicas")
public class HistoriasClinicasRestController {

    Log logger = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("historiaClinicaService")
    private HistoriaClinicaService historiaClinicaService;

    @PostMapping("/crear")
    public ResponseEntity<String> agregarHistoriaClinica(@RequestBody HistorialClinicaEntity historialClinicaEntity){
        try{
            historiaClinicaService.crear(historialClinicaEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Se creo correctamente la historia clinica");
        }catch (Exception ex){

            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo crear la historia clinica");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarHistoriaClinica(@RequestBody HistorialClinicaEntity historiaClinica){
        try{
            historiaClinicaService.actualizar(historiaClinica);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente la historia clinica");
        }catch (Exception ex){

            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo actualizar la historia clinica");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarHistoriaClinica(@PathVariable("id") Integer idHistoriaClinica){
        try{
            historiaClinicaService.borrar(idHistoriaClinica);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente la historia clinica");
        }catch (Exception ex){

            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo eliminar la historia clinica");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<HistoriaClinicaModel>> obtenerHistoriasClinicas(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(historiaClinicaService.obtenerHistoriasClinicas());
        }catch (Exception ex){

            logger.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las historias clinicas", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/hisotriasipsa")
    public ResponseEntity<List<HistoriaClinicaModel>> obtenerHisotirasClinicasIpsa(@RequestBody IpsaEntity ipsa){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(historiaClinicaService.obtenerHistoriasClinicasIpsa(ipsa));
        }catch (Exception ex){

            logger.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las historias clinicas", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/historiasusuarios")
    public ResponseEntity<List<HistoriaClinicaModel>> obtenerHistoriasClinicasUsuario(@RequestBody ClienteEntity usuario){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(historiaClinicaService.obtenerHistoriasClinicasUsuario(usuario));
        }catch (Exception ex){

            logger.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las historias clinicas", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/historiasveterinario")
    public ResponseEntity<List<HistoriaClinicaModel>> obtenerHistoriasClinicasVeter(@RequestBody VeterinarioEntity veterinario){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(historiaClinicaService.obtenerHistoriasClinicasVeterinario(veterinario));
        }catch (Exception ex){

            logger.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las historias clinicas", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/historiasmascotas")
    public ResponseEntity<List<HistoriaClinicaModel>> obtenerHisotriasClinicasMascotas(@RequestBody MascotaEntity mascota){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(historiaClinicaService.obtenerHistoriasClinicasMascota(mascota));
        }catch (Exception ex){

            logger.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las historias clinicas", HttpStatus.BAD_REQUEST);
        }
    }

}
