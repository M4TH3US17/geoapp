package br.com.location.geoapp.controllers;

import br.com.location.geoapp.infrastructure.location.request.LocationRequest;
import br.com.location.geoapp.services.LocationService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController @Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/location")
public class LocationController {

    private final LocationService service;

    @SneakyThrows
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> receiveLocation(@RequestBody LocationRequest request) {
        log.info("LocationController :: Returning current location...");
        var response = service.returnsCurrentLocation(request);
        return ResponseEntity.status(response.code()).body(response);
    }

}
