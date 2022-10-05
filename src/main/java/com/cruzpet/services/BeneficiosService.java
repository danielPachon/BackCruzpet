package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.BeneficiosEntity;
import com.cruzpet.entitys.ProductosEntity;
import com.cruzpet.models.BeneficiosModel;
import com.cruzpet.repositorys.BeneficiosRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("beneficiosService")
public class BeneficiosService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    @Autowired
    @Qualifier("beneficiosrepository")
    private BeneficiosRepository beneficiosRepository;

    public void insertar(BeneficiosEntity beneficiosEntity){

        List<BeneficiosEntity> bene = beneficiosRepository.findAll();

        try{

            beneficiosRepository.save(beneficiosEntity);

            BeneficiosEntity beneficios = new BeneficiosEntity(beneficiosEntity.getNombreBeneficio(), "a", beneficiosEntity.getAdministradorCreador());

            beneficiosRepository.save(beneficios);

        }catch (Exception ex){

            log.error(ex.getMessage());

        }

    }

    public void actualizar(BeneficiosEntity beneficiosEntity){

        try{

            BeneficiosEntity beneficiosEntityNuevo = beneficiosRepository.findByIdBeneficio(beneficiosEntity.getIdBeneficio());

            beneficiosEntityNuevo.setIdBeneficio(beneficiosEntity.getIdBeneficio());
            beneficiosEntityNuevo.setNombreBeneficio(beneficiosEntity.getNombreBeneficio());
            if(beneficiosEntity.getEstado() == "i"){
                beneficiosEntityNuevo.setEstado("i");
            }else{
                beneficiosEntityNuevo.setEstado("a");
            }

            beneficiosRepository.save(beneficiosEntityNuevo);

            BeneficiosEntity beneficiosEntityNuevoDos = beneficiosRepository.findByIdBeneficio(beneficiosEntity.getIdBeneficio()+1);

            beneficiosEntityNuevoDos.setIdBeneficio(beneficiosEntity.getIdBeneficio());
            beneficiosEntityNuevoDos.setNombreBeneficio(beneficiosEntity.getNombreBeneficio());

            if(beneficiosEntity.getEstado() == "i"){
                beneficiosEntityNuevoDos.setEstado("a");
            }else{
                beneficiosEntityNuevoDos.setEstado("i");
            }

            beneficiosRepository.save(beneficiosEntityNuevoDos);

        }catch (Exception ex){

            log.error(ex.getMessage());

        }

    }

    public void actualizarBeneficioSinEstado(BeneficiosEntity beneficiosEntity) {

        try {
            BeneficiosEntity beneficiosEntityNuevo = beneficiosRepository.findByIdBeneficio(beneficiosEntity.getIdBeneficio());

            beneficiosEntityNuevo.setNombreBeneficio(beneficiosEntity.getNombreBeneficio());
            beneficiosEntityNuevo.setEstado("i");


            beneficiosRepository.save(beneficiosEntityNuevo);

            int id = beneficiosEntity.getIdBeneficio();

            BeneficiosEntity beneficiosEntityNuevoDos = beneficiosRepository.findByIdBeneficio(id+1);

            beneficiosEntityNuevoDos.setNombreBeneficio(beneficiosEntity.getNombreBeneficio());
            beneficiosEntityNuevoDos.setEstado("a");

            beneficiosRepository.save(beneficiosEntityNuevoDos);

        } catch (Exception ex) {

            log.info(ex.getMessage());
        }
    }

    public void eliminar(int idBeneficio){

        try{

            BeneficiosEntity beneficiosEntity = beneficiosRepository.findByIdBeneficio(idBeneficio);
            BeneficiosEntity beneficiosEntityDos = beneficiosRepository.findByIdBeneficio(idBeneficio+1);

            beneficiosRepository.delete(beneficiosEntityDos);
            beneficiosRepository.delete(beneficiosEntity);

        }catch (Exception ex){

            log.error(ex.getMessage());

        }

    }

    public List<BeneficiosModel> traerBeneficios(){

        try{

              return  convertidor.convertirListaBeneficios(beneficiosRepository.findAll());

        }catch (Exception ex) {

            log.error(ex.getMessage());

            return null;

        }

    }

    public BeneficiosEntity traerBenficioId(BeneficiosEntity beneficiosEntity) {

        return beneficiosRepository.findByIdBeneficio(beneficiosEntity.getIdBeneficio());

    }

    public boolean existenciaBeneficioNombre(BeneficiosEntity beneficiosEntity){
        return beneficiosRepository.existsByNombreBeneficio(beneficiosEntity.getNombreBeneficio());
    }

    public Page<BeneficiosEntity> obtenerBeneficiosPaginador(Pageable pageable){
        return beneficiosRepository.findAllByEstado("i",pageable);
    }




}
