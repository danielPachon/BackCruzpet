package com.cruzpet.repositorys;

import com.cruzpet.entitys.HistorialClinicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("historiaClinicaRepository")
public interface HistoriaClinicaRepository extends JpaRepository<HistorialClinicaEntity, Integer> {

    HistorialClinicaEntity findByIdHistoriaClinica(Integer idHistoriaClin);

    List<HistorialClinicaEntity> findByIpsaHistoriaClin_Rut(Integer rut);

    List<HistorialClinicaEntity> findByHistoriaClinicaCliente_CedulaCliente(String cedula);

    List<HistorialClinicaEntity> findByHistoriaClinVeterinario_CedVeterinario(String cedula);

    List<HistorialClinicaEntity> findByHistoriaClinMascota_NumeroIdentidad(String identificador);

}
