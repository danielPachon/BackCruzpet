package com.cruzpet.controllers;

import com.cruzpet.entitys.ClienteEntity;
import com.cruzpet.entitys.DepartamentoEntity;
import com.cruzpet.entitys.RolEntity;
import com.cruzpet.entitys.VeterinarioEntity;
import com.cruzpet.models.ClienteModel;
import com.cruzpet.security.dto.JwtDto;
import com.cruzpet.security.jwt.JwtProvider;
import com.cruzpet.services.ClienteService;
import com.cruzpet.services.RolService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/v1.0/clientes")
public class ClientesRestController {

    Log logger = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("clienteService")
    private ClienteService clienteService;

    @PostMapping("/crear")
    public ResponseEntity<?> agregarCliente(@RequestBody ClienteEntity clienteEntity, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return new ResponseEntity("Campos mal puestos o email invalido", HttpStatus.BAD_REQUEST);

        }

        if (clienteService.existenciaCliente(clienteEntity.getEmail())) {

            return new ResponseEntity("El email ya existe", HttpStatus.BAD_REQUEST);

        }
        try {
            clienteService.crear(clienteEntity);
            return new ResponseEntity("El cliente ha sido creado con exito", HttpStatus.CREATED);
        } catch (Exception ex) {

            logger.error(ex.getMessage());
            return new ResponseEntity("El cliente no se pud crear", HttpStatus.BAD_REQUEST);

        }

    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarCliente(@RequestBody ClienteEntity clienteEntity) {
        try {
            clienteService.actualizar(clienteEntity);
            return ResponseEntity.status(HttpStatus.OK).body("El cliente fue actualizado con exito");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El cliente no se pudo actualizar");
        }
    }

    @PutMapping("/actualizaradmin")
    public ResponseEntity<String> actualizarClienteAdministrador(@RequestBody ClienteEntity clienteEntity) {
        try {
            clienteService.actualizarAdministrador(clienteEntity);
            return ResponseEntity.status(HttpStatus.OK).body("El cliente fue actualizado con exito");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El cliente no se pudo actualizar");
        }
    }

