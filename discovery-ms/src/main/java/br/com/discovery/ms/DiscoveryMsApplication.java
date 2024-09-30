package br.com.discovery.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DiscoveryMsApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscoveryMsApplication.class, args);
    }
}