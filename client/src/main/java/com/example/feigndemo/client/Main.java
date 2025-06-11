package com.example.feigndemo.client;

import com.example.feignapi.commons.api.SuperheroApiClient;
import com.example.feignapi.commons.dto.BatmanResponse;
import feign.Feign;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.ResponseEntity;

import static java.lang.Math.pow;


public class Main {
    private static SuperheroApiClient makeClient() {
        return Feign.builder()
                .contract(new SpringMvcContract())
                .encoder(new JacksonEncoder())
                .decoder(new ResponseEntityDecoder(new JacksonDecoder()))
//                .retryer(new Retryer.Default(1000, 10000, 5))
//                .retryer(new CustomRetryer(3, 1000))
                .target(SuperheroApiClient.class, "http://localhost:8080/gotham");
    }

    // period -> initial waiting time for retrial attempt
    // maxPeriod -> maximum waiting time for any two consecutive retrial attempts and it can increase by exponential or by some other methods
    // maxAttempts -> Maximum Retrial Attempts

    public static void callBatmanAPI(SuperheroApiClient client) throws InterruptedException {
        for(int i=1;i<=5;i++){
            try {
                if(i<=3) {
                    int AttemptToFailure = 2/0;
                }
                ResponseEntity<BatmanResponse> response = client.callBatman("Nikhil");
                System.out.println(response.getBody());
                return;
            } catch (Exception ignored) {
                Thread.sleep(1000*i);
                System.out.println("Retry");
            }
        }
    }

    public static void callNoOfVillainsAPI(SuperheroApiClient client) throws InterruptedException {
        for(int i=1;i<=5;i++){
            try {
                if(i==1) {
                    int AttemptToFailure = 2/0;
                }
                ResponseEntity<BatmanResponse> messageResponse = client.noOfVillains(5);
                System.out.println(messageResponse.getBody());
                return;
            } catch (Exception ignored) {
                Thread.sleep(1000*i);
                System.out.println("Retry");
            }
        }
    }

    public static void callNoOfHerosAPI(SuperheroApiClient client) throws InterruptedException {
        for(int i=1;i<=5;i++) {
            try {
                if(i==1) throw new Exception("First attempt is just to make it failure.");
                ResponseEntity<BatmanResponse> messageResponse = client.noOfHeros(10);
                System.out.println(messageResponse.getBody());
                return;
            } catch (Exception ignored) {
                Thread.sleep((long) (1000*pow(2,i)));
                System.out.println("Retry");
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        SuperheroApiClient client = makeClient();


        callBatmanAPI(client);
        callNoOfVillainsAPI(client);
        callNoOfHerosAPI(client);

    }
}
