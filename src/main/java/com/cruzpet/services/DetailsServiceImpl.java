package com.cruzpet.services;

import com.cruzpet.entitys.AdministradorEntity;
import com.cruzpet.entitys.ClienteEntity;
import com.cruzpet.entitys.IpsaEntity;
import com.cruzpet.entitys.VeterinarioEntity;
import com.cruzpet.models.AdministradorModel;
import com.cruzpet.models.ClienteModel;
import com.cruzpet.models.IpsasModel;
import com.cruzpet.models.VeterinarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("detailsService")
public class DetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private IpsasService ipsasService;

    @Autowired
    private VeterinarioService veterinarioService;

    @Autowired
    private AdministradorService administradorService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if(clienteService.existenciaCliente(email)){
            ClienteEntity cliente = clienteService.obtenerClienteEmail(email);

            return ClienteModel.build(cliente);
        }else if (ipsasService.existenciaIpsa(email)){

            IpsaEntity ipsa = ipsasService.obtenerIpsa(email);

            return IpsasModel.build(ipsa);

        }else if(veterinarioService.existenciaVeterinario(email)){

            VeterinarioEntity veterinario = veterinarioService.obtenerVeterinarioEmail(email);

            return VeterinarioModel.build(veterinario);

        }else if(administradorService.existenciaAdministrador(email)){

            AdministradorEntity administrador = administradorService.obtenerAdministrador(email);

            return AdministradorModel.build(administrador);

        }

        return null;

    }
}
