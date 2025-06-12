package com.example.feignapi.controllers;

import feign.RetryableException;
import feign.Retryer;

public class CustomRetryer implements Retryer {

    private int maxAttempts = 5;
    private int attempt = 1;

    public CustomRetryer() {}

    private CustomRetryer(int attempt, int maxAttempts) {
        this.attempt = attempt;
        this.maxAttempts = maxAttempts;
    }

    @Override
    public void continueOrPropagate(RetryableException e) {
        if (attempt++ >= maxAttempts) {
            throw e;
        }
        try {
            Thread.sleep(1000); // wait 1 second between retries
        } catch (InterruptedException ignored) {}
    }

    @Override
    public Retryer clone() {
        return new CustomRetryer(1, maxAttempts);
    }
}

