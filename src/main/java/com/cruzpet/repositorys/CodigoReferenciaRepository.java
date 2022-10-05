package com.cruzpet.repositorys;

import com.cruzpet.entitys.CodigoReferenciaEntity;
import com.cruzpet.models.CodigoReferenciaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("codigoReferenciaRepository")
public interface CodigoReferenciaRepository extends JpaRepository<CodigoReferenciaEntity, String> {

    List<CodigoReferenciaEntity> findByCodigoReferenciaCliente_CedulaCliente(String cedulaCliente);

    CodigoReferenciaEntity findByCodigoReferencia(String codigoReferencia);

}
