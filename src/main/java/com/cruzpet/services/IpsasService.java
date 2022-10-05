package com.cruzpet.services;

import com.cruzpet.controllers.VeterinariosRestController;
import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.*;
import com.cruzpet.models.AdministradorModel;
import com.cruzpet.models.ClienteModel;
import com.cruzpet.models.IpsasModel;
import com.cruzpet.repositorys.DireccionRepository;
import com.cruzpet.repositorys.IpsasRepository;
import com.cruzpet.security.dto.JwtDto;
import com.cruzpet.security.jwt.JwtProvider;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("ipasasService")
public class IpsasService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("ipsasRepository")
    private IpsasRepository ipsasRepository;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RolService rolService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;


    public void crear(IpsaEntity ipsa) {
        try{
            ipsa.setCorreoIpsa(ipsa.getNombre().toLowerCase() + "@cruzpet.com");
            ipsa.setLogoIpsa(ipsa.getLogoIpsa());
            ipsa.setPasswordIpsa(passwordEncoder.encode(ipsa.getPasswordIpsa()));
            Set<RolEntity> roles = new HashSet<>();
            roles.add(rolService.getByRolNombre("Ipsa"));
            ipsa.setRoles(roles);
            ipsasRepository.save(ipsa);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void actualizar(IpsaEntity objetoIpsa) {


        try {
            IpsaEntity ipsa = ipsasRepository.findByRut(objetoIpsa.getRut());
                ipsa.setNombre(objetoIpsa.getNombre());
                ipsa.setDireccionIpsa(objetoIpsa.getDireccionIpsa());
                ipsa.setEstado(objetoIpsa.getEstado());
                ipsa.setCorreoIpsa(objetoIpsa.getNombre() + "@cruzpet.com");
                ipsa.setPasswordIpsa(passwordEncoder.encode(objetoIpsa.getPasswordIpsa()));
                ipsa.setLogoIpsa(objetoIpsa.getLogoIpsa());
                ipsa.setAdministradorCreador(objetoIpsa.getAdministradorCreador());
                ipsasRepository.save(ipsa);
        }catch (IllegalArgumentException i){
            log.error(i.getMessage());
        }
    }

    public void actualizarSinContra(IpsaEntity objetoIpsa) {

        try {
            IpsaEntity ipsa = ipsasRepository.findByRut(objetoIpsa.getRut());
            ipsa.setNombre(objetoIpsa.getNombre());
            ipsa.setDireccionIpsa(objetoIpsa.getDireccionIpsa());
            ipsa.setEstado(objetoIpsa.getEstado());
            ipsa.setCorreoIpsa(objetoIpsa.getNombre() + "@cruzpet.com");
            ipsa.setAdministradorCreador(objetoIpsa.getAdministradorCreador());
            ipsasRepository.save(ipsa);
        }catch (IllegalArgumentException i){
            log.error(i.getMessage());
        }
    }
    public void borrar(int id) {

        try{

            IpsaEntity ipsa = ipsasRepository.findByRut(id);

            ipsasRepository.delete(ipsa);

        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public List<IpsasModel> obtenerIpsas(){

        return convertidor.convertidorListaIpsas(ipsasRepository.findAll());

    }

    public List<IpsasModel> obtenerIpsasDepartamento(IpsaEntity ipsa){

        return convertidor.convertidorListaIpsas(ipsasRepository.findByDireccionIpsa_Barrios_CiudadOrigen_DepartamentoOrigen_NombreDepartamento(ipsa.getDireccionIpsa().getBarrios().getCiudadOrigen().getDepartamentoOrigen().getNombreDepartamento()));

    }

    public JwtDto loginIpsa(IpsaEntity ipsaEntity){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(ipsaEntity.getCorreoIpsa(), ipsaEntity.getPasswordIpsa()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.crearTokenIpsa(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtDto jwtDto = new JwtDto(jwt,  userDetails.getAuthorities());

        return jwtDto;

    }

    public Boolean existenciaIpsa(String email){

        return ipsasRepository.existsByCorreoIpsa(email);

    }

    public IpsasModel obtenerIpsaRut(Integer rut){

        return convertidor.convertirObjetoIpsa(ipsasRepository.findByRut(rut));

    }

    public IpsaEntity obtenerIpsa(String email){

        return ipsasRepository.findByCorreoIpsa(email);

    }

    public IpsasModel buescarIpsaEmail(String email){

        IpsaEntity ipsa = ipsasRepository.findByCorreoIpsa(email);

        return convertidor.convertirObjetoIpsa(ipsa);

    }

    public Boolean actualizarContrasenaIpsa(String email, String contrasena){

        IpsaEntity ipsa = ipsasRepository.findByCorreoIpsa(email);


        if(!passwordEncoder.matches(contrasena, ipsa.getPasswordIpsa())){

            ipsa.setPasswordIpsa(passwordEncoder.encode(contrasena));

            ipsasRepository.save(ipsa);

            return true;

        }else{

            return false;

        }
    }

    public IpsaEntity buscarIpsaNombre(IpsaEntity ipsaEntity){

        IpsaEntity ipsa = ipsasRepository.findByNombre(ipsaEntity.getNombre());

        return ipsa;
    }

    public Page<IpsaEntity> obtenerIpsasPaginador(Pageable pageable){
        return ipsasRepository.findAll(pageable);
    }

}
