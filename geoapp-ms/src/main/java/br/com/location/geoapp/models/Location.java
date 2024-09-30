package br.com.location.geoapp.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@NoArgsConstructor @ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Location {

}