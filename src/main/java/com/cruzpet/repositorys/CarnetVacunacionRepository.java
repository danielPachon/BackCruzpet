package com.cruzpet.repositorys;

import com.cruzpet.entitys.CarnetVacunacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("carnetVacunacionRepository")
public interface CarnetVacunacionRepository extends JpaRepository<CarnetVacunacionEntity, Integer> {

    CarnetVacunacionEntity findByIdCarnetVacunacion(Integer idCarnetVacunacion);

    CarnetVacunacionEntity findByMascota_NumeroIdentidad(String numeroIdentidadMascota);

}
