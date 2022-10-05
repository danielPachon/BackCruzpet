package com.cruzpet.controllers;

import com.cruzpet.entitys.AdministradorEntity;
import com.cruzpet.entitys.IpsaEntity;
import com.cruzpet.entitys.RolEntity;
import com.cruzpet.entitys.VeterinarioEntity;
import com.cruzpet.models.AdministradorModel;
import com.cruzpet.models.IpsasModel;
import com.cruzpet.security.dto.JwtDto;
import com.cruzpet.security.jwt.JwtProvider;
import com.cruzpet.services.IpsasService;
import com.cruzpet.services.RolService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/v1.0/ipsas")
public class IpsasRestController {

    Log logger = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("ipasasService")
    private IpsasService ipsasService;

    @PostMapping("/crear")
    public ResponseEntity<?> agregarVeterinario(@RequestBody IpsaEntity ipsa, BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return new ResponseEntity("Campos mal puestos o email invalido", HttpStatus.BAD_REQUEST);

        }

        if (ipsasService.existenciaIpsa(ipsa.getCorreoIpsa())){

            return new ResponseEntity("El email ya existe", HttpStatus.BAD_REQUEST);

        }

        try {
            ipsasService.crear(ipsa);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity("El veterinario ha sido creado con exito", HttpStatus.CREATED);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarIpsa(@RequestBody IpsaEntity ipsa){
        try{
            ipsasService.actualizar(ipsa);
            return ResponseEntity.status(HttpStatus.OK).body("La ipsa fue actualizada con exito");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La ipsa no se pudo actualizar");
        }
    }

    @DeleteMapping("/eliminar/{rut}")
    public ResponseEntity<String> borrarIpsa(@PathVariable("rut") Integer rut){
        try {
            ipsasService.borrar(rut);
            return ResponseEntity.status(HttpStatus.OK).body("La ipsa fue borrada con exito");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La ipsa no se pudo borrar");
        }
    }

    @GetMapping("/")
    public List<IpsasModel> obtenerIpsas(){
        return ipsasService.obtenerIpsas();
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> liginIpsa(@RequestBody IpsaEntity ipsa, BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return new ResponseEntity("Campos mal puestos o email invalido o password invalido ", HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity(ipsasService.loginIpsa(ipsa), HttpStatus.OK);

    }

    @PostMapping("/existenciaemail")
    public ResponseEntity<Boolean> exsitenciaEmail(@RequestBody IpsaEntity ipsaEntity) {
        try {

            IpsasModel ipsasModel = ipsasService.buescarIpsaEmail(ipsaEntity.getCorreoIpsa());

            return ResponseEntity.status(HttpStatus.OK).body(true);

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);

        }

    }

    @PostMapping("/ipsarut")
    public  ResponseEntity<IpsasModel> ipsaRut(@RequestBody IpsaEntity ipsaEntity){

        try{

            return ResponseEntity.status(HttpStatus.OK).body(ipsasService.obtenerIpsaRut(ipsaEntity.getRut()));

        }catch (Exception exception){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }

    }

    @PutMapping("/actualizarcontrasena")
    public ResponseEntity<String> actualizarContrasena(@RequestBody IpsaEntity ipsa){

        try {
            if(ipsasService.actualizarContrasenaIpsa(ipsa.getCorreoIpsa(), ipsa.getPasswordIpsa())){
                return ResponseEntity.status(HttpStatus.OK).body("La contraseña fue actualizada con exito");
            }else{
                return ResponseEntity.status(HttpStatus.OK).body("La contraseña es la misma");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La contraseña no pudo ser actualizada");
        }

    }

    @PostMapping("/traeripsasdepartamento")
    public ResponseEntity<List<IpsasModel>> ipsasDepartamento(@RequestBody IpsaEntity ipsa){
        try{

            return ResponseEntity.status(HttpStatus.OK).body(ipsasService.obtenerIpsasDepartamento(ipsa));

        }catch (Exception exception){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }

    }

    @PostMapping("/traeripsanombre")
    public ResponseEntity<IpsaEntity> buscarNombreIpsa(@RequestBody IpsaEntity ipsa){
        try{

            return ResponseEntity.status(HttpStatus.OK).body(ipsasService.buscarIpsaNombre(ipsa));

        }catch (Exception exception){

            return new ResponseEntity("Hubo un error en traer la ipsa", HttpStatus.BAD_REQUEST);

        }

    }

    @PutMapping("/actualizarsincontralogo")
    public ResponseEntity<String> actualizarSinLogoPassword(@RequestBody IpsaEntity ipsa){
        try{
            ipsasService.actualizarSinContra(ipsa);
            return ResponseEntity.status(HttpStatus.OK).body("La ipsa fue actualizada con exito");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La ipsa no se pudo actualizar");
        }

    }

    @GetMapping("/ipsaspaginador")
    public ResponseEntity<Page<IpsaEntity>> obtenerIpsasPaginador(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "6")int size){

        Page<IpsaEntity> ipsas = ipsasService.obtenerIpsasPaginador(
                PageRequest.of(page, size));
        return new ResponseEntity<Page<IpsaEntity>>(ipsas, HttpStatus.OK);
    }
}
