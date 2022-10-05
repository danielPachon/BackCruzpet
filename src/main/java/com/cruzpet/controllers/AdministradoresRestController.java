package com.cruzpet.controllers;

import com.cruzpet.entitys.*;
import com.cruzpet.models.AdministradorModel;
import com.cruzpet.models.BarrioModel;
import com.cruzpet.models.ClienteModel;
import com.cruzpet.security.dto.JwtDto;
import com.cruzpet.security.jwt.JwtProvider;
import com.cruzpet.services.AdministradorService;
import com.cruzpet.services.BarrioService;
import com.cruzpet.services.RolService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/v1.0/administradores")
public class AdministradoresRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("administradorService")
    private AdministradorService administradorService;

    @PostMapping("/crear")
    public ResponseEntity<?> agregarAdministrador(@RequestBody AdministradorEntity administradorEntity, BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return new ResponseEntity("Campos mal puestos o email invalido", HttpStatus.BAD_REQUEST);

        }

        if (administradorService.existenciaAdministrador(administradorEntity.getCorreAdministrador())){

            return new ResponseEntity("El email ya existe", HttpStatus.BAD_REQUEST);

        }

        try {
            administradorService.insertar(administradorEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity("El veterinario ha sido creado con exito", HttpStatus.CREATED);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarAdministrador(@RequestBody AdministradorEntity administradorEntity){
        try{
            administradorService.actualizar(administradorEntity);
            return new ResponseEntity("El veterinario ha sido actualizado con exito", HttpStatus.OK);
        }catch (Exception ex) {

            log.error(ex.getMessage());
            return new ResponseEntity("El veterinario no pudo ser actualizado con exito", HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarAdministrador(@PathVariable("id") Integer idAdministrador){
        try{

            administradorService.eliminar(idAdministrador);
            return new ResponseEntity("El veterinario ha sido eliminado con exito", HttpStatus.OK);

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("El veterinario no se pudo leer", HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/")
    public ResponseEntity<List<AdministradorModel>> obtenerAdministradores(){
        return new ResponseEntity(administradorService.obtenerAdministradores(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto>  login(@RequestBody AdministradorEntity administradorEntity, BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return new ResponseEntity("Campos mal puestos o email invalido o password invalido ", HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity(administradorService.login(administradorEntity), HttpStatus.OK);

    }

    @PostMapping("/existenciaemail")
    public ResponseEntity<Boolean> exsitenciaEmail(@RequestBody AdministradorEntity administradorEntity) {
        try {

            AdministradorModel administradorModel = administradorService.buescarAdministradorEmail(administradorEntity.getCorreAdministrador());

            return ResponseEntity.status(HttpStatus.OK).body(true);

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);

        }

    }

    @PutMapping("/actualizarcontrasena")
    public ResponseEntity<String> actualizarContrasena(@RequestBody AdministradorEntity administradorEntity){

        try {
            if(administradorService.actualizarContrasenaAdministrador(administradorEntity.getCorreAdministrador(), administradorEntity.getPasswordAdministrador())){
                return ResponseEntity.status(HttpStatus.OK).body("La contraseña fue actualizada con exito");
            }else{
                return ResponseEntity.status(HttpStatus.OK).body("La contraseña es la misma");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La contraseña no pudo ser actualizada");
        }

    }


    @PostMapping("/administradorid")
    public ResponseEntity<AdministradorEntity> obtenerAdministradorId(@RequestBody AdministradorEntity administradorEntity){
        return new ResponseEntity(administradorService.traerAdministradorId(administradorEntity), HttpStatus.OK);
    }
}
