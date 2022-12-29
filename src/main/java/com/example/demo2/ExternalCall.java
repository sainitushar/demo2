package com.example.demo2;

import org.springframework.web.client.RestTemplate;

public class ExternalCall implements Runnable{

    String url;

    public ExternalCall(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        System.out.println("Thread id : " + Thread.currentThread().threadId());
        RestTemplate restTemplate = new RestTemplate();
        try {
            String response = restTemplate.getForObject(this.url, String.class);
            System.out.println("Response received for url : " + this.url + " is : " + response);
        } catch(Exception ex) {
            System.out.println("Exception encountered while calling url : " + this.url);
        }
    }
}
