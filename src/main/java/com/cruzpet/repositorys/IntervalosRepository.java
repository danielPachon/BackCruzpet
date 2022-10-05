package com.cruzpet.repositorys;

import com.cruzpet.entitys.IntervalosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("intervalosRepository")
public interface IntervalosRepository extends JpaRepository<IntervalosEntity, Integer> {

    IntervalosEntity findByIdIntervalo(Integer idIntervalo);

    IntervalosEntity findByIdIntervaloAndDisponibilidadfk_IdDisponibilidad(Integer idIntervalo, Integer idDisponibilidad);

}
