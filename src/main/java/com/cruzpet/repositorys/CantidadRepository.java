package com.cruzpet.repositorys;

import com.cruzpet.entitys.CantidadEntity;
import com.cruzpet.entitys.ProductosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cantidadrepository")
public interface CantidadRepository extends JpaRepository<CantidadEntity, Integer> {

    CantidadEntity findByIdCantidad(Integer idCantidad);

    List<CantidadEntity> findByCarritoCompras_IdCompra(Integer idCarritoCompras);

    CantidadEntity findByProductos_IdProducto_AndCarritoCompras_IdCompra(Integer idProducto, Integer idCarritCompras);

}
