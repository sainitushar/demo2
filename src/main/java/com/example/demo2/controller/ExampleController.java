package com.example.demo2.controller;

import com.example.demo2.ExternalCall;
import com.example.demo2.model.Call;
import com.example.demo2.model.CallPayload;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ExampleController {

    @PostMapping("/call")
    public String callEndpoint(@RequestBody CallPayload callPayload) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<Thread> threadList = new ArrayList<>();
        System.out.println("Main Thread id : " + Thread.currentThread().threadId());
        if(callPayload != null) {
            for(Call call : callPayload.getCallList()) {
                if(call.isParallel()) {
                    for(int i = 0; i< call.getCount(); i++) {
                        Runnable r = new ExternalCall(call.getUrl());
                        Thread t = new Thread(r);
                        threadList.add(t);
                        t.start();
                    }
                    for(Thread thread : threadList) {
                        try {
                            thread.join();
                        } catch (InterruptedException e) {
                            System.out.println("Exception caught while waiting for child thread to complete : " + e.getMessage());
                        }
                    }
                    threadList.clear();
                } else {
                    for(int i = 0; i < call.getCount(); i++) {
                        RestTemplate restTemplate = new RestTemplate();
                        try {
                            System.out.println("Thread id : " + Thread.currentThread().threadId());
                            String response = restTemplate.getForObject(call.getUrl(), String.class);
                            System.out.println("Response received for url : " + call.getUrl() + " is " + response);
                        } catch(Exception ex) {
                            System.out.println("Exception encountered while calling url : " + call.getUrl());
                        }
                    }
                }
            }
        }
        stopWatch.stop();
        System.out.println("Time taken : " + stopWatch.getTotalTimeMillis());
        return "Time taken : " + stopWatch.getTotalTimeMillis();
    }

}
