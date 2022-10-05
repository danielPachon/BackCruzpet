package com.cruzpet.repositorys;

import com.cruzpet.entitys.VacunaDetalleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("vacunaDetalleRepository")
public interface VacunaDetalleRepository extends JpaRepository<VacunaDetalleEntity, Integer> {

    VacunaDetalleEntity findByIdVacunasDetalles(Integer idVacunasDetalles);

}
