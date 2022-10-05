package com.cruzpet.repositorys;

import com.cruzpet.entitys.TipoSangreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("tipoSangreRepositorio")
public interface TipoSangreRepository extends JpaRepository<TipoSangreEntity, Integer> {

    TipoSangreEntity findByIdTipoSangre(Integer idTipoSangre);

    List<TipoSangreEntity> findByTipoMascotaEntity_IdTipoMascota(Integer idTipoMascota);

    TipoSangreEntity findByTipoSangre(String tipoSangre);
}
