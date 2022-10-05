package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

//ESTA ES UNA CLASE DE TIPO ENTIDAD, UNA ENTIDAD HACE REFERENCIA A UNA TABLA DE LA BASE DE DATOS, ESTAS
//PERMITIRAN TENER ACCESO A DICHA BASE DE DATOS Y MANIPULAR SU CONTENIDO POR MEDIO DE LOS REPOSITORYS,
//POR BUENAS PRACTICAS, ESTOS NO DEBEN MEZCLARSE CON LAS VIEWS.

@Entity //INDICA A LA CLASE QUE ESTA SERA UNA CLASE DE ENTIDAD
@Table(name = "departamentos") //PERMITE INDICAR A QUE TABLA ESTAMOS MAPEANDO
public class DepartamentoEntity {

    /*
    * HAY DOS CONSTRUCTORES, UNO PARA EL CORRECTO FUNCIONAMIENTO DE HIBERNATE Y EL SEGUNDO PARA
    * PERMITIR A LOS REPOSITORYS MANIPULAR LOS DATOS DE LAS ENTITYS
    * */

    public DepartamentoEntity() {
    }

    public DepartamentoEntity(Integer idDepartamento, String nombreDepartamento, AdministradorEntity administradorCreador) {
        this.idDepartamento = idDepartamento;
        this.nombreDepartamento = nombreDepartamento;
        this.administradorCreador = administradorCreador;
    }


    /*
        LA ETIQUETA @Id NOS SIRVE PARA INDICAR QUE EL DICHA VARIABLE HACE REFERENCIA AL CAMPO
        PRINCIPAL DE LA TABLA
     */

    @Id
    @Column(name = "iddepartamento") //NO PERMITE INDICAR A QUE COLUMNA DE LA BASE DE DATOS ESTAMOS HACIENDO REFERENCIA
    private Integer idDepartamento;

    @Column(name = "nombredepartamento", nullable = false, length = 50)
    private String nombreDepartamento;

    @JsonIgnore
    @OneToMany(mappedBy = "departamentoOrigen", fetch = FetchType.LAZY)
    private List<CiudadEntity> ciudad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idadministradorfk")
    private AdministradorEntity administradorCreador;


    //ESTOS GETTERS Y SETTERS NOS PERMITEN ACCESO A LOS DATOS DE LAS ENTITYS

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

    public AdministradorEntity getAdministradorCreador() {
        return administradorCreador;
    }

    public void setAdministradorCreador(AdministradorEntity administradorCreador) {
        this.administradorCreador = administradorCreador;
    }
}
