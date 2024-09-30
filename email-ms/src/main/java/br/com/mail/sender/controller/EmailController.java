package br.com.mail.sender.controller;

import br.com.mail.sender.models.dto.infra.request.EmailRequest;
import br.com.mail.sender.services.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController @Slf4j
@RequestMapping("/api/v1/email")
public class EmailController {

    @Autowired
    private EmailService service;

    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request) {
        log.info("EmailController :: Iniciando processo de envio de email...");
        service.sendEmail(request);

        return ResponseEntity.status(HttpStatus.OK).body("E-mail enviado com sucesso!");
    }
}
