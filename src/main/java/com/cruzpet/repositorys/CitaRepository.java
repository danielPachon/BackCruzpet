package com.cruzpet.repositorys;

import com.cruzpet.entitys.CitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository("citaRepository")
public interface CitaRepository extends JpaRepository<CitaEntity, Integer> {

    CitaEntity findByIdCita(Integer idCita);

    List<CitaEntity> findByFechaAndIpsaPropetaria_Rut(LocalDate fecha, Integer rut);

    List<CitaEntity> findByIpsaPropetaria_Rut(Integer rut);

    List<CitaEntity> findByClienteCita_CedulaCliente(String cedula);

    List<CitaEntity> findByCitaVeterinario_CedVeterinario(String cedula);

}
