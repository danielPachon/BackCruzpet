package com.cruzpet.repositorys;

import com.cruzpet.entitys.VeterinarioDisponibilidadesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("veterinarioDisponibilidadesRepository")
public interface VeterinarioDisponibilidadesRepository extends JpaRepository<VeterinarioDisponibilidadesEntity, Integer> {

    List<VeterinarioDisponibilidadesEntity> findByVeterinarioEntity_CedVeterinario(String cedulaVeterinario);

    List<VeterinarioDisponibilidadesEntity> findByDisponibilidadEntity_DiaDisponibilidadAndDisponibilidadEntity_MesDisponibilidadAndDisponibilidadEntity_Ipsafk_RutAndDisponibilidadEntity_YearDisponibilidad(String dia, String mes, Integer rut, String year);

    List<VeterinarioDisponibilidadesEntity> findByDisponibilidadEntity_DiaDisponibilidadAndDisponibilidadEntity_MesDisponibilidadAndDisponibilidadEntity_Ipsafk_RutAndDisponibilidadEntity_YearDisponibilidadAndVeterinarioEntity_CedVeterinario(String dia, String mes, Integer rut, String year, String cedVeterinario);
}
