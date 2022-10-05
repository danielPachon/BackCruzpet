package com.cruzpet.repositorys;

import com.cruzpet.entitys.IpsaEntity;
import com.cruzpet.entitys.VeterinarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ipsasRepository")
public interface IpsasRepository extends JpaRepository<IpsaEntity, Integer> {

    IpsaEntity findByRut(Integer rut);

    IpsaEntity findByCorreoIpsa(String correo);

    boolean existsByCorreoIpsa(String email);

    List<IpsaEntity> findByDireccionIpsa_Barrios_CiudadOrigen_DepartamentoOrigen_NombreDepartamento(String nombreDepartamento);

    IpsaEntity findByNombre(String nombreIpsa);

}
