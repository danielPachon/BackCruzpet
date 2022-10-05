package com.cruzpet.entitys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "veterinariodisponibilidades")
public class VeterinarioDisponibilidadesEntity  {

    public VeterinarioDisponibilidadesEntity() {
    }

    public VeterinarioDisponibilidadesEntity(DisponibilidadEntity disponibilidadEntity, VeterinarioEntity veterinario) {
        this.disponibilidadEntity = disponibilidadEntity;
        this.veterinarioEntity = veterinario;
    }

    @Id
    @GeneratedValue(generator = "secuenciaveterinariodisponibilidades")
    @SequenceGenerator(name = "secuenciaveterinariodisponibilidades", sequenceName = "autoveterinariodisponibilidades", allocationSize = 1)
    @Column(name = "idVeterinarioDisponibilidades")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "iddisponibilidadfk")
    @JsonBackReference(value="user-person")
    private DisponibilidadEntity disponibilidadEntity;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "cedulafk")
    @JsonBackReference
    private VeterinarioEntity veterinarioEntity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DisponibilidadEntity getDisponibilidadEntity() {
        return disponibilidadEntity;
    }

    public void setDisponibilidadEntity(DisponibilidadEntity disponibilidadEntity) {
        this.disponibilidadEntity = disponibilidadEntity;
    }

    public VeterinarioEntity getVeterinarioEntity() {
        return veterinarioEntity;
    }

    public void setVeterinarioEntity(VeterinarioEntity veterinarioEntity) {
        this.veterinarioEntity = veterinarioEntity;
    }
}
