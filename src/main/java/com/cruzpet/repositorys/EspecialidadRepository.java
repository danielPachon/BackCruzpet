package com.cruzpet.repositorys;

import com.cruzpet.entitys.EspecialidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("especialidadRepository")
public interface EspecialidadRepository extends JpaRepository<EspecialidadEntity, Integer> {

    EspecialidadEntity findByIdEspecialidad(Integer idEspecialidad);

    EspecialidadEntity findByNombreEspecialidad(String nombreEspecialidad);

}
