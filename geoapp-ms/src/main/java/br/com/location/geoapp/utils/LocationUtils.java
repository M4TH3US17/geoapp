package br.com.location.geoapp.utils;

import br.com.location.geoapp.infrastructure.geocoding.response.GeocodingResponse;
import br.com.location.geoapp.infrastructure.location.request.LocationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class LocationUtils {

    private static String API_URL = "https://maps.googleapis.com/maps/api/geocode/json?latlng=%s,%s&key=%s";

    public static Object returnsPossibleDeviceAddresses(LocationRequest data, String apiKey) {
            log.info("LocationUtils :: Accessing returnsCurrentAddress...");

            log.info("LocationUtils :: Preparing URL to request the address...");
            String url = String.format(API_URL, data.latitude(), data.longitude(), apiKey);

            log.info("LocationUtils :: URL constructed (URL={})", url);
            log.info("LocationUtils :: Sending request to Google...");
            GeocodingResponse response = new RestTemplate().getForObject(url, GeocodingResponse.class);

            log.info("LocationUtils :: Location returned by Google: {}", response);
            return response.results();
    }

    public static String buildEmailBody(String sendTo, String subject, String body) {
        log.info("LocationUtils :: Montando corpo do email...");
        return String.format("{\"to\": \"%s\", \"subject\": \"%s\", \"body\": \"%s\"}",
                sendTo,
                subject,
                body);
    }

}
