package com.cruzpet.repositorys;

import com.cruzpet.entitys.ComprasEntity;
import com.cruzpet.entitys.ProductosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("comprasRepository")
public interface ComprasRepository extends JpaRepository<ComprasEntity, Integer> {

    ComprasEntity findByIdCompra(Integer idCompra);

}
