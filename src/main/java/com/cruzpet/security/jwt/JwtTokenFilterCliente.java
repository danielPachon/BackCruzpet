package com.cruzpet.security.jwt;

//SI EL TOKEN ES VALIDO

import com.cruzpet.services.DetailsServiceImpl;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilterCliente extends OncePerRequestFilter {

    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private DetailsServiceImpl clienteService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try{

            String token = getToken(request);
            if(token != null && jwtProvider.validarToken(token)){

                String emailCliente = jwtProvider.getEmailClienteDelToken(token);
                UserDetails userDetails = clienteService.loadUserByUsername(emailCliente);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        }catch (Exception e){

            logger.error("Error en el metodo doFilter");

        }

        filterChain.doFilter(request, response);

    }
    private String getToken(HttpServletRequest request) {

        String header = request.getHeader("Authorization");

        if(header != null && header.startsWith("Bearer")){

            return header.replace("Bearer ", "");

        }

        return null;

    }
}
