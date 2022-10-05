package com.cruzpet.controllers;

import com.cruzpet.entitys.ClienteEntity;
import com.cruzpet.entitys.ProductosEntity;
import com.cruzpet.models.ProductosModel;
import com.cruzpet.services.ProductosService;
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

@RestController
@RequestMapping("/v1.0/productos")
public class ProductosRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("productosService")
    private ProductosService productosService;

    @PostMapping("/crear")
    public ResponseEntity<String> agregarProducto(@RequestBody ProductosEntity productosEntityo){

        try{
            productosService.crear(productosEntityo);
            return ResponseEntity.status(HttpStatus.CREATED).body("El producto fue creada con exito");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hubo un error en la creacion");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarProducto(@RequestBody ProductosEntity productosEntity){

        try{

            productosService.actualizar(productosEntity);
            return ResponseEntity.status(HttpStatus.OK).body("El producto fue actualizado con exito");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hubo un error en la actualizacion del producto");

        }

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarProducto(@PathVariable("id") Integer idProducto){
        try{

            productosService.eliminar(idProducto);
            return ResponseEntity.status(HttpStatus.OK).body("El producto fue borrado con exito");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hubo un error en la eliminacion del producto");

        }
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductosModel>> obtenerProductos(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productosService.mostrarProductos());
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer los productos", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductosModel> obtenerProducto(@PathVariable("id")Integer id){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(productosService.mostrarProducto(id));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo traer el producto", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/productonombre")
    public  ResponseEntity<ProductosModel> obtenerProductoNombre(@RequestBody ProductosEntity productosEntity){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(productosService.mostrarProductoNombre(productosEntity.getNombreProducto()));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo traer el producto", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/productopalabraclave/{clave}")
    public ResponseEntity<List<ProductosModel>> obtenerProductoPalabraClave(@PathVariable("clave") String palabraClave){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(productosService.mostrarProductosPlabra(palabraClave));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo traer el producto", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/productosipsa")
    public ResponseEntity<List<ProductosModel>> obtenerProductosIpsa(@RequestBody ProductosEntity productosEntity){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(productosService.traerProductosIpsa(productosEntity.getIpsaEntity().getRut()));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo traer el producto", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/mostrarpopularidad")
    private ResponseEntity<List<ProductosModel>> obtenerProductosRango(){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(productosService.traerProductosMayorMenor());
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudo traer el producto", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/productospaginador")
    public ResponseEntity<Page<ProductosEntity>> obtenerProductosPaginador(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "6")int size){

        Page<ProductosEntity> productos = productosService.obtenerProductosPaginador(
                PageRequest.of(page, size));
        return new ResponseEntity<Page<ProductosEntity>>(productos, HttpStatus.OK);
    }

    @PutMapping("/actualizarepsa")
    public ResponseEntity<String> actualizarProductoEpsa(@RequestBody ProductosEntity productosEntity){

        try{
            productosService.actualizarEpsa(productosEntity);
            return ResponseEntity.status(HttpStatus.OK).body("El producto fue actualizado con exito");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hubo un error en la actualizacion del producto");

        }

    }
}
