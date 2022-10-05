package com.cruzpet.repositorys;

import com.cruzpet.entitys.BarrioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("barrioRepository")
public interface BarrioRepository extends JpaRepository<BarrioEntity, Integer> {

    BarrioEntity findByIdBarrio(Integer idBarrio);

    BarrioEntity findByCiudadOrigen_IdCiudadAndNombreBarrio(Integer idCiudad, String nombreBarrio);
}
