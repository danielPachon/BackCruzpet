package com.cruzpet.repositorys;

import com.cruzpet.entitys.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("rolRepository")
public interface RolRepository extends JpaRepository<RolEntity, Integer> {

    RolEntity findByRolNombre(String nombreRol);

}
