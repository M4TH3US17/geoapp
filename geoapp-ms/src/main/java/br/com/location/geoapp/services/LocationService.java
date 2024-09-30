package br.com.location.geoapp.services;

import br.com.location.geoapp.infrastructure.location.request.LocationRequest;
import br.com.location.geoapp.infrastructure.location.response.LocationResponse;
import br.com.location.geoapp.utils.LocationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service @Slf4j @RequiredArgsConstructor
public class LocationService {

    @Value("${google.api-key}")
    private String API_KEY;

    private final RestTemplate restTemplate;

    public LocationResponse returnsCurrentLocation(LocationRequest request) {
        try {
            log.info("LocationService :: Starting search for device location (latitude={}, longitude={})...", request.latitude(), request.longitude());
            var possibleDeviceAddresses = LocationUtils.returnsPossibleDeviceAddresses(request, API_KEY);

            log.info("EmailController :: Preparando requisicao para envio de email...");
            String dateAndTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String route = "http://sender/api/v1/email/send-email";
            String requestBody = LocationUtils.buildEmailBody(
                    "matheusdalvino50@gmail.com",
                    "Enderecos (Data/Hora: " + dateAndTime + ")",
                    possibleDeviceAddresses.toString()
            );

            System.out.println(requestBody);

            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);
            log.info("EmailController :: Rota: {}, Headers (HTTP): {}", route, headers);

            log.info("EmailController :: Enviando requisicao para o microsservico de envio de emails...");
            ResponseEntity<String> response = restTemplate.exchange(route, HttpMethod.POST, httpEntity, String.class);
            log.info("EmailController :: Resposta do Microsservico de envio de e-mails: {} - Corpo: {}", response.getStatusCode(), response.getBody());

            return new LocationResponse(
                    HttpStatus.OK.value(),
                    "Current location returned successfully",
                    possibleDeviceAddresses
            );
        } catch(HttpClientErrorException error) {
            log.error("Erro ao enviar requisição para o serviço de email: {}", error.getMessage());
            return new LocationResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Endereço não encontrado ou erro na requisição.",
                    null
            );
        } catch (Exception e) {
            log.error("Erro inesperado: {}", e.getMessage());
            return new LocationResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Erro inesperado ao processar a requisição.",
                    null
            );
        }
    }

}
