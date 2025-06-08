package com.example.feigndemo.client;

import com.example.feignapi.commons.api.SuperheroApiClient;
import com.example.feignapi.commons.dto.BatmanResponse;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.ResponseEntity;

public class Main {
    private static SuperheroApiClient makeClient() {
        return Feign.builder()
                .contract(new SpringMvcContract())
                .encoder(new JacksonEncoder())
                .decoder(new ResponseEntityDecoder(new JacksonDecoder()))
                .target(SuperheroApiClient.class, "http://localhost:8080/gotham");
    }

    public static void main(String[] args) {
        SuperheroApiClient client = makeClient();

        try {
            ResponseEntity<BatmanResponse> response = client.callBatman("Nikhil");
            System.out.println(response.getBody());
        } catch (Exception ignored) {
        }

        ResponseEntity<BatmanResponse> messageResponse = client.noOfVillains(5);
        System.out.println(messageResponse.getBody());
    }
}
