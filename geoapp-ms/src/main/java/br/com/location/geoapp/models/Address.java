package br.com.location.geoapp.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder @AllArgsConstructor
@NoArgsConstructor @ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {

    private String neighborhood;
    private String street;
    private Integer number;
    private String country;
    private String city;

}
