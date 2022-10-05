package com.cruzpet.controllers;

import com.cruzpet.entitys.ClienteEntity;
import com.cruzpet.entitys.MedicamentoEntity;
import com.cruzpet.models.MedicamentosModel;
import com.cruzpet.services.MedicamentoService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/v1.0/medicamentos")
public class MedicamentosRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("medicamentoService")
    private MedicamentoService medicamentoService;

    @PostMapping("/crear")
    public ResponseEntity<String> agregarMedicamento(@RequestBody MedicamentoEntity medicamentoEntity){
        try{
            medicamentoService.crear(medicamentoEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("La mascota fue registrado con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al registrar la mascota");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarMedicamento(@RequestBody MedicamentoEntity medicamentoEntity){
        try{
            medicamentoService.actualizar(medicamentoEntity);
            return ResponseEntity.status(HttpStatus.OK).body("El medicamento fue actualizada con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al actualizar el medicamento");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarMedicamento(@PathVariable("id") Integer idMedicamento){
        try{
            medicamentoService.borrar(idMedicamento);
            return ResponseEntity.status(HttpStatus.OK).body("El medicamento fue borrado con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al borrar el medicamento");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<MedicamentosModel>> obtenerMedicamento(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(medicamentoService.obtenerMedicamentos());
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer los medicamentos", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/traermedicamentoid")
    public ResponseEntity<MedicamentoEntity> obtenerMedicamentoId(@RequestBody MedicamentoEntity medicamentoEntity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(medicamentoService.obtenerMedicamentoId(medicamentoEntity));
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity("Ubo un error", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/traermedicamentospaginador")
    public ResponseEntity<Page<MedicamentoEntity>> obtenerMedicamentosPaginador(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "6")int size){

        Page<MedicamentoEntity> medicamentos = medicamentoService.obtenerMedicamentosPaginador(
                PageRequest.of(page, size));
        return new ResponseEntity<Page<MedicamentoEntity>>(medicamentos, HttpStatus.OK);
    }}
