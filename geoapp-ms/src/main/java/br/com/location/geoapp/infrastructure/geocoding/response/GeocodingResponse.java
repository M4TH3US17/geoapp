package br.com.location.geoapp.infrastructure.geocoding.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record GeocodingResponse(String status, List<Result> results) {
    public record Result(@JsonProperty("formatted_address") String formattedAddress) {}
}