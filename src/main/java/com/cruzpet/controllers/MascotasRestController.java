package com.cruzpet.controllers;

import com.cruzpet.entitys.MascotaEntity;
import com.cruzpet.entitys.ClienteEntity;
import com.cruzpet.entitys.PostEntity;
import com.cruzpet.models.MascotaModel;
import com.cruzpet.services.MascotaService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/v1.0/mascotas")
public class MascotasRestController {

    Log logger = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("mascotaService")
    private MascotaService mascotaService;

    @PostMapping("/crear")
    public ResponseEntity<String> agregarMascota(@RequestBody MascotaEntity mascotaEntity){
        try{
            mascotaService.crear(mascotaEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("La mascota fue registrado con exito");
        }catch (Exception e){
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al registrar la mascota");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarMascota(@RequestBody MascotaEntity mascotaEntity){
        try{
            mascotaService.actualizar(mascotaEntity);
            return ResponseEntity.status(HttpStatus.OK).body("La mascota fue actualizada con exito");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La mascota no se pudo actualizar");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarMascota(@PathVariable("id") String numeroIdentificacion){
        try {
            mascotaService.borrar(numeroIdentificacion);
            return ResponseEntity.status(HttpStatus.OK).body("La mascota fue borrado con exito");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La mascota no se pudo borrar");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<MascotaModel>> mostrarMascotas(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(mascotaService.obtenerMascotas());
        }catch (Exception ex){

            logger.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las mascotas", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/individual/{id}")
    public ResponseEntity<MascotaModel> mostarMascota(@PathVariable("id") String idMascota){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(mascotaService.mostrarMascota(idMascota));
        }catch (Exception ex){

            logger.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las mascotas", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/mascotausuario")
    public  ResponseEntity<List<MascotaModel>> mostrarMascotaUsuario(@RequestBody MascotaEntity mascotaEntity){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(mascotaService.obtenerMascotaUsuario(mascotaEntity.getClienteMascota().getCedulaCliente()));
        }catch (Exception ex){

            logger.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las mascotas", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/existenciamascota")
    public ResponseEntity<Boolean> existenciaMascota(@RequestBody MascotaEntity mascotaEntity){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(mascotaService.existenciaMascota(mascotaEntity.getNumeroIdentidad()));
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mascotaService.existenciaMascota(mascotaEntity.getNumeroIdentidad()));
        }

    }

    @GetMapping("/mascotaspaginador")
    public ResponseEntity<Page<MascotaEntity>> obtenerMascotasPaginador(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "6")int size){

        Page<MascotaEntity> mascotas = mascotaService.obtenerMascotassPaginador(
                PageRequest.of(page, size));
        return new ResponseEntity<Page<MascotaEntity>>(mascotas, HttpStatus.OK);
    }

    @GetMapping("/mostrarmascotastepalabra")
    public ResponseEntity<Page<MascotaEntity>> obtenerMascotasPaginadorPalabra(@RequestParam(defaultValue = "0")int page,
                                                                               @RequestParam(defaultValue = "6")int size, @RequestParam("palabra") String palabra){

        try{
            List<MascotaEntity> mascotasSinP = mascotaService.mostrarMascotasCliente(palabra);

            Pageable primero = PageRequest.of(page, size);

            int start = Math.min((int)primero.getOffset(), mascotasSinP.size());
            int end = Math.min((start + primero.getPageSize()), mascotasSinP.size());

            Page<MascotaEntity> mascotasConP = new PageImpl(mascotasSinP.subList(start, end), primero, mascotasSinP.size());

            return new ResponseEntity<>(mascotasConP, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity("No se pudieron traer los departamentos", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/traermascotapaginadorcliente")
    public ResponseEntity<Page<MascotaEntity>> obtenerMascotaPaginadorCliente(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "6")int size, @RequestParam("cliente") String cliente){

        Page<MascotaEntity> mascotas = mascotaService.obtenerMascotassPaginadorCliente(
                cliente,PageRequest.of(page, size));
        return new ResponseEntity<Page<MascotaEntity>>(mascotas, HttpStatus.OK);
    }

}
