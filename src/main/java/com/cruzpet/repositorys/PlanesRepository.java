package com.cruzpet.repositorys;

import com.cruzpet.entitys.BeneficiosEntity;
import com.cruzpet.entitys.PlanesEntity;
import com.cruzpet.models.PlanesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository("planesrepository")
public interface PlanesRepository extends JpaRepository<PlanesEntity, Integer> {

    PlanesEntity findByIdPlan(Integer idPlan);

    PlanesEntity findByIdPlan(PlanesEntity planesEntity);

    PlanesEntity findByTituloPlan(String nombre);
}
