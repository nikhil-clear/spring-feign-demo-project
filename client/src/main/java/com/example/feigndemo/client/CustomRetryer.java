package com.example.feigndemo.client;

import feign.RetryableException;
import feign.Retryer;

public class CustomRetryer implements Retryer {

    private final int maxAttempts;
    private final long backoff;
    private int attempt;

    public CustomRetryer() {
        this(3, 2000); // 3 attempts, 2 seconds delay
    }

    public CustomRetryer(int maxAttempts, long backoff) {
        this.maxAttempts = maxAttempts;
        this.backoff = backoff;
        this.attempt = 1;
    }

    @Override
    public void continueOrPropagate(RetryableException e) {
        if (attempt++ >= maxAttempts) {
            throw e;
        }
        try {
            Thread.sleep(backoff);
        } catch (InterruptedException ignored) {

        }
    }

    @Override
    public Retryer clone() {
        return new CustomRetryer(maxAttempts, backoff);
    }
}
