package com.cruzpet.configurations.email;

import com.cruzpet.models.ClienteModel;
import com.cruzpet.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/v1.0/email")
public class EmailController {

    @Autowired
    private  EmailService emailService;

    @Autowired
    private ClienteService cliente;
    @Value("${spring.mail.username}")
    private String mailFrom;

    @PostMapping("/send-html")
    public ResponseEntity<?> sendEmailTemplate(@RequestBody EmailValuesDTO dto) {
            if(cliente.existenciaCliente(dto.getMailTo())){
                dto.setMailFrom(mailFrom);
                dto.setSubject("Cambio de contraseña");
                emailService.sendEmailTemplate(dto);
                return new ResponseEntity("Correo enviado con exito", HttpStatus.OK);
            }else{
                return new ResponseEntity("No existe el usuario con dicho correo", HttpStatus.NOT_FOUND);

            }
    }

}
