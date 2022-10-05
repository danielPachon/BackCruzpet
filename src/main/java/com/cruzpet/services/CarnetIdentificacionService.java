package com.cruzpet.services;

import com.cruzpet.entitys.CarnetIdentificacionEntity;
import com.cruzpet.repositorys.CarnetIdentificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("carnetidentificacionservice")
public class CarnetIdentificacionService {

    @Autowired
    private CarnetIdentificacionRepository carnetIdentificacionRepository;

    public void crearCarnetIdentificacion(CarnetIdentificacionEntity carnetIdentificacionEntity){

        carnetIdentificacionRepository.save(carnetIdentificacionEntity);

    }

    public CarnetIdentificacionEntity traerCarnetIdentificacionMascota(CarnetIdentificacionEntity carnetIdentificacionEntity){

        return carnetIdentificacionRepository.findByMascota_NumeroIdentidad(carnetIdentificacionEntity.getMascota().getNumeroIdentidad());

    }

}
