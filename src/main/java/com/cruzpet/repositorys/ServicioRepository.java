package com.cruzpet.repositorys;

import com.cruzpet.entitys.ServiciosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("servicioRepository")
public interface ServicioRepository extends JpaRepository<ServiciosEntity, Integer> {

    ServiciosEntity findByIdServicio(Integer idServicio);

}
