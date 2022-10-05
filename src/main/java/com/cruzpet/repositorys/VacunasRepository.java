package com.cruzpet.repositorys;


import com.cruzpet.entitys.VacunaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("vacunasRepository")
public interface VacunasRepository extends JpaRepository<VacunaEntity, Integer> {

    VacunaEntity findByIdVacuna(Integer id);


}
