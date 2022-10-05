package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.*;
import com.cruzpet.models.AdministradorModel;
import com.cruzpet.models.ClienteModel;
import com.cruzpet.repositorys.ClienteRepository;
import com.cruzpet.security.dto.JwtDto;
import com.cruzpet.security.jwt.JwtProvider;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("clienteService")
public class ClienteService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("clienteRepository")
    private ClienteRepository clienteRepository;

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


    @Autowired
    @Qualifier("comprasservice")
    private ComprasService comprasService;

    public void crear(ClienteEntity clienteEntity) {

        try{
            clienteEntity.setPassword(passwordEncoder.encode(clienteEntity.getPassword()));
            Set<RolEntity> roles = new HashSet<>();
            roles.add(rolService.getByRolNombre("Cliente"));
            clienteEntity.setRoles(roles);

            List<ProductosEntity> productos = new ArrayList<>();

            clienteEntity.setCompras(comprasService.crear(new ComprasEntity(productos)));

            clienteRepository.save(clienteEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void actualizar(ClienteEntity clienteEntity)  {

        try {

            ClienteEntity usuario = clienteRepository.findByCedulaCliente(clienteEntity.getCedulaCliente());

            usuario.setEmail(clienteEntity.getEmail());
            usuario.setUsername(clienteEntity.getUsername());
            usuario.setTelefono(clienteEntity.getTelefono());
            usuario.setCedulaCliente(clienteEntity.getCedulaCliente());
            usuario.setNombres(clienteEntity.getNombres());
            usuario.setApellidos(clienteEntity.getApellidos());
            usuario.setTipoDocumento(clienteEntity.getTipoDocumento());
            usuario.setPassword(passwordEncoder.encode(clienteEntity.getPassword()));

            clienteRepository.save(usuario);
        }catch (IllegalArgumentException i){
            log.error(i.getMessage());
        }
    }

    public Boolean actualizarImagenCliente(ClienteEntity clienteEntity)  {

        try {

            ClienteEntity usuario = clienteRepository.findByCedulaCliente(clienteEntity.getCedulaCliente());

            log.info(usuario.getCedulaCliente());

            usuario.setImagenCliente(clienteEntity.getImagenCliente());

            clienteRepository.save(usuario);

            return true;

        }catch (IllegalArgumentException i){
            log.error(i.getMessage());

            return false;
        }
    }

    public void actualizarAdministrador(ClienteEntity clienteEntity)  {

        try {

            ClienteEntity usuario = clienteRepository.findByCedulaCliente(clienteEntity.getCedulaCliente());

            usuario.setEmail(clienteEntity.getEmail());
            usuario.setImagenCliente(clienteEntity.getImagenCliente());
            usuario.setUsername(clienteEntity.getUsername());
            usuario.setTelefono(clienteEntity.getTelefono());
            usuario.setPassword(clienteEntity.getPassword());
            usuario.setApellidos(clienteEntity.getApellidos());
            usuario.setNombres(clienteEntity.getNombres());
            usuario.setEstado(clienteEntity.getEstado());
            usuario.setCedulaCliente(clienteEntity.getCedulaCliente());
            usuario.setTipoDocumento(clienteEntity.getTipoDocumento());
            usuario.setPlanes(clienteEntity.getPlanes());
            usuario.setAdministradorCreador(clienteEntity.getAdministradorCreador());

            clienteRepository.save(usuario);
        }catch (IllegalArgumentException i){
            log.error(i.getMessage());
        }
    }


    public void borrar(String cedula) {

        try{
            ClienteEntity cliente = clienteRepository.findByCedulaCliente(cedula);
            clienteRepository.delete(cliente);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public List<ClienteModel> obtenerClientes(){

        return convertidor.convertidorListaClientes(clienteRepository.findAll());

    }

    public ClienteModel obtenerClienteIdentificador(String identificador){

        return convertidor.convertirObjetoClientes(clienteRepository.findByCedulaCliente(identificador));

    }


    public ClienteModel buescarClienteEmail(String email){

        ClienteEntity cliente = clienteRepository.findByEmail(email);

        return convertidor.convertirObjetoClientes(cliente);

    }

    public List<ClienteModel> buscarClienteNombre(String nombre){

        List<ClienteEntity> cliente = clienteRepository.findByNombres(nombre);

        return convertidor.convertidorListaClientes(cliente);

    }

    public List<ClienteModel> buscarClienteApellidos(String apellidos){

        List<ClienteEntity> cliente = clienteRepository.findByApellidos(apellidos);

        return convertidor.convertidorListaClientes(cliente);

    }

    public List<ClienteModel> buscarClienteEstado(String estado){

        List<ClienteEntity> cliente = clienteRepository.findByEstado(estado);

        return convertidor.convertidorListaClientes(cliente);

    }


    public ClienteModel buscarClienteUsername(String username){

        ClienteEntity cliente = clienteRepository.findByUsername(username);

        return convertidor.convertirObjetoClientes(cliente);
    }



    public Boolean existenciaCliente(String email){

        return clienteRepository.existsByEmail(email);

    }

    public Boolean existenciaClienteCedula(String cedula){

        return clienteRepository.existsByCedulaCliente(cedula);

    }

    public Boolean actualizarContrasenaCliente(String email, String contrasena){

        ClienteEntity cliente = clienteRepository.findByEmail(email);



        if(!passwordEncoder.matches(contrasena, cliente.getPassword())){

            cliente.setPassword(passwordEncoder.encode(contrasena));

            clienteRepository.save(cliente);

            return true;

        }else{

            return false;

        }
    }

    public ClienteEntity obtenerClienteEmail(String email){

        return clienteRepository.findByEmail(email);

    }

    public  ClienteEntity obtenerClienteCedula(String cedula){

        return clienteRepository.findByCedulaCliente(cedula);

    }



    public JwtDto login(ClienteEntity clienteEntity){


        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(clienteEntity.getEmail(), clienteEntity.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.crearTokenCliente(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtDto jwtDto = new JwtDto(jwt, userDetails.getAuthorities());

        return jwtDto;
    }

   public void actualizarCedula(ClienteEntity clienteEntity){

        try{

            ClienteEntity cliente = clienteRepository.findByCedulaCliente(clienteEntity.getCedulaCliente());

            cliente.setCedulaCliente(clienteEntity.getUsername());

            clienteRepository.save(cliente);

        }catch (Exception ex) {

            log.error(ex.getMessage());
        }

   }

   public void actualizarTodo(ClienteEntity clienteEntity){

       try{

           ClienteEntity cliente = clienteRepository.findByCedulaCliente(clienteEntity.getCedulaCliente());

           cliente.setPlanes(clienteEntity.getPlanes());
           cliente.setUsername(clienteEntity.getUsername());
           cliente.setEmail(clienteEntity.getEmail());
           cliente.setTelefono(clienteEntity.getTelefono());
           cliente.setNombres(clienteEntity.getNombres());
           cliente.setApellidos(clienteEntity.getApellidos());
           cliente.setEstado(clienteEntity.getEstado());
           cliente.setDireccion(clienteEntity.getDireccion());
           cliente.setTipoDocumento(clienteEntity.getTipoDocumento());
           cliente.setAdministradorCreador(clienteEntity.getAdministradorCreador());

           clienteRepository.save(cliente);

       }catch (Exception ex) {

           log.error(ex.getMessage());
       }

   }

    public Page<ClienteEntity> obtenerClientesPaginador(Pageable pageable){
        return clienteRepository.findAll(pageable);
    }

    public List<ClienteEntity> mostrarClientesNombre(String palabraCliente){

        int espacios = 0;

        int indice = 0;

        for(int i = 0; i<palabraCliente.length(); i++ ){

            if(palabraCliente.charAt(i) == ' '){

                indice=i-1;
                espacios++;
                break;

            }

        }

        String primeraPalabra = palabraCliente.substring(0, indice+1);

        List<ClienteEntity> clientes = clienteRepository.findAll();

        List<ClienteEntity> clientesMostrar = new ArrayList<>();

        if(espacios > 0){

            for(ClienteEntity cliente: clientes){

                String palabraClave = String.valueOf(cliente.getNombres().charAt(0));

                for(int i =1; i<cliente.getNombres().length(); i++){

                    if(cliente.getNombres().charAt(i) != ' '){

                        palabraClave += cliente.getNombres().charAt(i);
                        if(palabraClave.toLowerCase().equals(palabraCliente.toLowerCase())){

                            if(!clientesMostrar.contains(cliente)){
                                clientesMostrar.add(cliente);
                                palabraClave = "";

                            }

                        }

                    }else{

                        if(palabraClave.toLowerCase().equals(primeraPalabra.toLowerCase())){


                            for(int a = indice+2; a<palabraCliente.length(); a++){

                                if (palabraCliente.charAt(a) == ' ' || palabraCliente.charAt(a) == palabraCliente.charAt(palabraCliente.length()-1)){

                                    primeraPalabra += " " + palabraCliente.substring(indice+2, a+1);
                                    palabraClave += " ";
                                    break;
                                }
                            }

                        }else{

                            palabraClave = "";

                        }

                    }

                }

                for(int j = 0; j<palabraCliente.length(); j++ ){

                    if(palabraCliente.charAt(j) == ' '){

                        indice=j-1;
                        espacios++;
                        break;

                    }

                }

                primeraPalabra = palabraCliente.substring(0, indice+1);

            }


        }else{

            for (ClienteEntity cliente: clientes){

                String palabraClave = "";

                for(int i = 0; i < cliente.getNombres().length(); i++){

                    if(cliente.getNombres().charAt(i) != ' '){

                        palabraClave += cliente.getNombres().charAt(i);
                        if(palabraClave.toLowerCase().equals(palabraCliente.toLowerCase())){

                            if(!clientesMostrar.contains(cliente)){
                                clientesMostrar.add(cliente);
                                palabraClave = "";
                            }

                        }

                    }else{

                        palabraClave = "";

                    }


                }

            }

        }


        return clientesMostrar;

    }

    public List<ClienteEntity> mostrarClientesDocumento(String palabraCliente){

        int espacios = 0;

        int indice = 0;

        for(int i = 0; i<palabraCliente.length(); i++ ){

            if(palabraCliente.charAt(i) == ' '){

                indice=i-1;
                espacios++;
                break;

            }

        }

        String primeraPalabra = palabraCliente.substring(0, indice+1);

        List<ClienteEntity> clientes = clienteRepository.findAll();

        List<ClienteEntity> clientesMostrar = new ArrayList<>();

        if(espacios > 0){

            for(ClienteEntity cliente: clientes){

                String palabraClave = String.valueOf(cliente.getCedulaCliente().charAt(0));

                for(int i =1; i<cliente.getCedulaCliente().length(); i++){

                    if(cliente.getCedulaCliente().charAt(i) != ' '){

                        palabraClave += cliente.getCedulaCliente().charAt(i);
                        if(palabraClave.toLowerCase().equals(palabraCliente.toLowerCase())){

                            if(!clientesMostrar.contains(cliente)){
                                clientesMostrar.add(cliente);
                                palabraClave = "";

                            }

                        }

                    }else{

                        if(palabraClave.toLowerCase().equals(primeraPalabra.toLowerCase())){


                            for(int a = indice+2; a<palabraCliente.length(); a++){

                                if (palabraCliente.charAt(a) == ' ' || palabraCliente.charAt(a) == palabraCliente.charAt(palabraCliente.length()-1)){

                                    primeraPalabra += " " + palabraCliente.substring(indice+2, a+1);
                                    palabraClave += " ";
                                    break;
                                }
                            }

                        }else{

                            palabraClave = "";

                        }

                    }

                }

                for(int j = 0; j<palabraCliente.length(); j++ ){

                    if(palabraCliente.charAt(j) == ' '){

                        indice=j-1;
                        espacios++;
                        break;

                    }

                }

                primeraPalabra = palabraCliente.substring(0, indice+1);

            }


        }else{

            for (ClienteEntity cliente: clientes){

                String palabraClave = "";

                for(int i = 0; i < cliente.getCedulaCliente().length(); i++){

                    if(cliente.getCedulaCliente().charAt(i) != ' '){

                        palabraClave += cliente.getCedulaCliente().charAt(i);
                        if(palabraClave.toLowerCase().equals(palabraCliente.toLowerCase())){

                            if(!clientesMostrar.contains(cliente)){
                                clientesMostrar.add(cliente);
                                palabraClave = "";
                            }

                        }

                    }else{

                        palabraClave = "";

                    }


                }

            }

        }


        return clientesMostrar;

    }

    public List<ClienteEntity> mostrarClientesCiudad(String palabraCliente){

        int espacios = 0;

        int indice = 0;

        for(int i = 0; i<palabraCliente.length(); i++ ){

            if(palabraCliente.charAt(i) == ' '){

                indice=i-1;
                espacios++;
                break;

            }

        }

        String primeraPalabra = palabraCliente.substring(0, indice+1);

        List<ClienteEntity> clientes = clienteRepository.findAll();

        List<ClienteEntity> clientesMostrar = new ArrayList<>();

        if(espacios > 0){

            for(ClienteEntity cliente: clientes){

                String palabraClave = String.valueOf(cliente.getDireccion().getBarrios().getCiudadOrigen().getNombreCiudad().charAt(0));

                for(int i =1; i<cliente.getDireccion().getBarrios().getCiudadOrigen().getNombreCiudad().length(); i++){

                    if(cliente.getDireccion().getBarrios().getCiudadOrigen().getNombreCiudad().charAt(i) != ' '){

                        palabraClave += cliente.getDireccion().getBarrios().getCiudadOrigen().getNombreCiudad().charAt(i);
                        if(palabraClave.toLowerCase().equals(palabraCliente.toLowerCase())){

                            if(!clientesMostrar.contains(cliente)){
                                clientesMostrar.add(cliente);
                                palabraClave = "";

                            }

                        }

                    }else{

                        if(palabraClave.toLowerCase().equals(primeraPalabra.toLowerCase())){


                            for(int a = indice+2; a<palabraCliente.length(); a++){

                                if (palabraCliente.charAt(a) == ' ' || palabraCliente.charAt(a) == palabraCliente.charAt(palabraCliente.length()-1)){

                                    primeraPalabra += " " + palabraCliente.substring(indice+2, a+1);
                                    palabraClave += " ";
                                    break;
                                }
                            }

                        }else{

                            palabraClave = "";

                        }

                    }

                }

                for(int j = 0; j<palabraCliente.length(); j++ ){

                    if(palabraCliente.charAt(j) == ' '){

                        indice=j-1;
                        espacios++;
                        break;

                    }

                }

                primeraPalabra = palabraCliente.substring(0, indice+1);

            }


        }else{

            for (ClienteEntity cliente: clientes){

                String palabraClave = "";

                for(int i = 0; i < cliente.getDireccion().getBarrios().getCiudadOrigen().getNombreCiudad().length(); i++){

                    if(cliente.getDireccion().getBarrios().getCiudadOrigen().getNombreCiudad().charAt(i) != ' '){

                        palabraClave += cliente.getDireccion().getBarrios().getCiudadOrigen().getNombreCiudad().charAt(i);
                        if(palabraClave.toLowerCase().equals(palabraCliente.toLowerCase())){

                            if(!clientesMostrar.contains(cliente)){
                                clientesMostrar.add(cliente);
                                palabraClave = "";
                            }

                        }

                    }else{

                        palabraClave = "";

                    }


                }

            }

        }


        return clientesMostrar;

    }

}
