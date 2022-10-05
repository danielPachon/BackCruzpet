package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.AdministradorEntity;
import com.cruzpet.entitys.ClienteEntity;
import com.cruzpet.entitys.RolEntity;
import com.cruzpet.models.AdministradorModel;
import com.cruzpet.models.ClienteModel;
import com.cruzpet.models.VeterinarioModel;
import com.cruzpet.repositorys.AdministradorRepository;
import com.cruzpet.security.dto.JwtDto;
import com.cruzpet.security.jwt.JwtProvider;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("administradorService")
public class AdministradorService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("administradorRepository")
    private AdministradorRepository administradorRepository;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    @Autowired
    private RolService rolService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    public void insertar(AdministradorEntity administradorEntity){

        try{
            administradorEntity.setPasswordAdministrador(passwordEncoder.encode(administradorEntity.getPasswordAdministrador()));
            Set<RolEntity> roles = new HashSet<>();
            roles.add(rolService.getByRolNombre("Admin"));
            administradorEntity.setRoles(roles);
            administradorRepository.save(administradorEntity);

        }catch (Exception ex){

            log.error(ex.getMessage());
        }

    }

    public void actualizar(AdministradorEntity administradorEntity){
        /*

        administrador.setNombreAdministrador(administradorEntity.getNombreAdministrador());
        administrador.setCorreAdministrador(administradorEntity.getCorreAdministrador());
        administrador.setEstado(administradorEntity.getEstado());
        */

        AdministradorEntity administrado = administradorRepository.findByIdAdministrador(administradorEntity.getIdAdministrador());
        administrado.setNombreAdministrador(administradorEntity.getNombreAdministrador());
        administrado.setCorreAdministrador(administradorEntity.getCorreAdministrador());
        administrado.setEstado(administradorEntity.getEstado());

        try{

            administradorRepository.save(administrado);

        }catch (Exception ex){

            log.error(ex.getMessage());
        }

    }

    public void eliminar(Integer idAdministrador){

        try{

            AdministradorEntity administrador = administradorRepository.findByIdAdministrador(idAdministrador);

            administradorRepository.delete(administrador);

        }catch (Exception ex){

            log.error(ex.getMessage());

        }

    }

    public List<AdministradorModel> obtenerAdministradores(){

        return convertidor.convertidorListaAdministrador(administradorRepository.findAll());

    }

    public Boolean existenciaAdministrador(String correoAdministrador){

        return administradorRepository.existsByCorreAdministrador(correoAdministrador);

    }

    public AdministradorEntity obtenerAdministrador(String email){

        return administradorRepository.findByCorreAdministrador(email);

    }

    public JwtDto login(AdministradorEntity administrador){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(administrador.getCorreAdministrador(), administrador.getPasswordAdministrador()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.crearTokenAdministrador(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtDto jwtDto = new JwtDto(jwt, userDetails.getAuthorities());
        return jwtDto;

    }

    public AdministradorModel buescarAdministradorEmail(String email){

        AdministradorEntity administrador = administradorRepository.findByCorreAdministrador(email);

        return convertidor.convertirObjetoAdministrador(administrador);

    }

    public Boolean actualizarContrasenaAdministrador(String email, String contrasena){

        AdministradorEntity administrador = administradorRepository.findByCorreAdministrador(email);


        if(!passwordEncoder.matches(contrasena, administrador.getPasswordAdministrador())){

            administrador.setPasswordAdministrador(passwordEncoder.encode(contrasena));

            administradorRepository.save(administrador);

            return true;

        }else{

            return false;

        }
    }

    public AdministradorEntity traerAdministradorId(AdministradorEntity administradorEntity){

        return administradorRepository.findByIdAdministrador(administradorEntity.getIdAdministrador());

    }


}
