package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.BeneficiosEntity;
import com.cruzpet.entitys.MedicamentoEntity;
import com.cruzpet.entitys.PlanesEntity;
import com.cruzpet.models.MedicamentosModel;
import com.cruzpet.models.PlanesModel;
import com.cruzpet.repositorys.BeneficiosRepository;
import com.cruzpet.repositorys.PlanesRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("planesservice")
public class PlanesService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("planesrepository")
    private PlanesRepository planesRepository;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    @Autowired
    @Qualifier("beneficiosrepository")
    private BeneficiosRepository beneficiosRepository;

    public void crear(PlanesEntity planesEntity){


        List<BeneficiosEntity> bene = beneficiosRepository.findAll();

        List<BeneficiosEntity> beneNuevos = new ArrayList<>();

        try{

            for(BeneficiosEntity planes: planesEntity.getBeneficiosEntity()){

                planes.setIdBeneficio(planes.getIdBeneficio()+1);
            }

            for(BeneficiosEntity ben: bene){

                for(BeneficiosEntity beneficiosPlan : planesEntity.getBeneficiosEntity()){


                    if(ben.getIdBeneficio() != beneficiosPlan.getIdBeneficio()-1){

                        if(ben.getIdBeneficio() != beneficiosPlan.getIdBeneficio()){

                            if(!ben.getEstado().equals("a")){
                                int repetidos = 0;

                                for(BeneficiosEntity beneficiosNuevos: beneNuevos){

                                    for (BeneficiosEntity benPlan: planesEntity.getBeneficiosEntity()){

                                        if((benPlan.getIdBeneficio()-1) == ben.getIdBeneficio()){
                                            repetidos++;
                                        }
                                        if(beneficiosNuevos.getIdBeneficio() == ben.getIdBeneficio()){
                                            repetidos++;
                                        }
                                    }
                                }

                                if(repetidos == 0){
                                    beneNuevos.add(ben);
                                }

                            }

                        }

                    }
                }
            }

            beneNuevos.remove(0);

            for (BeneficiosEntity beneficios : beneNuevos){

                planesEntity.getBeneficiosEntity().add(beneficios);

            }

            planesRepository.save(planesEntity);


        }catch (Exception e) {

            log.error(e.getMessage());
        }

    }

    public void actualizarSinBenficios(PlanesEntity planesEntity){
        try {

            PlanesEntity planes = planesRepository.findByIdPlan(planesEntity.getIdPlan());
            planes.setContenidoplan(planesEntity.getContenidoplan());
            planes.setPrecio(planesEntity.getPrecio());
            planes.setTituloPlan(planesEntity.getTituloPlan());
            planesRepository.save(planes);
        }catch (Exception ex){

            log.error(ex.getMessage());

        }
    }

    public void actualizar(PlanesEntity planesEntity) {

        List<BeneficiosEntity> bene = beneficiosRepository.findAll();

        List<BeneficiosEntity> beneNuevos = new ArrayList<>();

        try {

            PlanesEntity planes = planesRepository.findByIdPlan(planesEntity.getIdPlan());
            planes.setIdPlan(planesEntity.getIdPlan());
            planes.setContenidoplan(planesEntity.getContenidoplan());
            planes.setPrecio(planesEntity.getPrecio());

            planes.setBeneficiosEntity(planesEntity.getBeneficiosEntity());

            for(int i = 0; i<planes.getBeneficiosEntity().size(); i++){
                planes.getBeneficiosEntity().get(i).setIdBeneficio(planes.getBeneficiosEntity().get(i).getIdBeneficio() + 1);
            }

            for(BeneficiosEntity ben: bene){

                for(BeneficiosEntity beneficiosPlan : planes.getBeneficiosEntity()){
                    if(ben.getIdBeneficio() != beneficiosPlan.getIdBeneficio()-1){

                        if(ben.getIdBeneficio().intValue() != beneficiosPlan.getIdBeneficio().intValue()){

                            if(!ben.getEstado().equals("a")){
                                beneNuevos.add(ben);
                            }

                        }

                    }
                }
            }

            for (BeneficiosEntity beneficios : beneNuevos){

                planes.getBeneficiosEntity().add(beneficios);

            }

            planes.setTituloPlan(planes.getTituloPlan());
            planesRepository.save(planes);
        }catch (Exception ex){

            log.error(ex.getMessage());

        }

    }

    public void borrar(Integer id){
        try{
            PlanesEntity planes = planesRepository.findByIdPlan(id);
            planesRepository.delete(planes);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public List<PlanesModel> obtenerPlanes(){

        List<PlanesEntity> planes = planesRepository.findAll();

        List<PlanesEntity> planesMostrar = new ArrayList<>();

        List<BeneficiosEntity> beneficiosA = new ArrayList<>();
        List<BeneficiosEntity> beneficiosI = new ArrayList<>();
        List<BeneficiosEntity> concatenar = new ArrayList<>();

        for(PlanesEntity plan : planes){

            for(int i = 0; i<plan.getBeneficiosEntity().size(); i++){

                if(plan.getBeneficiosEntity().get(i).getEstado().toString().equals("a")){
                    beneficiosA.add(plan.getBeneficiosEntity().get(i));
                }else{
                    beneficiosI.add(plan.getBeneficiosEntity().get(i));
                }

            }

            concatenar = Stream.concat(beneficiosA.stream(), beneficiosI.stream()).collect(Collectors.toList());
            plan.setBeneficiosEntity(concatenar);
            planesMostrar.add(plan);
            beneficiosA.clear();
            beneficiosI.clear();
        }


        return convertidor.convertidorListaPlanes(planesMostrar);

    }

    public PlanesModel obtenerPlanId(Integer id){


        return convertidor.convertirObjetoPlanes(planesRepository.findByIdPlan(id));

    }

    public PlanesModel obtenerBeneficiosPlanes(PlanesEntity idPlan){

        return convertidor.convertirObjetoPlanes(planesRepository.findByIdPlan(idPlan.getIdPlan()));

    }

    public PlanesEntity obtenerPlanTitulo(PlanesEntity planesEntity){

        return  planesRepository.findByTituloPlan(planesEntity.getTituloPlan());

    }

    public List<BeneficiosEntity> obtenerBeneficiosFaltantes(PlanesEntity planesEntity){

        PlanesEntity plan = planesRepository.findByIdPlan(planesEntity.getIdPlan());

        List<BeneficiosEntity> beneficiosConservados = plan.getBeneficiosEntity();

        List<BeneficiosEntity> beneficiosFaltantes = new ArrayList<>();

        for(BeneficiosEntity beneficio: beneficiosConservados){

            if(beneficio.getEstado().toString().equals("i")){
                beneficiosFaltantes.add(beneficio);
            }

        }

        return beneficiosFaltantes;

    }

    public List<BeneficiosEntity> obtenerBeneficiosConservados(PlanesEntity planesEntity){
        PlanesEntity plan = planesRepository.findByIdPlan(planesEntity.getIdPlan());

        List<BeneficiosEntity> beneficiosConservados = plan.getBeneficiosEntity();

        List<BeneficiosEntity> beneficiosFaltantes = new ArrayList<>();

        for(BeneficiosEntity beneficio: beneficiosConservados){

            if(beneficio.getEstado().toString().equals("a")){
                beneficiosFaltantes.add(beneficio);
            }

        }

        return beneficiosFaltantes;
    }

    public void quitarBeneficios(PlanesEntity planesEntity){

        PlanesEntity plan = planesRepository.findByIdPlan(planesEntity.getIdPlan());

        for(int i = 0; i<planesEntity.getBeneficiosEntity().size(); i++){

            for(int j = 0; j<plan.getBeneficiosEntity().size(); j++){

                if(planesEntity.getBeneficiosEntity().get(i).getIdBeneficio() ==  plan.getBeneficiosEntity().get(j).getIdBeneficio()){

                    plan.getBeneficiosEntity().remove(j);

                }

            }

        }

        for(int i = 0; i <planesEntity.getBeneficiosEntity().size(); i++){

            planesEntity.getBeneficiosEntity().get(i).setIdBeneficio(planesEntity.getBeneficiosEntity().get(i).getIdBeneficio()-1);
            plan.getBeneficiosEntity().add(planesEntity.getBeneficiosEntity().get(i));

        }

        planesRepository.save(plan);

    }

    public void agregarBeneficios(PlanesEntity planesEntity){

        PlanesEntity plan = planesRepository.findByIdPlan(planesEntity.getIdPlan());

        for(int i = 0; i<planesEntity.getBeneficiosEntity().size(); i++){

            for(int j = 0; j<plan.getBeneficiosEntity().size(); j++){

                if(planesEntity.getBeneficiosEntity().get(i).getIdBeneficio() ==  plan.getBeneficiosEntity().get(j).getIdBeneficio()){
                    plan.getBeneficiosEntity().remove(j);

                }

            }

        }

        for(int i = 0; i <planesEntity.getBeneficiosEntity().size(); i++){

            planesEntity.getBeneficiosEntity().get(i).setIdBeneficio(planesEntity.getBeneficiosEntity().get(i).getIdBeneficio()+1);
            plan.getBeneficiosEntity().add(planesEntity.getBeneficiosEntity().get(i));

        }

        planesRepository.save(plan);

    }


}
