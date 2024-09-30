package br.com.location.geoapp.infrastructure.location.request;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record LocationRequest(
        Double latitude,
        Double longitude
) {
}
