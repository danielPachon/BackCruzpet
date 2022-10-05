package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.AdministradorEntity;
import com.cruzpet.entitys.CarnetVacunacionEntity;
import com.cruzpet.entitys.VacunaAplicadaEntity;
import com.cruzpet.models.AdministradorModel;
import com.cruzpet.models.CarnetVacunacionModel;
import com.cruzpet.repositorys.CarnetVacunacionRepository;
import com.cruzpet.repositorys.VacunaAplicadaRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("carnetVacunacionService")
public class CarnetVacunacionService{

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("carnetVacunacionRepository")
    private CarnetVacunacionRepository carnetVacunacionRepository;

    @Autowired
    private VacunaAplicadaRepository vacunaAplicadaRepository;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    public void insertar(CarnetVacunacionEntity carnetVacunacionEntity){

        try{

            carnetVacunacionRepository.save(carnetVacunacionEntity);

        }catch (Exception ex){

            log.error(ex.getMessage());
        }

    }

    public void actualizar(CarnetVacunacionEntity carnetVacunacionEntity) {


        CarnetVacunacionEntity carnetVacunacion = carnetVacunacionRepository.findByIdCarnetVacunacion(carnetVacunacionEntity.getIdCarnetVacunacion());

        List<VacunaAplicadaEntity> vacunasAplicadas = carnetVacunacion.getVacunaAplicadaEntity();

        carnetVacunacion.setPeso(carnetVacunacionEntity.getPeso().doubleValue());

        for (int i = 0; i<carnetVacunacionEntity.getVacunaAplicadaEntity().size(); i++){

            for(int j = 0; j<carnetVacunacion.getVacunaAplicadaEntity().size(); j++){

                VacunaAplicadaEntity vacunaAplicadaEntity = vacunaAplicadaRepository.findByIdVacunaAplicada(carnetVacunacionEntity.getVacunaAplicadaEntity().get(i).getIdVacunaAplicada());

                if(carnetVacunacion.getVacunaAplicadaEntity().get(j).getVacunasEntity().getIdVacuna() == vacunaAplicadaEntity.getVacunasEntity().getIdVacuna()){

                    VacunaAplicadaEntity vacunaAplicada = vacunaAplicadaRepository.findByIdVacunaAplicada(carnetVacunacion.getVacunaAplicadaEntity().get(j).getIdVacunaAplicada());

                    vacunaAplicada.setEstado("i");

                    vacunaAplicadaRepository.save(vacunaAplicada);

                }

            }

            vacunasAplicadas.add(carnetVacunacionEntity.getVacunaAplicadaEntity().get(i));

        }

        carnetVacunacion.setVacunaAplicadaEntity(vacunasAplicadas);


        try{

            carnetVacunacionRepository.save(carnetVacunacion);

        }catch (Exception ex){

            log.error(ex.getMessage());

        }

    }

    public void eliminar(Integer idCarnetVacunacion){

        try{

            CarnetVacunacionEntity carnetVacunacionEntity = carnetVacunacionRepository.findByIdCarnetVacunacion(idCarnetVacunacion);

            carnetVacunacionRepository.delete(carnetVacunacionEntity);


        }catch (Exception ex){

            log.error(ex.getMessage());


        }

    }

    public List<CarnetVacunacionModel> obtenerCarnetVacunacion(){

        return convertidor.convertidorListaCarnetVacunacion(carnetVacunacionRepository.findAll());

    }

    public CarnetVacunacionEntity obternerCarnetVacunacionMascota(CarnetVacunacionEntity carnetVacunacionEntity){

        return carnetVacunacionRepository.findByMascota_NumeroIdentidad(carnetVacunacionEntity.getMascota().getNumeroIdentidad());

    }


}
