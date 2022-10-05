package com.cruzpet.repositorys;

import com.cruzpet.entitys.CiudadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ciudadRepository")
public interface CiudadRepository extends JpaRepository<CiudadEntity, Integer> {

    CiudadEntity findByIdCiudad(Integer idCiudad);

    List<CiudadEntity> findByDepartamentoOrigen_IdDepartamento(Integer idDepartamento);

    CiudadEntity findByDepartamentoOrigen_IdDepartamentoAndNombreCiudad(Integer idDepartamento, String nombreCiudad);

    List<CiudadEntity> findByDepartamentoOrigen_NombreDepartamento(String nombreDepartamento);
}
