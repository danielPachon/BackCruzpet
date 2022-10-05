package com.cruzpet.repositorys;

import com.cruzpet.entitys.AdministradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("administradorRepository")
public interface AdministradorRepository extends JpaRepository<AdministradorEntity, Integer> {

    AdministradorEntity findByIdAdministrador(Integer idAdministrador);

    Boolean existsByCorreAdministrador(String correoAdministrador);

    AdministradorEntity findByCorreAdministrador(String correoAdministrador);
}
