package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.CantidadEntity;
import com.cruzpet.entitys.ComprasEntity;
import com.cruzpet.entitys.ProductosEntity;
import com.cruzpet.models.ComprasModel;
import com.cruzpet.repositorys.CantidadRepository;
import com.cruzpet.repositorys.ComprasRepository;
import com.cruzpet.repositorys.ProductosRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.util.Collections.reverseOrder;

@Service("comprasservice")
public class ComprasService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    @Autowired
    @Qualifier("comprasRepository")
    private ComprasRepository comprasRepository;


    @Autowired
    @Qualifier("cantidadrepository")
    private CantidadRepository cantidadRepository;

    @Autowired
    @Qualifier("productosrepository")
    private ProductosRepository productosRepository;

    public ComprasEntity crear(ComprasEntity comprasEntity){

        try{
            comprasRepository.save(comprasEntity);
            return comprasRepository.findByIdCompra(comprasEntity.getIdCompra());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }

    }

    public void actualizarCantidadCompras(ComprasEntity comprasEntity){

        ComprasEntity compras = comprasRepository.findByIdCompra(comprasEntity.getIdCompra());

        List<ProductosEntity> productosSet = new ArrayList<>(compras.getComprasProductos());

        if(compras.getComprasProductos().size() == 0){
            productosSet.addAll(comprasEntity.getComprasProductos());

            compras.setComprasProductos(productosSet);

            comprasRepository.save(compras);
            cantidadRepository.save(new CantidadEntity(compras, comprasEntity.getComprasProductos().get(0)));
        }else{
            int igual = 0;

            for(ProductosEntity productosExistentes : productosSet){

                if(productosExistentes.getIdProducto().equals(comprasEntity.getComprasProductos().get(0).getIdProducto())){

                    CantidadEntity cantidad = cantidadRepository.findByProductos_IdProducto_AndCarritoCompras_IdCompra(comprasEntity.getComprasProductos().get(0).getIdProducto(), compras.getIdCompra());

                    cantidad.setCantidad(cantidad.getCantidad() + 1);
                    cantidadRepository.save(cantidad);
                    igual++;
                    break;

                }


            }

            if(igual == 0){
                productosSet.addAll(comprasEntity.getComprasProductos());
                compras.setComprasProductos(productosSet);
                comprasRepository.save(compras);
                cantidadRepository.save(new CantidadEntity(compras, comprasEntity.getComprasProductos().get(0)));

            }
        }

    }



    public List<ComprasModel> mostrarCompras(){
        return convertidor.convertirListaCompras(comprasRepository.findAll());
    }

    public ComprasModel mostrarComprasCliente(Integer idCompra){

        return convertidor.convertirObjetoCompras(comprasRepository.findByIdCompra(idCompra));

    }

    public String montoTotal(Integer idCarrito){

        NumberFormat monedaColombia = NumberFormat.getNumberInstance(new Locale("es", "CO"));

        List<Integer> totales =  new ArrayList<>();

        Integer totalMostrar = 0;

       ComprasEntity comprasEntity = comprasRepository.findByIdCompra(idCarrito);

       int corredor = 0;

        for(ProductosEntity productos : comprasEntity.getComprasProductos()){

            int monto = comprasEntity.getCantidades().get(corredor).getCantidad();

            int precio = parseInt(productos.getPrecioproducto()) * monto;

            String precioString = precio + "";

            log.info(precioString);

            String numero = "";

            for(int i = 0; i< precioString.length(); i++){

                if(precioString.charAt(i) != '.'){

                    numero += precioString.charAt(i);

                }


            }

            totales.add(parseInt(numero));

            corredor++;
        }

        for(Integer numero: totales){

            totalMostrar+=numero;

        }

        return monedaColombia.format(totalMostrar);

    }

    public Integer montoTotalSinPunto(Integer idCarrito){

        List<Integer> totales =  new ArrayList<>();

        Integer totalMostrar = 0;

        ComprasEntity comprasEntity = comprasRepository.findByIdCompra(idCarrito);

        int corredor = 0;

        for(ProductosEntity productos : comprasEntity.getComprasProductos()){

            int monto = comprasEntity.getCantidades().get(corredor).getCantidad();

            int precio = parseInt(productos.getPrecioproducto()) * monto;

            String precioString = precio + "";

            log.info(precioString);

            String numero = "";

            for(int i = 0; i< precioString.length(); i++){

                if(precioString.charAt(i) != '.'){

                    numero += precioString.charAt(i);

                }


            }

            totales.add(parseInt(numero));

            corredor++;
        }

        for(Integer numero: totales){

            totalMostrar+=numero;

        }

        return totalMostrar;

    }

    public void eliminarContenido(Integer idCarritoCompras){

        try{
            List<CantidadEntity> cantidades = cantidadRepository.findByCarritoCompras_IdCompra(idCarritoCompras);

            ComprasEntity comprasEntity = comprasRepository.findByIdCompra(idCarritoCompras);
            comprasEntity.setComprasProductos(new ArrayList<>());

            comprasRepository.save(comprasEntity);

            cantidadRepository.deleteAll(cantidades);

        }catch (Exception e){
            log.error(e.getMessage());
        }

    }

    public void eliminarProducto(ComprasEntity comprasRecibido){

        ComprasEntity comprasExistentes = comprasRepository.findByIdCompra(comprasRecibido.getIdCompra());

        List<ProductosEntity> productosExistentes = comprasExistentes.getComprasProductos();

        for(ProductosEntity productos : comprasExistentes.getComprasProductos()){

            if(comprasRecibido.getComprasProductos().get(0).getIdProducto().equals(productos.getIdProducto())){
                    CantidadEntity cantidad = cantidadRepository.findByProductos_IdProducto_AndCarritoCompras_IdCompra(comprasRecibido.getComprasProductos().get(0).getIdProducto(), comprasRecibido.getIdCompra());

                    productosExistentes.remove(productos);

                    comprasExistentes.setComprasProductos(productosExistentes);

                    comprasRepository.save(comprasExistentes);

                    cantidadRepository.delete(cantidad);

                    break;

            }

        }
    }

    public void reducirCantidades(ComprasEntity comprasEntity){

        CantidadEntity cantidad = cantidadRepository.findByProductos_IdProducto_AndCarritoCompras_IdCompra(comprasEntity.getComprasProductos().get(0).getIdProducto(), comprasEntity.getIdCompra());

        cantidad.setCantidad(comprasEntity.getCantidadCompras());

        cantidadRepository.save(cantidad);

    }


    public void aumentarCantidades(ComprasEntity comprasEntity){


        CantidadEntity cantidad = cantidadRepository.findByProductos_IdProducto_AndCarritoCompras_IdCompra(comprasEntity.getComprasProductos().get(0).getIdProducto(), comprasEntity.getIdCompra());

        cantidad.setCantidad(comprasEntity.getCantidadCompras());

        cantidadRepository.save(cantidad);

    }

    public CantidadEntity mostrarContadorProductoCarrito(ComprasEntity comprasEntity){

        return cantidadRepository.findByProductos_IdProducto_AndCarritoCompras_IdCompra(comprasEntity.getComprasProductos().get(0).getIdProducto(), comprasEntity.getIdCompra());

    }

}
