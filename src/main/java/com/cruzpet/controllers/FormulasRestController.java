package com.cruzpet.controllers;

import com.cruzpet.entitys.FormulaEntity;
import com.cruzpet.models.FormulasModel;
import com.cruzpet.services.FormulasService;
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
@RequestMapping("/v1.0/formulas")
public class FormulasRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("formulasService")
    private FormulasService formulasService;

    @PostMapping("/crear")
    public ResponseEntity<String> agregarFormulas(@RequestBody FormulaEntity formulaEntity){
        try{
            formulasService.crear(formulaEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Se creo correctamente la formula");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo crear la formula");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarFormulas(@RequestBody FormulaEntity formulaEntity){
        try{
            formulasService.actualizar(formulaEntity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente la formula");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo actualizar la formula");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarFormulas(@PathVariable("id") Integer idFormulas){
        try{
            formulasService.eliminar(idFormulas);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente la formula");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo eliminar la formula");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<FormulasModel>> obtenerFormulas(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(formulasService.mostrarEspecialidades());
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las formulas", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/actualizarestado")
    public ResponseEntity<String> actualizarEstadoFormulas(@RequestBody FormulaEntity formulaEntity){
        try{
            formulasService.actualizarEstadoFormula(formulaEntity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente el estado de la formula");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo actualizar el estado de la formula");
        }
    }

    @PostMapping("/traerformulasmascota")
    public ResponseEntity<List<FormulaEntity>> obtenerFormulas(@RequestBody FormulaEntity formulaEntity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(formulasService.traerFormulasMascota(formulaEntity));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las formulas", HttpStatus.BAD_REQUEST);
        }
    }
}
