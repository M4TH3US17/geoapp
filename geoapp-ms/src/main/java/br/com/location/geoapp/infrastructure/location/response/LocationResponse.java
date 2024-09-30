package br.com.location.geoapp.infrastructure.location.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record LocationResponse(
        int    code,
        String message,
        Object data
) {
}
