package com.cruzpet.controllers;

import com.cruzpet.entitys.BarrioEntity;
import com.cruzpet.entitys.BeneficiosEntity;
import com.cruzpet.entitys.EspecialidadEntity;
import com.cruzpet.models.BarrioModel;
import com.cruzpet.models.BeneficiosModel;
import com.cruzpet.services.BarrioService;
import com.cruzpet.services.BeneficiosService;
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
@RequestMapping("/v1.0/beneficios")
public class BeneficiosRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("beneficiosService")
    private BeneficiosService beneficiosService;

    @PostMapping("/crear")
    public ResponseEntity<String> agregarBeneficio(@RequestBody BeneficiosEntity beneficiosEntity){

        try{
            beneficiosService.insertar(beneficiosEntity);
            return new ResponseEntity("El beneficio se creo correctamente", HttpStatus.CREATED);

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo crear el beneficio", HttpStatus.BAD_REQUEST);

        }

    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarBeneficio(@RequestBody BeneficiosEntity beneficiosEntity){

        try{

            beneficiosService.actualizar(beneficiosEntity);
            return new ResponseEntity("El beneficio se actualizo correctamente", HttpStatus.OK);

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("El beneficio no se pudo actualizar", HttpStatus.BAD_REQUEST);
        }


    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarBeneficio(@PathVariable("id") Integer idBeneficio){

        try{
            beneficiosService.eliminar(idBeneficio);
            return new ResponseEntity("El beneficio se elimino correctamente", HttpStatus.OK);
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("El beneficio no se pudo eliminar", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/")
    public ResponseEntity<List<BeneficiosModel>> obtenerBeneficios(){

        try {

            return new ResponseEntity(beneficiosService.traerBeneficios(), HttpStatus.OK);
        }catch (Exception ex){

            return new ResponseEntity("Hubo un errror en traer los beneficios", HttpStatus.BAD_REQUEST);

        }


    }

    @PostMapping("/traerbeneficioid")
    public ResponseEntity<BeneficiosEntity> mostrarBeneficiosId(@RequestBody BeneficiosEntity beneficiosEntity){

        try{
            return new ResponseEntity(beneficiosService.traerBenficioId(beneficiosEntity), HttpStatus.CREATED);

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo crear el beneficio", HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping("/existenciabeneficio")
    public ResponseEntity<Boolean> existenciaBeneficioNombre (@RequestBody BeneficiosEntity beneficiosEntity){

        try{
            return new ResponseEntity(beneficiosService.existenciaBeneficioNombre(beneficiosEntity), HttpStatus.CREATED);

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo crear el beneficio", HttpStatus.BAD_REQUEST);

        }

    }

    @GetMapping("/beneficiospaginador")
    public ResponseEntity<Page<BeneficiosEntity>> obtenerBeneficiosPaginador(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "6")int size){

        Page<BeneficiosEntity> beneficios = beneficiosService.obtenerBeneficiosPaginador(
                PageRequest.of(page, size));

        return new ResponseEntity<Page<BeneficiosEntity>>(beneficios, HttpStatus.OK);
    }

    @PutMapping("/actualizarbeneficiosinestado")
    public ResponseEntity<String> actualizarBeneficioSinEstado(@RequestBody BeneficiosEntity beneficiosEntity){

        try{

            beneficiosService.actualizarBeneficioSinEstado(beneficiosEntity);
            return new ResponseEntity("El beneficio se actualizo correctamente", HttpStatus.OK);

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("El beneficio no se pudo actualizar", HttpStatus.BAD_REQUEST);
        }


    }

}
