package br.com.mail.sender.models.dto.infra.request;

public record EmailRequest(
        String to,
        String subject,
        String body
) {
}
