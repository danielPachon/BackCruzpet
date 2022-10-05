package com.cruzpet.entitys;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RolEntity {

    public RolEntity(){

    }

    public RolEntity(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    @Id
    @GeneratedValue(generator = "secuenciaroles")
    @SequenceGenerator(name = "secuenciaroles", sequenceName = "autoroles", allocationSize = 1)
    private int id;

    @NotNull
    @Column(name = "nombrerol")
    private String rolNombre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }
}
