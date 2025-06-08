package com.example.feignapi.commons.dto;

public class BatmanResponse {
    private String message;

    public BatmanResponse() {}

    public BatmanResponse(String s) {
        this.message = s;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("BatmanResponse(message=%s)", this.message);
    }
}
