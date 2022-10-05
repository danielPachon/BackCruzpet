package com.cruzpet.entitys;

import javax.persistence.*;

@Entity
@Table(name = "codigoreferencia")
public class CodigoReferenciaEntity {

    public CodigoReferenciaEntity() {
    }

    public CodigoReferenciaEntity(String codigoReferencia, ClienteEntity codigoReferenciaCliente) {
        this.codigoReferencia = codigoReferencia;
        this.codigoReferenciaCliente = codigoReferenciaCliente;
    }

    @Id
    private String codigoReferencia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idclientefk")
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
