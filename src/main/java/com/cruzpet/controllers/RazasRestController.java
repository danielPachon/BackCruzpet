package com.cruzpet.controllers;

import com.cruzpet.entitys.RazaEntity;
import com.cruzpet.models.RazaModel;
import com.cruzpet.services.RazaService;
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

//ESTA CLASE SERA UN CONTROLADOR REST(QUE RECIBE LAS PETICIONES HTTPS) DE LA API, TIENE RELACION CON LA URL

@RestController //INDICAMOS QUE ES UN CONTROLADOR PARA APIS, NO RETORNARA VISTA
@CrossOrigin
@RequestMapping("/v1.0/razas") //INDICAMOS EN QUE RUTA ESTARA PARA ACCEDER A LOS CONTROLADORES DE ESTA CLASE
public class RazasRestController {

    Log log = LogFactory.getLog(getClass());

    //ESTAMOS INYECTANDO EL SERVICIO DE LA CLASE PARA PODER INTERACTUAR CON LA LOGICA DE DICHA CLASE
    @Autowired
    @Qualifier("razaService") //LE INDICAMOS ESPECIFICAMENTE QUE SERVICIO NECESITAMOS
    private RazaService razaService;

    //PUT, GET, DELETE O POST MAPPING, SON LOS METODOS QUE SE PEDIRAN POR MEDIO DE LA URL AL SISTEMA
    @PostMapping("/crear")
    //EL @RequestBody INDICA QUE NOS MANDE LOS DATOS DEL CUERPO DE LA PETICION, NORMALMENTE ES UN JSON
    public ResponseEntity<String> agregarRaza(@RequestBody RazaEntity raza) {
        try{
            razaService.crear(raza);
            return ResponseEntity.status(HttpStatus.CREATED).body("La raza fue registrada con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al registrar la raza");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarCategoria(@RequestBody RazaEntity raza){
        try{
            razaService.actualizar(raza);
            return ResponseEntity.status(HttpStatus.OK).body("La raza fue registrado con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al registrar la raza");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    //EL @PathVariable INDICA A LA VARIBALE QUE MANDAREMOS POR MEDIO DE LA URL, ESTA VARIABLE TIENE CONTENIDO
    public ResponseEntity<String> borrarRaza(@PathVariable("id") int id){
        try{
            razaService.borrar(id);
            return ResponseEntity.status(HttpStatus.OK).body("La raza fue eliminada con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al eliminar la raza");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<RazaModel>> obtenerRazas(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(razaService.obtenerRazas());
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer las razas", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/traerrazanombre")
    public ResponseEntity<RazaEntity> traerRazaNombre(@RequestBody RazaEntity razaEntity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(razaService.buscarRazaNombre(razaEntity));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo traer las razas", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/traerrazaidtipo")
    public ResponseEntity<List<RazaEntity>> traerRazaTipoMascota(@RequestBody RazaEntity razaEntity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(razaService.buscarRazsaTipoMascota(razaEntity));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo traer las razas", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/razaspaginador")
    public ResponseEntity<Page<RazaEntity>> obtenerRazasPaginador(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "6")int size){

        Page<RazaEntity> razas = razaService.obtenerRazasPaginador(
                PageRequest.of(page, size));
        return new ResponseEntity<Page<RazaEntity>>(razas, HttpStatus.OK);
    }

    @PostMapping("/obtenerrazaid")
    public ResponseEntity<RazaEntity> obtenerRazasId(@RequestBody RazaEntity razaEntity){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(razaService.obtenerRazaId(razaEntity));
        }catch (Exception ex){

            log.info(ex.getMessage());
            return null;

        }

    }
}
