package com.cruzpet.models;

import com.cruzpet.entitys.AdministradorEntity;
import com.cruzpet.entitys.DepartamentoEntity;
import com.cruzpet.entitys.RazaEntity;

import javax.persistence.Column;
import javax.persistence.Id;

//LO MDELS SON PARECIDOS A LOS ENTITYS, PERO LA INFORMACION QUE CONTENGAN ESTOS, SERA EL QUE SE MOSTRARA
//EN LAS VISTAS

public class DepartamentoModel {

    //HAY DOS CONSTRUCTORES COMO EN LAS ENTIDADES, UNO PARA EL MOMENTO DE HACER REFERENCIA AL MODELO,
    //Y OTRO PARA PASAR LOS DATOS DE UNA ENTIDAD Y CONVERTILOS A MODELOS

    public DepartamentoModel() {
    }

    public DepartamentoModel(DepartamentoEntity departamento) {
        this.idDepartamento = departamento.getIdDepartamento();
        this.nombreDepartamento = departamento.getNombreDepartamento();
        this.administrador = departamento.getAdministradorCreador();
    }



    //LAS VARIABLES DEBEN SER IGUAL A LA DE LAS ENTIDADES Y SI O SI DEBEN ESTAR ENCAPSULADAS PARA SU MANIPULACION

    private Integer idDepartamento;

    private String nombreDepartamento;

    private AdministradorEntity administrador;

    //LOS GETTERS Y SETTERS NOS SIRVEN PARA MANIPULAR LA INFORMACION QUE TENGA EL MODELO

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public AdministradorEntity getAdministrador() {
        return administrador;
    }

    public void setAdministrador(AdministradorEntity administrador) {
        this.administrador = administrador;
    }
}
