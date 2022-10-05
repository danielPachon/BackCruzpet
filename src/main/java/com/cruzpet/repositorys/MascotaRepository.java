package com.cruzpet.repositorys;

import com.cruzpet.entitys.MascotaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("mascotaRepository")
public interface MascotaRepository extends JpaRepository<MascotaEntity, String> {

    MascotaEntity findByNumeroIdentidad(String numeroIdentidad);

    List<MascotaEntity> findByClienteMascota_CedulaCliente(String cedulaUsuario);

    Boolean existsByNumeroIdentidad(String numeroIdentidad);


    Page<MascotaEntity> findAllByClienteMascota_CedulaCliente(String cedualaCliente, Pageable pageable);

}
