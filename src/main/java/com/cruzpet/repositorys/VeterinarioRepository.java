package com.cruzpet.repositorys;

import com.cruzpet.entitys.VeterinarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("veterinarioRepository")
public interface VeterinarioRepository extends JpaRepository<VeterinarioEntity, String> {

    VeterinarioEntity findByCedVeterinario(String cedulaVeterinario);

    VeterinarioEntity findByCedVeterinarioAndEstado(String cedulaVeterinario, String estado);

    List<VeterinarioEntity> findByIpsaTrabajo_Rut(Integer rut);

    VeterinarioEntity findByCorreoVeterinario(String correo);

    boolean existsByCorreoVeterinario(String email);

    boolean existsByCedVeterinario(String cedulaVeterinario);

}
