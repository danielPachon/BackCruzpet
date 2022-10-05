package com.cruzpet.repositorys;

import com.cruzpet.entitys.DireccionEntity;
import com.cruzpet.services.DireccionService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("direccionRepository")
public interface DireccionRepository extends JpaRepository<DireccionEntity, Integer> {

    DireccionEntity findByIdDireccion(Integer idDireccion);

    DireccionEntity findByDireccionCasaAndBarrios_IdBarrio(String direccionCasa, Integer idBarrio);

}
