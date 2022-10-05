package com.cruzpet.controllers;

import com.cruzpet.entitys.BarrioEntity;
import com.cruzpet.entitys.CodigoReferenciaEntity;
import com.cruzpet.models.BarrioModel;
import com.cruzpet.models.CodigoReferenciaModel;
import com.cruzpet.services.BarrioService;
import com.cruzpet.services.CodigoReferenciaService;
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
@RequestMapping("/v1.0/codigoreferencia")
public class CodigoReferenciaRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("codigoreferenciaservice")
    private CodigoReferenciaService codigoReferenciaService;

    @PostMapping("/crear")
    public ResponseEntity<CodigoReferenciaModel> agregarCodigoReferencia(@RequestBody CodigoReferenciaEntity codigoReferenciaEntity){

        try{
            codigoReferenciaService.crear(codigoReferenciaEntity);
            return new ResponseEntity(codigoReferenciaEntity, HttpStatus.CREATED);

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo crear el codigo de refencia", HttpStatus.BAD_REQUEST);

        }

    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarCodigoReferencia(@RequestBody CodigoReferenciaEntity codigoReferenciaEntity){

        try{

            codigoReferenciaService.actualizar(codigoReferenciaEntity);
            return new ResponseEntity("El codigo de referencia se actualizo correctamente", HttpStatus.OK);

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("El codigo de referencia no se pudo actualizar", HttpStatus.BAD_REQUEST);
        }


    }

    @GetMapping("/")
    public ResponseEntity<List<CodigoReferenciaModel>> obtenerCodigoReferencias(){

        try {

            return new ResponseEntity(codigoReferenciaService.mostrarCodigosReferencia(), HttpStatus.OK);
        }catch (Exception ex){

            return new ResponseEntity("Hubo un errror en traer los codigos de referencia", HttpStatus.BAD_REQUEST);

        }


    }

    @PostMapping("/cliente")
    public ResponseEntity<List<CodigoReferenciaModel>> obtenerCodigoReferenciasClientes(@RequestBody CodigoReferenciaEntity codigoReferencia){

        try {

            return new ResponseEntity(codigoReferenciaService.mostrarCodigosRerenciaCliente(codigoReferencia.getCodigoReferenciaCliente().getCedulaCliente()), HttpStatus.OK);
        }catch (Exception ex){

            return new ResponseEntity("Hubo un errror en traer los codigos de referencia", HttpStatus.BAD_REQUEST);

        }


    }

}
