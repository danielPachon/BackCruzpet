package com.cruzpet.repositorys;

import com.cruzpet.entitys.MedicamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("medicamentoRepository")
public interface MedicamentoRepository extends JpaRepository<MedicamentoEntity, Integer> {

    MedicamentoEntity findByIdMedicamento(Integer idMedicamento);


}
