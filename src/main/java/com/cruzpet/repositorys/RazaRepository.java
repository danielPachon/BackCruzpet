package com.cruzpet.repositorys;

import com.cruzpet.entitys.RazaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("razaRepository")
public interface RazaRepository extends JpaRepository<RazaEntity, Integer> {

    public abstract RazaEntity findByIdRaza(Integer idRaza);

     RazaEntity findByNombreRaza(String nombreRaza);

     List<RazaEntity> findByTipoMascotaEntity_IdTipoMascota(Integer idMascota);
}
