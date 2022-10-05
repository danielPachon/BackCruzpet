package com.cruzpet.controllers;

import com.cruzpet.entitys.*;
import com.cruzpet.models.IpsasModel;
import com.cruzpet.models.VeterinarioModel;
import com.cruzpet.security.dto.JwtDto;
import com.cruzpet.security.jwt.JwtProvider;
import com.cruzpet.services.RolService;
import com.cruzpet.services.VeterinarioService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/v1.0/veterinarios")
public class VeterinariosRestController {

    Log logger = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("veterinarioService")
    private VeterinarioService veterinarioService;


    @PostMapping(value = "/crear")
    public ResponseEntity<?> agregarVeterinario(@RequestBody VeterinarioEntity veterinarioEntity, BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return new ResponseEntity("Campos mal puestos o email invalido", HttpStatus.BAD_REQUEST);

        }

        if (veterinarioService.existenciaVeterinario(veterinarioEntity.getCorreoVeterinario())){

            return new ResponseEntity("El email ya existe", HttpStatus.BAD_REQUEST);

        }

        try {
            veterinarioService.crear(veterinarioEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity("El veterinario ha sido creado con exito", HttpStatus.CREATED);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarVeterinario(@RequestBody VeterinarioEntity veterinarioEntity){
        try{
            veterinarioService.actualizar(veterinarioEntity);
            return ResponseEntity.status(HttpStatus.OK).body("El veterinario fue actualizado con exito");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El veterinario no se pudo actualizar");
        }
    }

    @PutMapping("/actualizarsincontra")
    public ResponseEntity<String> actualizarVeterinarioSinContra(@RequestBody VeterinarioEntity veterinarioEntity){
        try{
            veterinarioService.actualizarSinPassword(veterinarioEntity);
            return ResponseEntity.status(HttpStatus.OK).body("El veterinario fue actualizado con exito");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El veterinario no se pudo actualizar");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarVeterinario(@PathVariable("id") String cedVeterinario){
        try {
            veterinarioService.borrar(cedVeterinario);
            return ResponseEntity.status(HttpStatus.OK).body("El veterinario fue borrado con exito");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El veterinario no se pudo borrar");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<VeterinarioModel>> mostrarVeterinarios(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(veterinarioService.obtenerVeterinarios());
        }catch (Exception ex){

            logger.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer los veterinarios", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/filtroipsas")
    public ResponseEntity<List<VeterinarioModel>> mostrarVeterinariosIpsas(@RequestBody IpsaEntity ipsa){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(veterinarioService.obtenerVeterinariosIpsa(ipsa));
        }catch (Exception ex){

            logger.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer los veterinarios", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto>  login(@RequestBody VeterinarioEntity veterinarioEntity, BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return new ResponseEntity("Campos mal puestos o email invalido o password invalido ", HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity(veterinarioService.loginVeterinario(veterinarioEntity), HttpStatus.OK);

    }

    @PostMapping("/existenciaemail")
    public ResponseEntity<Boolean> exsitenciaEmail(@RequestBody VeterinarioEntity veterinarioEntity) {
        try {

            VeterinarioEntity veterinario = veterinarioService.obtenerVeterinarioEmail(veterinarioEntity.getCorreoVeterinario());

            return ResponseEntity.status(HttpStatus.OK).body(true);

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);

        }

    }

    @PostMapping("/existenciacedula")
    public ResponseEntity<String> existenciaCedula(@RequestBody VeterinarioEntity veterinarioEntity){

        if(veterinarioService.existenciaCedula(veterinarioEntity.getCedVeterinario())){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La cedula existe");

        }else{

            return ResponseEntity.status(HttpStatus.OK).body("La cedula no existe");


        }

    }

    @PutMapping("/actualizarcontrasena")
    public ResponseEntity<String> actualizarContrasena(@RequestBody VeterinarioEntity veterinario){

        try {
            if(veterinarioService.actualizarContrasenaVeterinario(veterinario.getCorreoVeterinario(), veterinario.getPasswordVeterinario())){
                return ResponseEntity.status(HttpStatus.OK).body("La contraseña fue actualizada con exito");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La contraseña es la misma");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La contraseña no pudo ser actualizada");
        }

    }

    @PostMapping("/veterinariocedula")
    public ResponseEntity<VeterinarioEntity> traerVeterinarioCedula(@RequestBody VeterinarioEntity veterinario){

        try {

            return ResponseEntity.status(HttpStatus.OK).body(veterinarioService.buscarVeteriarioCedula(veterinario.getCedVeterinario()));

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("La contraseña no pudo ser actualizada", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/veterinariospaginador")
    public ResponseEntity<Page<VeterinarioEntity>> obtenerVeternariosPaginador(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "6")int size){

        Page<VeterinarioEntity> veterinarios = veterinarioService.obtenerVeterinariosPaginador(
                PageRequest.of(page, size));
        return new ResponseEntity<Page<VeterinarioEntity>>(veterinarios, HttpStatus.OK);
    }

    @PostMapping("/actualzarveterinarioestado")
    public ResponseEntity<String> actualizarVeterinarioEstado(@RequestBody VeterinarioEntity veterinario){

        try {

            veterinarioService.actualizarEstadoVeterinario(veterinario);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo el estado correctamente");

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("El estado no pudo ser actualizada", HttpStatus.BAD_REQUEST);
        }

    }

}
