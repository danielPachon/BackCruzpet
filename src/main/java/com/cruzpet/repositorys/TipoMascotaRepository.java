package com.cruzpet.repositorys;

import com.cruzpet.entitys.TipoMascotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("tipoMascotaRepository")
public interface TipoMascotaRepository extends JpaRepository<TipoMascotaEntity, Integer> {

   TipoMascotaEntity findByIdTipoMascota(Integer idTipoMascota);

   TipoMascotaEntity findByTipoMascota(String tipoMascota);
}
