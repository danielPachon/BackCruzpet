package com.cruzpet.configurations.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;

    @Value("${mail.url.front}")
    private String urlFront;

    public void sendEmail() {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("dsrodriguez561@misena.edu.co");
        message.setTo("bacg20044@gmail.com");
        message.setSubject("Prueba Envio Email Simple");
        message.setText("Esto es el contenido del correo");

        javaMailSender.send(message);

    }

    public void sendEmailTemplate(EmailValuesDTO dto){

        MimeMessage message = javaMailSender.createMimeMessage();

        try{

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            Context context = new Context();
            Map<String, Object> model = new HashMap<>();
            model.put("userName", dto.getUserName());
            model.put("url", urlFront + dto.getPasswordEncript());
            context.setVariables(model);
            String htmlText = templateEngine.process("email-template", context);
            helper.setFrom(dto.getMailFrom());
            helper.setTo(dto.getMailTo());
            helper.setSubject(dto.getSubject());
            helper.setText(htmlText, true);

            javaMailSender.send(message);

        }catch (MessagingException e){
            e.printStackTrace();
        }

    }

}
