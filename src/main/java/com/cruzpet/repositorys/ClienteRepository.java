package com.cruzpet.repositorys;

import com.cruzpet.entitys.ClienteEntity;
import com.cruzpet.services.ClienteService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("clienteRepository")
public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {

    ClienteEntity findByCedulaCliente(String cedulaUsuario);

    ClienteEntity findByEmail(String email);

    ClienteEntity findByUsername(String username);

    List<ClienteEntity> findByNombres(String nombres);

    List<ClienteEntity> findByApellidos(String apellidos);

    List<ClienteEntity> findByEstado(String estado);

    boolean existsByEmail(String email);

    boolean existsByCedulaCliente(String cedula);

}
