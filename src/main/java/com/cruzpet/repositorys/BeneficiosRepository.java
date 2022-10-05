package com.cruzpet.repositorys;

import com.cruzpet.entitys.BeneficiosEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("beneficiosrepository")
public interface BeneficiosRepository extends JpaRepository<BeneficiosEntity, Integer> {

    BeneficiosEntity findByIdBeneficio(Integer idBeneficio);

    boolean existsByNombreBeneficio(String nombreBeneficio);

    Page<BeneficiosEntity> findAllByEstado(String estado, Pageable pageable);

}
