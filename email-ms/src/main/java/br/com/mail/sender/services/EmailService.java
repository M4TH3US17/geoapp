package br.com.mail.sender.services;

import br.com.mail.sender.models.dto.infra.request.EmailRequest;
import br.com.mail.sender.utils.EmailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service @Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public void sendEmail(EmailRequest request) {
        log.info("EmailService :: Preparing e-mail to send...");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(request.to());
        message.setSubject(request.subject());
        message.setText(EmailUtils.returnsBodyEmail(request.body()));

        log.info("EmailService :: Enviando email (subject {})...", request.subject());
        mailSender.send(message);
    }

}
