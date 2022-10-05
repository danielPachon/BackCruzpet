package com.cruzpet.controllers;

import com.cruzpet.entitys.BarrioEntity;
import com.cruzpet.entitys.DepartamentoEntity;
import com.cruzpet.models.DepartamentoModel;
import com.cruzpet.services.DepartamentoService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/v1.0/departamentos")
public class DepartamentoRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("departamentoService")
    private DepartamentoService departamentoService;

    @PostMapping("/crear")
    public ResponseEntity<String> agregarDepartamento(@RequestBody DepartamentoEntity departamentoEntity){
        try{
            departamentoService.crear(departamentoEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Se creo correctamente el departamento");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo crear el departamento");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarDepartamento(@RequestBody DepartamentoEntity departamentoEntity){
        try{
            departamentoService.crear(departamentoEntity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente el departamento");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo actualizar el departamento");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarDepartamento(@PathVariable("id") Integer idDepartamento){
        try{
            departamentoService.eliminar(idDepartamento);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente el departamento");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo eliminar el departamento");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<DepartamentoModel>> obtenerDepartamento(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(departamentoService.obtenerDepartamentos());
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer los departamentos", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/obtenerdepartamento")
    public ResponseEntity<DepartamentoEntity> obtenerDepartamentoNombre(@RequestBody DepartamentoEntity departamentoEntity){
        try{
            log.info(departamentoEntity.getNombreDepartamento());
            return ResponseEntity.status(HttpStatus.OK).body(departamentoService.obtenerDepartamento(departamentoEntity.getNombreDepartamento()));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer los departamentos", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/departamentospaginador")
    public ResponseEntity<Page<DepartamentoEntity>> obtenerDepartamentosPaginador(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "6")int size, @RequestParam(defaultValue = "idDepartamento") String order, @RequestParam(defaultValue = "true") boolean asc){

        Page<DepartamentoEntity> departamentos = departamentoService.obtenerDepartamentosPaginador(
                PageRequest.of(page, size, Sort.by(order)));
        if(!asc){
            departamentos = departamentoService.obtenerDepartamentosPaginador(
                    PageRequest.of(page, size, Sort.by(order).descending()));
        }
        return new ResponseEntity<Page<DepartamentoEntity>>(departamentos, HttpStatus.OK);
    }

    @GetMapping("/mostrardepartamentopalabra")
    public ResponseEntity<Page<DepartamentoEntity>> obtenerDepartamentosPaginadorPalabra(@RequestParam(defaultValue = "0")int page,
                                                                                         @RequestParam(defaultValue = "6")int size, @RequestParam("palabra") String palabra){

        try{
            List<DepartamentoEntity> departamentosSinP = departamentoService.mostrarDepartamentosPalabra(palabra);

            Pageable primero = PageRequest.of(page, size);

            int start = Math.min((int)primero.getOffset(), departamentosSinP.size());
            int end = Math.min((start + primero.getPageSize()), departamentosSinP.size());

            Page<DepartamentoEntity> departamentosConP = new PageImpl(departamentosSinP.subList(start, end), primero, departamentosSinP.size());

            return new ResponseEntity<>(departamentosConP, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity("No se pudieron traer los departamentos", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/obtenerdepartamentoid")
    public ResponseEntity<DepartamentoEntity> obtenerDepartamentoId(@RequestBody DepartamentoEntity departamentoEntity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(departamentoService.obtenerDepartamentoId(departamentoEntity));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo traer el departamento", HttpStatus.BAD_REQUEST);
        }
    }
}
