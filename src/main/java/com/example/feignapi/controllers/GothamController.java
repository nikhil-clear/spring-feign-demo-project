package com.example.feignapi.controllers;

import com.example.feignapi.commons.api.SuperheroApiClient;
import com.example.feignapi.commons.dto.BatmanResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gotham")
public final class GothamController implements SuperheroApiClient {

    @Override
    public ResponseEntity<BatmanResponse> callBatman(String name) {
        return ResponseEntity.ok(new BatmanResponse("Hello " + name + ", this is batman!. I'm coming to save Gotham city!"));
    }

    @Override
    public ResponseEntity<BatmanResponse> noOfVillains(Integer value) {
      return ResponseEntity.ok(new BatmanResponse("Batman has received count of villains: " + value));
    }
}
