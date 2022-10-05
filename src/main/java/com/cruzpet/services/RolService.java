package com.cruzpet.services;

import com.cruzpet.entitys.RolEntity;
import com.cruzpet.repositorys.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service("rolService")
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public RolEntity getByRolNombre(String rolNombre){

        return rolRepository.findByRolNombre(rolNombre);

    }

    public void save(RolEntity rolEntity){

        rolRepository.save(rolEntity);

    }

}
