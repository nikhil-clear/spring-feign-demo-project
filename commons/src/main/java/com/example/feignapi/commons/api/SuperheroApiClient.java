package com.example.feignapi.commons.api;

import com.example.feignapi.commons.dto.BatmanResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient
public interface SuperheroApiClient {
    @GetMapping(value = "/call-batman", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BatmanResponse> callBatman(@RequestParam("name") String name);

    @GetMapping(value = "/no-of-villains", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BatmanResponse> noOfVillains(@RequestParam("value") Integer value);

    @PostMapping(value = "/no-of-heros",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BatmanResponse> noOfHeros(@RequestParam("value") Integer value);
}
