package com.cruzpet.repositorys;

import com.cruzpet.entitys.FormulaEntity;
import com.cruzpet.entitys.MascotaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("formulasRepository")
public interface FormulasRepository extends JpaRepository<FormulaEntity, Integer> {

    FormulaEntity findByIdFormula(Integer idFormulas);

    List<FormulaEntity> findByMascotaEntity_NumeroIdentidad(String numeroIdentidad);

}
