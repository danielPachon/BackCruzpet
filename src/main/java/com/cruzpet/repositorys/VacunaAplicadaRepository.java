package com.cruzpet.repositorys;

import com.cruzpet.entitys.VacunaAplicadaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("vacunaaplicadarepository")
public interface VacunaAplicadaRepository extends JpaRepository<VacunaAplicadaEntity, Integer> {

    VacunaAplicadaEntity findByIdVacunaAplicada(Integer vacunaAplicada);

}
