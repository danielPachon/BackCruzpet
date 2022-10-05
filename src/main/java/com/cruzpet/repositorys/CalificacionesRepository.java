package com.cruzpet.repositorys;

import com.cruzpet.entitys.CalificacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("calificacionesRepository")
public interface CalificacionesRepository extends JpaRepository<CalificacionEntity, Integer> {

    CalificacionEntity findByIdCalificacion(Integer id);

}
