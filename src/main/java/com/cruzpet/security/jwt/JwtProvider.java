package com.cruzpet.security.jwt;


import com.cruzpet.models.AdministradorModel;
import com.cruzpet.models.ClienteModel;
import com.cruzpet.models.IpsasModel;
import com.cruzpet.models.VeterinarioModel;
import io.jsonwebtoken.*;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;


//GENERA EL TOKEN Y VE SI ESTA BIEN FORMADO EL TOKEN
@Component
public class JwtProvider {

    private final Log logger = LogFactory.getLog(getClass());

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String crearTokenCliente(Authentication authentication) {

        ClienteModel cliente = (ClienteModel) authentication.getPrincipal();

        return Jwts.builder().setSubject(cliente.getEmail())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000L)).signWith(SignatureAlgorithm.HS512, secret)
                .claim("informacion", cliente).compact();

    }

    public String crearTokenVeterinario(Authentication authentication) {

        VeterinarioModel veterinario = (VeterinarioModel) authentication.getPrincipal();

        return Jwts.builder().setSubject(veterinario.getNombres())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000L)).signWith(SignatureAlgorithm.HS512, secret)
                .claim("informacion", veterinario).compact();

    }

    public String crearTokenIpsa(Authentication authentication) {

        IpsasModel ipsa = (IpsasModel) authentication.getPrincipal();

        return Jwts.builder().setSubject(ipsa.getNombre())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000L)).signWith(SignatureAlgorithm.HS512, secret)
                .claim("informacion", ipsa).compact();

    }

    public String crearTokenAdministrador(Authentication authentication) {

        AdministradorModel administradorModel = (AdministradorModel) authentication.getPrincipal();

        return Jwts.builder().setSubject(administradorModel.getCorreAdministrador())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000L)).signWith(SignatureAlgorithm.HS512, secret)
                .claim("informacion", administradorModel).compact();

    }

    public String getTokenAdministrador(String token){

        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();


    }

    public String getEmailClienteDelToken (String token){

        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();

    }

    public String getEmailVeterinarioDelToken (String token){

        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();

    }

    public boolean validarToken(String token){

        try{

            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;

        }catch(MalformedJwtException ex){
            logger.error("Token mal formado");
        }catch(UnsupportedJwtException ex){
            logger.error("Token no soportado");
        }catch(ExpiredJwtException ex){
            logger.error("Token  expirado");
        }catch(IllegalArgumentException ex){
            logger.error("Token vacio");
        }catch(SignatureException ex){
            logger.error("Fallo en la firma");
        }
        return false;
    }


}
