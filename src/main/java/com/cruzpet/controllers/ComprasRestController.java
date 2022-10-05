package com.cruzpet.controllers;

import com.cruzpet.entitys.BarrioEntity;
import com.cruzpet.entitys.CantidadEntity;
import com.cruzpet.entitys.ComprasEntity;
import com.cruzpet.models.BarrioModel;
import com.cruzpet.models.ComprasModel;
import com.cruzpet.services.BarrioService;
import com.cruzpet.services.ComprasService;
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
@RequestMapping("/v1.0/compras")
public class ComprasRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("comprasservice")
    private ComprasService comprasService;

    @PostMapping("/crear")
    public ResponseEntity<String> agregarCompra(@RequestBody ComprasEntity comprasEntity){

        try{
            comprasService.crear(comprasEntity);
            return new ResponseEntity("El carrito de compras se creo correctamente", HttpStatus.CREATED);

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo crear el carrito de compras", HttpStatus.BAD_REQUEST);

        }

    }

    @PutMapping("/actualizarcontenido")
    public ResponseEntity<String> actualizarContenidoCarritoCompras(@RequestBody ComprasEntity comprasEntity){

        try{

            comprasService.actualizarCantidadCompras(comprasEntity);
            return new ResponseEntity("El contenido del carrito de compras se actualizo correctamente", HttpStatus.OK);

        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("El contenido del carrito de compras no se pudo actualizar", HttpStatus.BAD_REQUEST);
        }


    }


    @GetMapping("/")
    public ResponseEntity<List<ComprasModel>> obtenerCarritos(){

        try {

            return new ResponseEntity(comprasService.mostrarCompras(), HttpStatus.OK);
        }catch (Exception ex){

            return new ResponseEntity("Hubo un errror en traer los carritos de compras", HttpStatus.BAD_REQUEST);

        }


    }

    @PostMapping("/carritocliente")
    public ResponseEntity<ComprasModel> obtenerCarritoCliente(@RequestBody ComprasEntity comprasEntity){

        try {

            return new ResponseEntity(comprasService.mostrarComprasCliente(comprasEntity.getIdCompra()), HttpStatus.OK);
        }catch (Exception ex){

            return new ResponseEntity("Hubo un errror en traer los carritos de compras", HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping("/montototal")
    public ResponseEntity<String> obtenerMontoTotalProductos(@RequestBody ComprasEntity comprasEntity){

        try {

            return new ResponseEntity(comprasService.montoTotal(comprasEntity.getIdCompra()), HttpStatus.OK);
        }catch (Exception ex){

            return new ResponseEntity("Hubo un errror en traer el monto total", HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping("/eliminarcontenidocarrito")
    public ResponseEntity<String> eliminarProductosCarrito(@RequestBody ComprasEntity comprasEntity){

        try {

            comprasService.eliminarContenido(comprasEntity.getIdCompra());
            return new ResponseEntity("Se eliminaron correctamente los productos del carrito", HttpStatus.OK);
        }catch (Exception ex){

            return new ResponseEntity("Hubo un errror en eliminar los productos del carrito", HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping("/eliminarproducto")
    private ResponseEntity<String> eliminarProductoCarrito(@RequestBody ComprasEntity comprasEntity){

        try {

            comprasService.eliminarProducto(comprasEntity);
            return new ResponseEntity("Se elimino correctamente el producto del carrito", HttpStatus.OK);
        }catch (Exception ex){

            return new ResponseEntity("Hubo un errror en eliminar el producto del carrito", HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/aumentar")
    private ResponseEntity<String> aumentarCantidad(@RequestBody ComprasEntity comprasEntity){

        try{

            comprasService.aumentarCantidades(comprasEntity);

            return new ResponseEntity("Se aumento correctamente la cantidad del producto del carrito", HttpStatus.OK);

        }catch (Exception ex){

            log.error(ex.getMessage());
            log.error(ex.getCause());

            return new ResponseEntity("No se pudo aumentar la cantidad del producto del carrito", HttpStatus.BAD_REQUEST);

        }

    }
    @PostMapping("/reducir")
    private ResponseEntity<String> reducirCantidad(@RequestBody ComprasEntity comprasEntity){

        try{

            comprasService.reducirCantidades(comprasEntity);

            return new ResponseEntity("Se redujo correctamente la cantidad del producto del carrito", HttpStatus.OK);

        }catch (Exception ex){

            log.error(ex.getMessage());

            return new ResponseEntity("No se pudo reducir la cantidad del producto del carrito", HttpStatus.OK);

        }

    }

    @PostMapping("/cantidad")
    public ResponseEntity<CantidadEntity> mostrarCantidad(@RequestBody ComprasEntity comprasEntity){

        try{

            return new ResponseEntity(comprasService.mostrarContadorProductoCarrito(comprasEntity), HttpStatus.OK);

        }catch (Exception ex){

            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping("/mostrartotalsinpunto")
    public ResponseEntity<Integer> obtenerMontoTotalProductosSinPunto(@RequestBody ComprasEntity comprasEntity){

        try {

            return new ResponseEntity(comprasService.montoTotalSinPunto(comprasEntity.getIdCompra()), HttpStatus.OK);
        }catch (Exception ex){

            return new ResponseEntity("Hubo un errror en traer el monto total", HttpStatus.BAD_REQUEST);

        }

    }
}
