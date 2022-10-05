package com.cruzpet.models;

import com.cruzpet.entitys.ClienteEntity;
import com.cruzpet.entitys.CodigoReferenciaEntity;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class CodigoReferenciaModel {

    public CodigoReferenciaModel(CodigoReferenciaEntity codigoReferencia) {
        this.codigoReferencia = codigoReferencia.getCodigoReferencia();
        this.codigoReferenciaCliente = codigoReferencia.getCodigoReferenciaCliente();
    }

    private String codigoReferencia;

    private ClienteEntity codigoReferenciaCliente;

    public String getCodigoReferencia() {
        return codigoReferencia;
    }

    public void setCodigoReferencia(String codigoReferencia) {
        this.codigoReferencia = codigoReferencia;
    }

    public ClienteEntity getCodigoReferenciaCliente() {
        return codigoReferenciaCliente;
    }

    public void setCodigoReferenciaCliente(ClienteEntity codigoReferenciaCliente) {
        this.codigoReferenciaCliente = codigoReferenciaCliente;
    }
}
