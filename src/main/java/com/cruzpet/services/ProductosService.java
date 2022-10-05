package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.CalificacionEntity;
import com.cruzpet.entitys.ClienteEntity;
import com.cruzpet.entitys.ProductosEntity;
import com.cruzpet.models.ProductosModel;
import com.cruzpet.repositorys.CalificacionesRepository;
import com.cruzpet.repositorys.ProductosRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.NumberFormat;
import java.util.*;

@Service("productosService")
public class ProductosService {

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    @Autowired
    @Qualifier("productosrepository")
    private ProductosRepository productosRepository;

    @Autowired
    private CalificacionesRepository calificacionesRepository;

    Log log = LogFactory.getLog(getClass());


    public void crear(ProductosEntity productosEntity){
        Double calificacion = 0.0;
        NumberFormat monedaColombia = NumberFormat.getNumberInstance(new Locale("es", "CO"));
        try{
            for(int i = 0; i<productosEntity.getCalificacion().size(); i++){

                CalificacionEntity calificacionEntity = calificacionesRepository.findByIdCalificacion(productosEntity.getCalificacion().get(i).getIdCalificacion());

                calificacion += calificacionEntity.getNumeroCalificacion();

            }
            if(productosEntity.getCalificacion().size() != 0){
                productosEntity.setTotalCalificacion(calificacion/productosEntity.getCalificacion().size());
            }
            productosEntity.setPrecioproducto(monedaColombia.format(Double.parseDouble(productosEntity.getPrecioproducto())));
            productosRepository.save(productosEntity);
            log.info("Se creo correctamente");
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
    }

    public void actualizar(ProductosEntity productosEntity){
        try{
            ProductosEntity productos = productosRepository.findByIdProducto(productosEntity.getIdProducto());
            productos.setIdProducto(productosEntity.getIdProducto());
            productos.setAdministradorCreador(productosEntity.getAdministradorCreador());
            productos.setNombreProducto(productosEntity.getNombreProducto());
            productos.setRutaImagen(productosEntity.getRutaImagen());
            productos.setCalificacion(productosEntity.getCalificacion());
            productos.setPrecioproducto(productosEntity.getPrecioproducto());
            productos.setTotalCalificacion(productosEntity.getTotalCalificacion());
            productos.setEstado(productosEntity.getEstado());
            productosRepository.save(productos);

        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void actualizarEpsa(ProductosEntity productosEntity){
        try{
            ProductosEntity productos = productosRepository.findByIdProducto(productosEntity.getIdProducto());
            productos.setAdministradorCreador(productosEntity.getAdministradorCreador());
            productos.setNombreProducto(productosEntity.getNombreProducto());
            productos.setPrecioproducto(productosEntity.getPrecioproducto());
            productos.setIpsaEntity(productosEntity.getIpsaEntity());
            productos.setTotalCalificacion(productosEntity.getTotalCalificacion());
            productos.setEstado(productosEntity.getEstado());
            productosRepository.save(productos);

        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void eliminar(Integer idProducto){

        try{
            ProductosEntity productosEntity = productosRepository.findByIdProducto(idProducto);

            productosEntity.setCalificacion(new ArrayList<>());

            productosEntity.setIpsaEntity(null);

            productosRepository.save(productosEntity);

            productosRepository.delete(productosEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }

    public List<ProductosModel> mostrarProductos(){
        return convertidor.convertidorListaProductos(productosRepository.findAll());
    }

    public ProductosModel mostrarProducto(Integer idProducto){

        return convertidor.convertirObjetoProductos(productosRepository.findByIdProducto(idProducto));

    }

    public ProductosModel mostrarProductoNombre(String nombreProducto){

        return convertidor.convertirObjetoProductos(productosRepository.findByNombreProducto(nombreProducto));

    }

    public List<ProductosModel> mostrarProductosPlabra(String palabraProductos){

        int espacios = 0;

        int indice = 0;

        for(int i = 0; i<palabraProductos.length(); i++ ){

            if(palabraProductos.charAt(i) == ' '){

                indice=i-1;
                espacios++;
                break;

            }

        }

        String primeraPalabra = palabraProductos.substring(0, indice+1);

        List<ProductosEntity> productos = productosRepository.findAll();

        List<ProductosEntity> productosMostrar = new ArrayList<>();

        if(espacios > 0){

            for(ProductosEntity producto: productos){

                String palabraClave = String.valueOf(producto.getNombreProducto().charAt(0));

                for(int i =1; i<producto.getNombreProducto().length(); i++){

                    if(producto.getNombreProducto().charAt(i) != ' '){

                        palabraClave += producto.getNombreProducto().charAt(i);
                        if(palabraClave.toLowerCase().equals(palabraProductos.toLowerCase())){

                            if(!productosMostrar.contains(producto)){
                                productosMostrar.add(producto);
                                palabraClave = "";

                            }

                        }

                    }else{

                        if(palabraClave.toLowerCase().equals(primeraPalabra.toLowerCase())){


                            for(int a = indice+2; a<palabraProductos.length(); a++){

                                if (palabraProductos.charAt(a) == ' ' || palabraProductos.charAt(a) == palabraProductos.charAt(palabraProductos.length()-1)){

                                    primeraPalabra += " " + palabraProductos.substring(indice+2, a+1);
                                    palabraClave += " ";
                                    break;
                                }
                            }

                        }else{

                            palabraClave = "";

                        }

                    }

                }

                for(int j = 0; j<palabraProductos.length(); j++ ){

                    if(palabraProductos.charAt(j) == ' '){

                        indice=j-1;
                        espacios++;
                        break;

                    }

                }

                primeraPalabra = palabraProductos.substring(0, indice+1);

            }


        }else{

            for (ProductosEntity producto: productos){

                String palabraClave = "";

                for(int i = 0; i < producto.getNombreProducto().length(); i++){

                    if(producto.getNombreProducto().charAt(i) != ' '){

                        palabraClave += producto.getNombreProducto().charAt(i);
                        if(palabraClave.toLowerCase().equals(palabraProductos.toLowerCase())){

                            if(!productosMostrar.contains(producto)){
                                productosMostrar.add(producto);
                                palabraClave = "";
                            }

                        }

                    }else{

                        palabraClave = "";

                    }


                }

            }

        }


        return convertidor.convertidorListaProductos(productosMostrar);

    }

    public List<ProductosModel> traerProductosIpsa(Integer rut){

        return convertidor.convertidorListaProductos(productosRepository.findByIpsaEntity_Rut(rut));

    }

    public List<ProductosModel> traerProductosMayorMenor(){

        List<ProductosEntity>  productosEntities = productosRepository.findAll();

        productosEntities.sort(Comparator.comparing(ProductosEntity::getTotalCalificacion).reversed());

        List<ProductosEntity> productosMostrar = productosEntities;

        return convertidor.convertidorListaProductos(productosMostrar);

    }

    public Page<ProductosEntity> obtenerProductosPaginador(Pageable pageable){
        return productosRepository.findAll(pageable);
    }


}
