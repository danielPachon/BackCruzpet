package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.DepartamentoEntity;
import com.cruzpet.entitys.EspecialidadEntity;
import com.cruzpet.models.EspecialidadModel;
import com.cruzpet.repositorys.EspecialidadRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("especialidadService")
public class EspecialidadService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("especialidadRepository")
    private EspecialidadRepository especialidadRepository;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    public void crear(EspecialidadEntity especialidadEntity){

        try{
            especialidadRepository.save(especialidadEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }

    public void actualizar(EspecialidadEntity especialidadEntity){
        try{
            EspecialidadEntity especialidades = especialidadRepository.findByIdEspecialidad(especialidadEntity.getIdEspecialidad());
            especialidades.setNombreEspecialidad(especialidadEntity.getNombreEspecialidad());
            especialidades.setAdministradorCreador(especialidadEntity.getAdministradorCreador());
            especialidadRepository.save(especialidades);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void eliminar(Integer idEspecialidad){

        try{
            EspecialidadEntity especialidades = especialidadRepository.findByIdEspecialidad(idEspecialidad);
            especialidadRepository.delete(especialidades);
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }

    public List<EspecialidadModel> mostrarEspecialidades(){
        return convertidor.convertirListaEspecialidad(especialidadRepository.findAll());
    }

    public EspecialidadEntity mostrarEspecialidadNombres(String nombreEspecialidad){

        return especialidadRepository.findByNombreEspecialidad(nombreEspecialidad);

    }

    public EspecialidadEntity mostrarEspecialidadId(Integer idEspecialidad){

        return especialidadRepository.findByIdEspecialidad(idEspecialidad);

    }

    public Page<EspecialidadEntity> obtenerEspecialidadesPaginador(Pageable pageable){
        return especialidadRepository.findAll(pageable);
    }



}