    @DeleteMapping("/eliminar/{cedula}")
    public ResponseEntity<String> borrarCliente(@PathVariable("cedula") String cedula) {
        try {
            clienteService.borrar(cedula);
            return ResponseEntity.status(HttpStatus.OK).body("El cliente fue borrado con exito");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El cliente no se pudo borrar");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<ClienteModel>> obtenerClientes() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clienteService.obtenerClientes());
        } catch (Exception ex) {

            logger.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer los clientes", HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@RequestBody ClienteEntity clienteEntity, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return new ResponseEntity("Campos mal puestos o email invalido o password invalido ", HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity(clienteService.login(clienteEntity), HttpStatus.OK);

    }

    @GetMapping("/{cedula}")
    public ResponseEntity<ClienteModel> traerCliente(@PathVariable("cedula") String cedula) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clienteService.obtenerClienteIdentificador(cedula));
        } catch (Exception ex) {

            logger.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer los clientes", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/existenciaemail")
    public ResponseEntity<Boolean> exsitenciaEmail(@RequestBody ClienteEntity clienteEntity) {
        try {

            ClienteModel cliente = clienteService.buescarClienteEmail(clienteEntity.getEmail());

            logger.error(cliente.getEmail());

            return ResponseEntity.status(HttpStatus.OK).body(true);

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);

        }

    }

    @PostMapping("/traercedula")
    public ResponseEntity<ClienteEntity> buscarClienteCedula(@RequestBody ClienteEntity clienteEntity){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(clienteService.obtenerClienteCedula(clienteEntity.getCedulaCliente()));

        }catch (Exception ex){

            return new ResponseEntity("No existe el cliente", HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping("/existenciacedula")
    public ResponseEntity<String> exsitenciaCedula(@RequestBody ClienteEntity clienteEntity) {
        try {


            if(clienteService.existenciaClienteCedula(clienteEntity.getCedulaCliente())){
                return ResponseEntity.status(HttpStatus.OK).body("Existe el Cliente");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existe el Cliente");
            }

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

        }

    }

    @PostMapping("/buscarcliente")
    public ResponseEntity<ClienteModel> traerUsuario(@RequestBody ClienteEntity clienteEntity) {
        try {

            ClienteModel cliente = clienteService.buscarClienteUsername(clienteEntity.getUsername());


            return ResponseEntity.status(HttpStatus.OK).body(cliente);

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }

    }

    @PutMapping("/actualizarcontrasena")
    public ResponseEntity<String> actualizarContrasena(@RequestBody ClienteEntity clienteEntity){

        try {
            if(clienteService.actualizarContrasenaCliente(clienteEntity.getEmail(), clienteEntity.getPassword())){
                return ResponseEntity.status(HttpStatus.OK).body("La contraseña fue actualizada con exito");
            }else{
                return ResponseEntity.status(HttpStatus.OK).body("La contraseña es la misma");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La contraseña no pudo ser actualizada");
        }

    }


    @PutMapping("/actualizarimagen")
    public ResponseEntity<String> actualizarImafeb(@RequestBody ClienteEntity clienteEntity){

        try {
            if(clienteService.actualizarImagenCliente(clienteEntity)){
                return ResponseEntity.status(HttpStatus.OK).body("La imagen fue actualizada con exito");
            }else{
                return ResponseEntity.status(HttpStatus.OK).body("La imagen no se pudo actualizar");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La imagen no pudo ser actualizada");
        }

    }

    @PostMapping("/buscarclienteemail")
    public ResponseEntity<ClienteModel> traerUsuarioEmail(@RequestBody ClienteEntity clienteEntity) {
        try {

            ClienteModel cliente = clienteService.buescarClienteEmail(clienteEntity.getEmail());


            return ResponseEntity.status(HttpStatus.OK).body(cliente);

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }

    }

    @PostMapping("/buscarclientenombres")
    public ResponseEntity<List<ClienteModel>> traerUsuarioNombres(@RequestBody ClienteEntity clienteEntity) {
        try {

            List<ClienteModel> cliente = clienteService.buscarClienteNombre(clienteEntity.getNombres());


            return ResponseEntity.status(HttpStatus.OK).body(cliente);

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }

    }

    @PostMapping("/buscarclienteapellidos")
    public ResponseEntity<List<ClienteModel>> traerUsuarioApellidos(@RequestBody ClienteEntity clienteEntity) {
        try {

            List<ClienteModel> cliente = clienteService.buscarClienteApellidos(clienteEntity.getApellidos());


            return ResponseEntity.status(HttpStatus.OK).body(cliente);

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }

    }

    @PostMapping("/buscarclienteestado")
    public ResponseEntity<List<ClienteModel>> traerUsuarioEstado(@RequestBody ClienteEntity clienteEntity) {
        try {

            List<ClienteModel> cliente = clienteService.buscarClienteEstado(clienteEntity.getEstado());


            return ResponseEntity.status(HttpStatus.OK).body(cliente);

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }

    }

    @PostMapping("/actualizarcedula")
    public ResponseEntity<List<String>> actualizarCedula(@RequestBody ClienteEntity clienteEntity){

        try{

            clienteService.actualizarCedula(clienteEntity);
            return new ResponseEntity("Se actualizo correctamente la cedula", HttpStatus.OK);

        }catch (Exception ex){

            logger.error(ex.getMessage());
            return new ResponseEntity("Hubo un error en actualizar la cedula", HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping("/actualizartodoepsa")
    public ResponseEntity<List<String>> actualizarTodoEpsa(@RequestBody ClienteEntity clienteEntity){

        try{

            clienteService.actualizarTodo(clienteEntity);
            return new ResponseEntity("Se actualizo correctamente", HttpStatus.OK);

        }catch (Exception ex){

            logger.error(ex.getMessage());
            return new ResponseEntity("Hubo un error en actualizar", HttpStatus.BAD_REQUEST);

        }

    }

    @GetMapping("/clientespaginador")
    public ResponseEntity<Page<ClienteEntity>> obtenerClientesPaginador(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "6")int size){

        Page<ClienteEntity> clientes = clienteService.obtenerClientesPaginador(
                PageRequest.of(page, size));
        return new ResponseEntity<Page<ClienteEntity>>(clientes, HttpStatus.OK);
    }

    @GetMapping("/mostrarclientepalabra")
    public ResponseEntity<Page<ClienteEntity>> obtenerClientesPaginadorPalabra(@RequestParam(defaultValue = "0")int page,
                                                                                         @RequestParam(defaultValue = "6")int size, @RequestParam("palabra") String palabra){

        try{
            List<ClienteEntity> clientesSinP = clienteService.mostrarClientesNombre(palabra);

            Pageable primero = PageRequest.of(page, size);

            int start = Math.min((int)primero.getOffset(), clientesSinP.size());
            int end = Math.min((start + primero.getPageSize()), clientesSinP.size());

            Page<ClienteEntity> clientesConP = new PageImpl(clientesSinP.subList(start, end), primero, clientesSinP.size());

            return new ResponseEntity<>(clientesConP, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity("No se pudieron traer los departamentos", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/mostrarclientepalabradocumento")
    public ResponseEntity<Page<ClienteEntity>> obtenerClientesPaginadorPalabraDocumento(@RequestParam(defaultValue = "0")int page,
                                                                               @RequestParam(defaultValue = "6")int size, @RequestParam("palabra") String palabra){

        try{
            List<ClienteEntity> clientesSinP = clienteService.mostrarClientesDocumento(palabra);

            Pageable primero = PageRequest.of(page, size);

            int start = Math.min((int)primero.getOffset(), clientesSinP.size());
            int end = Math.min((start + primero.getPageSize()), clientesSinP.size());

            Page<ClienteEntity> clientesConP = new PageImpl(clientesSinP.subList(start, end), primero, clientesSinP.size());

            return new ResponseEntity<>(clientesConP, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity("No se pudieron traer los departamentos", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/mostrarclientepalabraciudad")
    public ResponseEntity<Page<ClienteEntity>> obtenerClientesPaginadorPalabraCiudad(@RequestParam(defaultValue = "0")int page,
                                                                                        @RequestParam(defaultValue = "6")int size, @RequestParam("palabra") String palabra){

        try{
            List<ClienteEntity> clientesSinP = clienteService.mostrarClientesCiudad(palabra);

            Pageable primero = PageRequest.of(page, size);

            int start = Math.min((int)primero.getOffset(), clientesSinP.size());
            int end = Math.min((start + primero.getPageSize()), clientesSinP.size());

            Page<ClienteEntity> clientesConP = new PageImpl(clientesSinP.subList(start, end), primero, clientesSinP.size());

            return new ResponseEntity<>(clientesConP, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity("No se pudieron traer los departamentos", HttpStatus.BAD_REQUEST);
        }

    }

}
