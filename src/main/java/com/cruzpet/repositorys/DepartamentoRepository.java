package com.cruzpet.repositorys;

import com.cruzpet.entitys.DepartamentoEntity;
import com.cruzpet.entitys.RazaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    LOS REPOSITORIOS SON INTERFACES QUE NOS PERMITIRA TENER ACCESO A LA MANIPULACION DE LA
    BASE DE DATOS, ES DECIR, QUE POR MEDIO DE ESTA PODREMOS REALIZAR SENTENCIAS SQL POR MEDIO
    DEL ORM HIBERNATE
 */

@Repository("departamentoRepository") //INDICAMOS QUE ESTA CLASE SERA UN REPOSITORIO
//EL JpaRepository ES UNA INTERFAZ QUE NOS SERVIRA PARA IMPLEMENTAR LAS SENTENCIAS BASICAS DE SQL A NUESTRO PROGRAMA
public interface DepartamentoRepository extends JpaRepository<DepartamentoEntity, Integer> {

    //AQUI ESTARAN LAS SENTENCIAS PERSONALIZADAS QUE NUESTRO PROGRAMA IRA REQUIERIENDO, ESTAS
    //SENTENCIAS DEBEN UTILIZAR LA NOMENCLATURA DEL JPA, POR ELLO ESTA EN INGLES.

    DepartamentoEntity findByIdDepartamento(Integer idDepartamento);

    DepartamentoEntity findByNombreDepartamento(String nombreDepartamento);


}
