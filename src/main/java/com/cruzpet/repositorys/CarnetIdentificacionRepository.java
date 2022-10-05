package com.cruzpet.repositorys;

import com.cruzpet.entitys.CarnetIdentificacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("carnetidentificacionrepository")
public interface CarnetIdentificacionRepository extends JpaRepository<CarnetIdentificacionEntity, Integer> {

    CarnetIdentificacionEntity findByIdCarnetIdentificacion(Integer idCarnetIdentificacion);

    CarnetIdentificacionEntity findByMascota_NumeroIdentidad(String numeroIdentidad);

}
