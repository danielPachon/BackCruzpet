package com.cruzpet.repositorys;

import com.cruzpet.entitys.CalificacionEntity;
import com.cruzpet.entitys.ProductosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productosrepository")
public interface ProductosRepository extends JpaRepository<ProductosEntity, Integer> {

    ProductosEntity findByIdProducto(Integer idProducto);

    ProductosEntity findByNombreProducto(String nombreProducto);

    List<ProductosEntity> findByIpsaEntity_Rut(Integer rut);

}
