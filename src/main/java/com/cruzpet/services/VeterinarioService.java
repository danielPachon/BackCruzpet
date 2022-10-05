package com.cruzpet.services;

import com.cruzpet.controllers.VeterinariosRestController;
import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.*;
import com.cruzpet.models.AdministradorModel;
import com.cruzpet.models.IpsasModel;
import com.cruzpet.models.VeterinarioModel;
import com.cruzpet.repositorys.DireccionRepository;
import com.cruzpet.repositorys.EspecialidadRepository;
import com.cruzpet.repositorys.IpsasRepository;
import com.cruzpet.repositorys.VeterinarioRepository;
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

import javax.persistence.Id;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("veterinarioService")
public class VeterinarioService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RolService rolService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private IpsasRepository ipsasRepository;
    @Autowired
    @Qualifier("veterinarioRepository")
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    public void crear(VeterinarioEntity veterinarioEntity) throws Exception {

        IpsaEntity ipsa = ipsasRepository.findByRut(veterinarioEntity.getIpsaTrabajo().getRut());

        try{
            veterinarioEntity.setPasswordVeterinario(passwordEncoder.encode(veterinarioEntity.getPasswordVeterinario()));
            Set<RolEntity> roles = new HashSet<>();
            roles.add(rolService.getByRolNombre("Veterinario"));
            veterinarioEntity.setRoles(roles);
            veterinarioEntity.setCedVeterinario(veterinarioEntity.getCedVeterinario().replace(" ", ""));
            veterinarioEntity.setCorreoVeterinario(veterinarioEntity.getNombres() + ipsa.getNombre() + "@cruzpet.com");
            veterinarioRepository.save(veterinarioEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void actualizar(VeterinarioEntity veterinarioEntity) throws IOException {


        try {
            VeterinarioEntity veterinario = veterinarioRepository.findByCedVeterinario(veterinarioEntity.getCedVeterinario());
                veterinario.setNombres(veterinarioEntity.getNombres());
                veterinario.setApellidos(veterinarioEntity.getApellidos());
                veterinario.setFechaNacimiento(veterinarioEntity.getFechaNacimiento());
                veterinario.setDireccion(veterinarioEntity.getDireccion());
                veterinario.setCelular(veterinario.getCelular());
                veterinario.setFoto(veterinario.getFoto());
                veterinario.setCorreoVeterinario(veterinario.getCorreoVeterinario());
                veterinario.setPasswordVeterinario(veterinario.getPasswordVeterinario());
                veterinario.setIpsaTrabajo(veterinario.getIpsaTrabajo());
                veterinario.setEspecialidadVeterinario(veterinario.getEspecialidadVeterinario());
                veterinario.setAdministradorCreador(veterinarioEntity.getAdministradorCreador());
            veterinarioRepository.save(veterinario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarSinPassword(VeterinarioEntity veterinarioEntity) throws IOException {


        try {
            VeterinarioEntity veterinario = veterinarioRepository.findByCedVeterinario(veterinarioEntity.getCedVeterinario());
            veterinario.setNombres(veterinarioEntity.getNombres());
            veterinario.setApellidos(veterinarioEntity.getApellidos());
            veterinario.setFechaNacimiento(veterinarioEntity.getFechaNacimiento());
            veterinario.setDireccion(veterinarioEntity.getDireccion());
            veterinario.setCelular(veterinarioEntity.getCelular());
            veterinario.setFoto(veterinarioEntity.getFoto());
            veterinario.setEstado(veterinarioEntity.getEstado());
            veterinario.setIpsaTrabajo(veterinarioEntity.getIpsaTrabajo());
            veterinario.setEspecialidadVeterinario(veterinarioEntity.getEspecialidadVeterinario());
            veterinario.setAdministradorCreador(veterinarioEntity.getAdministradorCreador());
            veterinarioRepository.save(veterinario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void borrar(String cedulaVeterinario) throws IOException {

        try{

            VeterinarioEntity veterinario = veterinarioRepository.findByCedVeterinario(cedulaVeterinario);

            veterinarioRepository.delete(veterinario);

        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public List<VeterinarioModel> obtenerVeterinarios(){

        return convertidor.convertidorListaVeterinarios(veterinarioRepository.findAll());

    }

    public List<VeterinarioModel> obtenerVeterinariosIpsa(IpsaEntity ipsa){

        return convertidor.convertidorListaVeterinarios(veterinarioRepository.findByIpsaTrabajo_Rut(ipsa.getRut()));

    }

    public JwtDto loginVeterinario(VeterinarioEntity veterinarioEntity){


        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(veterinarioEntity.getCorreoVeterinario(), veterinarioEntity.getPasswordVeterinario()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.crearTokenVeterinario(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtDto jwtDto = new JwtDto(jwt, userDetails.getAuthorities());

        return jwtDto;
    }

    public Boolean existenciaVeterinario(String email){

        return veterinarioRepository.existsByCorreoVeterinario(email);

    }

    public VeterinarioEntity obtenerVeterinarioEmail(String email){

        return veterinarioRepository.findByCorreoVeterinario(email);

    }

    public boolean existenciaCedula(String cedula){

        return veterinarioRepository.existsByCorreoVeterinario(cedula);

    }

    public Boolean actualizarContrasenaVeterinario(String email, String contrasena){

        VeterinarioEntity veterinario = veterinarioRepository.findByCorreoVeterinario(email);


        if(!passwordEncoder.matches(contrasena, veterinario.getPasswordVeterinario())){

            veterinario.setPasswordVeterinario(passwordEncoder.encode(contrasena));

            veterinarioRepository.save(veterinario);

            return true;

        }else{

            return false;

        }
    }

    public VeterinarioEntity buscarVeteriarioCedula(String cedulaVeterinario){

        return veterinarioRepository.findByCedVeterinario(cedulaVeterinario);

    }

    public Page<VeterinarioEntity> obtenerVeterinariosPaginador(Pageable pageable){
        return veterinarioRepository.findAll(pageable);
    }

    public  void actualizarEstadoVeterinario(VeterinarioEntity veterinario){

        VeterinarioEntity veterinarioEntity = veterinarioRepository.findByCedVeterinario(veterinario.getCedVeterinario());

        veterinarioEntity.setEstado(veterinario.getEstado());

        veterinarioRepository.save(veterinarioEntity);

    }

}
