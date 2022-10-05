package com.cruzpet.repositorys;

import com.cruzpet.entitys.DisponibilidadEntity;
import com.cruzpet.entitys.VeterinarioEntity;
import com.cruzpet.models.DisponibilidadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("disponibilidadRepository")
public interface DisponibilidadRepository extends JpaRepository<DisponibilidadEntity, Integer> {

    DisponibilidadEntity findByIdDisponibilidad(Integer idDisponibilidad);

    List<DisponibilidadEntity> findByIpsafk_Rut(Integer rut);

    List<DisponibilidadEntity> findByDiaDisponibilidad(String dia);

    List<DisponibilidadEntity> findByMesDisponibilidad(String mes);

    List<DisponibilidadEntity> findByYearDisponibilidad(String year);

    List<DisponibilidadEntity> findByDiaDisponibilidadAndAndMesDisponibilidad(String dia, String mes);

    List<DisponibilidadEntity> findByDiaDisponibilidadAndMesDisponibilidadAndYearDisponibilidad(String dia, String mes, String year);

    List<DisponibilidadEntity>findByDiaDisponibilidadAndMesDisponibilidadAndYearDisponibilidadAndIpsafk_Rut(String dia, String mes, String year, Integer rut);

}
