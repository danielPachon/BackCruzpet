package com.cruzpet.security.jwt;

//VERIFICA SI EL TOKEN ES CORRECTO Y SI EXISTE

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

    private final Log logger = LogFactory.getLog(getClass());

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        logger.error("Hubo un error en el token");

        logger.error(authException.getMessage());

        //PARA RECHAZAR TODAS LAS NUEVAS PETICIONES QUE NO ESTEN AUTENTICADAS
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No autorizado");

    }
}
