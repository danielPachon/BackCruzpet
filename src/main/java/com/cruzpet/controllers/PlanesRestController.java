package com.cruzpet.controllers;

import com.cruzpet.entitys.BeneficiosEntity;
import com.cruzpet.entitys.FormulaEntity;
import com.cruzpet.entitys.PlanesEntity;
import com.cruzpet.models.FormulasModel;
import com.cruzpet.models.PlanesModel;
import com.cruzpet.services.PlanesService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/v1.0/planes")
public class PlanesRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("planesservice")
    private PlanesService planesService;

    @PostMapping("/crear")
    public ResponseEntity<String> agregarPlan(@RequestBody PlanesEntity planesEntity){
        try{
            planesService.crear(planesEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("El plan fue registrado con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al registrar el plan");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarPlanes(@RequestBody PlanesEntity planesEntity){
        try{
            planesService.actualizar(planesEntity);
            return ResponseEntity.status(HttpStatus.OK).body("El plan fue actualizado con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al actualizar el plan");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarPlanes(@PathVariable("id") Integer idPlanes){
        try{
            planesService.borrar(idPlanes);
            return ResponseEntity.status(HttpStatus.OK).body("El plan fue eliminado con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al eliminar el plan");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<PlanesModel>> obtenerPlanes(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(planesService.obtenerPlanes());
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer los planes", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/plan/{id}")
    public ResponseEntity<PlanesModel> obtenerPlan(@PathVariable("id") Integer idPlan){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(planesService.obtenerPlanId(idPlan));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo traer el plan", HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/plan/beneficios")
    public ResponseEntity<PlanesModel> obtenerPlanBeneficios(@RequestBody PlanesEntity planesEntity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(planesService.obtenerBeneficiosPlanes(planesEntity));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo traer el plan", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/traerplannombre")
    public ResponseEntity<PlanesEntity> traerPlanId(@RequestBody PlanesEntity planesEntity){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(planesService.obtenerPlanTitulo(planesEntity));
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity("No se pudo traer el plan", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/traerbeneficiosfaltante")
    public ResponseEntity<List<BeneficiosEntity>> traerBeneficiosFaltantes(@RequestBody PlanesEntity planesEntity){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(planesService.obtenerBeneficiosFaltantes(planesEntity));
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity("No se pudieron traer los planes faltantes", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/traerbeneficiosconservados")
    public ResponseEntity<List<BeneficiosEntity>> traerBeneficiosConservados(@RequestBody PlanesEntity planesEntity){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(planesService.obtenerBeneficiosConservados(planesEntity));
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity("No se pudieron traer los planes faltantes", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/actualizarsinbeneficios")
    public ResponseEntity<String> actualizarPlanesSinBenficios(@RequestBody PlanesEntity planesEntity){
        try{
            planesService.actualizarSinBenficios(planesEntity);
            return ResponseEntity.status(HttpStatus.OK).body("El plan fue actualizado con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al actualizar el plan");
        }
    }

    @PostMapping("/quitarbeneficios")
    public ResponseEntity<String> quitarBeneficios(@RequestBody PlanesEntity planesEntity){

        try{
            planesService.quitarBeneficios(planesEntity);
            return ResponseEntity.status(HttpStatus.OK).body("Se quitaron los beneficios con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error en quitar los beneficios");
        }

    }

    @PostMapping("/agregarbeneficios")
    public ResponseEntity<String> agregarBeneficios(@RequestBody PlanesEntity planesEntity){

        try{
            planesService.agregarBeneficios(planesEntity);
            return ResponseEntity.status(HttpStatus.OK).body("Se agregaron los beneficios con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error en agregar los beneficios");
        }

    }
}
